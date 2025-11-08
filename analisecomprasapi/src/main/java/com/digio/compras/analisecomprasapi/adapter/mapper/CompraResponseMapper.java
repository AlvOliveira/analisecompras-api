package com.digio.compras.analisecomprasapi.adapter.mapper;

import com.digio.compras.analisecomprasapi.adapter.dto.response.ClienteFielResponseDto;
import com.digio.compras.analisecomprasapi.adapter.dto.response.CompraResponseDto;
import com.digio.compras.analisecomprasapi.adapter.dto.response.RecomendacaoResponseDto;
import com.digio.compras.analisecomprasapi.application.dto.ClienteFiel;
import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.application.dto.RecomendacaoVinho;

import java.util.stream.Collectors;

/**
 * Mapper para converter DTOs da camada de aplicação para DTOs de resposta da camada de adaptador.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompraResponseMapper {
    
    private CompraResponseMapper() {
        // Construtor privado para classe utilitária
    }

    /**
     * Converte CompraDetalhada para CompraResponseDto.
     * 
     * @param compraDetalhada DTO da camada de aplicação
     * @return DTO de resposta
     */
    public static CompraResponseDto toResponseDto(CompraDetalhada compraDetalhada) {
        if (compraDetalhada == null) {
            return null;
        }
        
        var itens = compraDetalhada.getItens().stream()
            .map(CompraResponseMapper::toDetalheCompraDto)
            .collect(Collectors.toList());
        
        return new CompraResponseDto(
            compraDetalhada.getNomeCliente(),
            compraDetalhada.getCpf(),
            itens,
            compraDetalhada.getValorTotal()
        );
    }

    /**
     * Converte ItemCompra para DetalheCompraDto.
     * 
     * @param itemCompra Item da compra
     * @return DTO de detalhe da compra
     */
    private static CompraResponseDto.DetalheCompraDto toDetalheCompraDto(
            CompraDetalhada.ItemCompra itemCompra) {
        if (itemCompra == null) {
            return null;
        }
        
        return new CompraResponseDto.DetalheCompraDto(
            itemCompra.getTipo(),
            itemCompra.getSafra(),
            itemCompra.getPrecoUnitario(),
            itemCompra.getQuantidade(),
            itemCompra.getAnoCompra(),
            itemCompra.getSubtotal()
        );
    }

    /**
     * Converte ClienteFiel para ClienteFielResponseDto.
     * 
     * @param clienteFiel DTO da camada de aplicação
     * @return DTO de resposta
     */
    public static ClienteFielResponseDto toClienteFielResponseDto(ClienteFiel clienteFiel) {
        if (clienteFiel == null) {
            return null;
        }
        
        return new ClienteFielResponseDto(
            clienteFiel.getNome(),
            clienteFiel.getCpf(),
            clienteFiel.getQuantidadeCompras(),
            clienteFiel.getValorTotalGasto()
        );
    }

    /**
     * Converte RecomendacaoVinho para RecomendacaoResponseDto.
     * 
     * @param recomendacao DTO da camada de aplicação
     * @return DTO de resposta
     */
    public static RecomendacaoResponseDto toRecomendacaoResponseDto(RecomendacaoVinho recomendacao) {
        if (recomendacao == null) {
            return null;
        }
        
        return new RecomendacaoResponseDto(
            recomendacao.getNome(),
            recomendacao.getCpf(),
            recomendacao.getTipoRecomendado(),
            recomendacao.getQuantidadeComprasDoTipo()
        );
    }
}
