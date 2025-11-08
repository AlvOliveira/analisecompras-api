package com.digio.compras.analisecomprasapi.adapter.controller;

import com.digio.compras.analisecomprasapi.application.dto.ClienteFiel;
import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.application.dto.RecomendacaoVinho;
import com.digio.compras.analisecomprasapi.application.usecase.BuscarClientesFieisUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.BuscarMaiorCompraAnoUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.ListarComprasOrdenadasUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.RecomendarVinhoUseCase;
import com.digio.compras.analisecomprasapi.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Testes de integração para AnaliseComprasController.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@WebFluxTest(AnaliseComprasController.class)
@DisplayName("Testes de integração do Controller de Análise de Compras")
class AnaliseComprasControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ListarComprasOrdenadasUseCase listarComprasUseCase;

    @MockBean
    private BuscarMaiorCompraAnoUseCase buscarMaiorCompraUseCase;

    @MockBean
    private BuscarClientesFieisUseCase buscarClientesFieisUseCase;

    @MockBean
    private RecomendarVinhoUseCase recomendarVinhoUseCase;

    @Test
    @DisplayName("GET /compras deve retornar lista de compras com status 200")
    void getComprasDeveRetornarListaComStatus200() {
        // Arrange
        CompraDetalhada.ItemCompra item = new CompraDetalhada.ItemCompra(
            "Tinto", "2018", new BigDecimal("50.00"), 2, 2020, new BigDecimal("100.00")
        );
        CompraDetalhada compra = new CompraDetalhada(
            "João Silva", "111.111.111-11", List.of(item), new BigDecimal("100.00")
        );

        when(listarComprasUseCase.execute()).thenReturn(Flux.just(compra));

        // Act & Assert
        webTestClient.get()
            .uri("/compras")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Object.class)
            .hasSize(1);
    }

    @Test
    @DisplayName("GET /maior-compra/{ano} deve retornar maior compra com status 200")
    void getMaiorCompraDeveRetornarCompraComStatus200() {
        // Arrange
        CompraDetalhada.ItemCompra item = new CompraDetalhada.ItemCompra(
            "Tinto", "2018", new BigDecimal("75.00"), 3, 2020, new BigDecimal("225.00")
        );
        CompraDetalhada compra = new CompraDetalhada(
            "Maria Santos", "222.222.222-22", List.of(item), new BigDecimal("225.00")
        );

        when(buscarMaiorCompraUseCase.execute(2020)).thenReturn(Mono.just(compra));

        // Act & Assert
        webTestClient.get()
            .uri("/maior-compra/2020")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.nomeCliente").isEqualTo("Maria Santos")
            .jsonPath("$.valorTotal").isEqualTo(225.00);
    }

    @Test
    @DisplayName("GET /maior-compra/{ano} deve retornar 404 quando não encontrar compra")
    void getMaiorCompraDeveRetornar404QuandoNaoEncontrar() {
        // Arrange
        when(buscarMaiorCompraUseCase.execute(anyInt()))
            .thenReturn(Mono.error(new ResourceNotFoundException("Não encontrado")));

        // Act & Assert
        webTestClient.get()
            .uri("/maior-compra/2025")
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("GET /clientes-fieis deve retornar top 3 clientes com status 200")
    void getClientesFieisDeveRetornarTop3ComStatus200() {
        // Arrange
        ClienteFiel cliente = new ClienteFiel(
            "João Silva", "111.111.111-11", 10, new BigDecimal("1500.00")
        );

        when(buscarClientesFieisUseCase.execute()).thenReturn(Flux.just(cliente));

        // Act & Assert
        webTestClient.get()
            .uri("/clientes-fieis")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Object.class)
            .hasSize(1);
    }

    @Test
    @DisplayName("GET /recomendacao/cliente/tipo deve retornar recomendações com status 200")
    void getRecomendacaoDeveRetornarRecomendacoesComStatus200() {
        // Arrange
        RecomendacaoVinho recomendacao = new RecomendacaoVinho(
            "Pedro Alves", "333.333.333-33", "Tinto", 8
        );

        when(recomendarVinhoUseCase.execute()).thenReturn(Flux.just(recomendacao));

        // Act & Assert
        webTestClient.get()
            .uri("/recomendacao/cliente/tipo")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Object.class)
            .hasSize(1);
    }
}
