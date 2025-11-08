package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.domain.exception.ResourceNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Testes unitários para BuscarMaiorCompraAnoUseCase.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do caso de uso: Buscar Maior Compra por Ano")
class BuscarMaiorCompraAnoUseCaseTest {

    @Mock
    private ListarComprasOrdenadasUseCase listarComprasUseCase;

    private BuscarMaiorCompraAnoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BuscarMaiorCompraAnoUseCase(listarComprasUseCase);
    }

    @Test
    @DisplayName("Deve buscar a maior compra do ano especificado")
    void deveBuscarMaiorCompraDoAno() {
        // Arrange
        int ano = 2020;
        CompraDetalhada.ItemCompra item1 = new CompraDetalhada.ItemCompra(
            "Tinto", "2018", new BigDecimal("50.00"), 2, 2020, new BigDecimal("100.00")
        );
        CompraDetalhada.ItemCompra item2 = new CompraDetalhada.ItemCompra(
            "Branco", "2019", new BigDecimal("75.00"), 3, 2020, new BigDecimal("225.00")
        );

        CompraDetalhada compra1 = new CompraDetalhada("João", "111", List.of(item1), new BigDecimal("100.00"));
        CompraDetalhada compra2 = new CompraDetalhada("Maria", "222", List.of(item2), new BigDecimal("225.00"));

        when(listarComprasUseCase.execute()).thenReturn(Flux.just(compra1, compra2));

        // Act & Assert
        StepVerifier.create(useCase.execute(ano))
            .assertNext(result -> {
                assertEquals("Maria", result.getNomeCliente());
                assertEquals(new BigDecimal("225.00"), result.getValorTotal());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar compras no ano")
    void deveLancarExcecaoQuandoNaoEncontrarComprasNoAno() {
        // Arrange
        int ano = 2025;
        CompraDetalhada.ItemCompra item = new CompraDetalhada.ItemCompra(
            "Tinto", "2018", new BigDecimal("50.00"), 2, 2020, new BigDecimal("100.00")
        );
        CompraDetalhada compra = new CompraDetalhada("João", "111", List.of(item), new BigDecimal("100.00"));

        when(listarComprasUseCase.execute()).thenReturn(Flux.just(compra));

        // Act & Assert
        StepVerifier.create(useCase.execute(ano))
            .expectError(ResourceNotFoundException.class)
            .verify();
    }
}
