package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.domain.entity.ClienteEntity;
import com.digio.compras.analisecomprasapi.domain.entity.CompraEntity;
import com.digio.compras.analisecomprasapi.domain.entity.ProdutoEntity;
import com.digio.compras.analisecomprasapi.domain.repository.ClienteRepository;
import com.digio.compras.analisecomprasapi.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para ListarComprasOrdenadasUseCase.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do caso de uso: Listar Compras Ordenadas")
class ListarComprasOrdenadasUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private ListarComprasOrdenadasUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarComprasOrdenadasUseCase(clienteRepository, produtoRepository);
    }

    @Test
    @DisplayName("Deve listar compras ordenadas por valor crescente")
    void deveListarComprasOrdenadasPorValor() {
        // Arrange
        ProdutoEntity produto1 = new ProdutoEntity("P001", "Tinto", "2018", 
            new BigDecimal("50.00"), 2020);
        ProdutoEntity produto2 = new ProdutoEntity("P002", "Branco", "2019", 
            new BigDecimal("100.00"), 2020);

        CompraEntity compra1 = new CompraEntity("P001", 2);
        CompraEntity compra2 = new CompraEntity("P002", 1);

        ClienteEntity cliente1 = new ClienteEntity("João Silva", "111.111.111-11", 
            List.of(compra1));
        ClienteEntity cliente2 = new ClienteEntity("Maria Santos", "222.222.222-22", 
            List.of(compra2));

        when(produtoRepository.findAll()).thenReturn(Flux.just(produto1, produto2));
        when(clienteRepository.findAll()).thenReturn(Flux.just(cliente1, cliente2));

        // Act & Assert
        StepVerifier.create(useCase.execute())
            .assertNext(compra -> {
                assertEquals("João Silva", compra.getNomeCliente());
                assertEquals(new BigDecimal("100.00"), compra.getValorTotal());
            })
            .assertNext(compra -> {
                assertEquals("Maria Santos", compra.getNomeCliente());
                assertEquals(new BigDecimal("100.00"), compra.getValorTotal());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar flux vazio quando não há compras")
    void deveRetornarVazioQuandoNaoHaCompras() {
        // Arrange
        when(produtoRepository.findAll()).thenReturn(Flux.empty());
        when(clienteRepository.findAll()).thenReturn(Flux.empty());

        // Act & Assert
        StepVerifier.create(useCase.execute())
            .verifyComplete();
    }

    @Test
    @DisplayName("Deve calcular valor total corretamente")
    void deveCalcularValorTotalCorretamente() {
        // Arrange
        ProdutoEntity produto = new ProdutoEntity("P001", "Tinto", "2018", 
            new BigDecimal("75.50"), 2020);
        CompraEntity compra = new CompraEntity("P001", 3);
        ClienteEntity cliente = new ClienteEntity("João Silva", "111.111.111-11", 
            List.of(compra));

        when(produtoRepository.findAll()).thenReturn(Flux.just(produto));
        when(clienteRepository.findAll()).thenReturn(Flux.just(cliente));

        // Act & Assert
        StepVerifier.create(useCase.execute())
            .assertNext(result -> {
                assertEquals(new BigDecimal("226.50"), result.getValorTotal());
                assertEquals(1, result.getItens().size());
                assertEquals(3, result.getItens().get(0).getQuantidade());
            })
            .verifyComplete();
    }
}
