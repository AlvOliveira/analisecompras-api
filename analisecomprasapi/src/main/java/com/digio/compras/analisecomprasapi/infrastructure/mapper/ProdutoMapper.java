package com.digio.compras.analisecomprasapi.infrastructure.mapper;

import com.digio.compras.analisecomprasapi.domain.entity.ProdutoEntity;
import com.digio.compras.analisecomprasapi.infrastructure.client.dto.ProdutoApiDto;

/**
 * Mapper para conversão entre ProdutoApiDto e ProdutoEntity.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProdutoMapper {
    
    private ProdutoMapper() {
        // Construtor privado para classe utilitária
    }
    
    /**
     * Converte um ProdutoApiDto para ProdutoEntity.
     * 
     * @param dto O DTO recebido da API externa
     * @return A entidade de domínio correspondente
     */
    public static ProdutoEntity toDomain(ProdutoApiDto dto) {
        if (dto == null) {
            return null;
        }
        
        return new ProdutoEntity(
            dto.getCodigo(),
            dto.getTipoVinho(),
            dto.getSafra(),
            dto.getPreco(),
            dto.getAnoCompra()
        );
    }
}
