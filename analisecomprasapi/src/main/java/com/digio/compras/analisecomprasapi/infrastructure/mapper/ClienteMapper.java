package com.digio.compras.analisecomprasapi.infrastructure.mapper;

import com.digio.compras.analisecomprasapi.domain.entity.ClienteEntity;
import com.digio.compras.analisecomprasapi.domain.entity.CompraEntity;
import com.digio.compras.analisecomprasapi.infrastructure.client.dto.ClienteApiDto;
import com.digio.compras.analisecomprasapi.infrastructure.client.dto.CompraApiDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para conversão entre ClienteApiDto e ClienteEntity.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClienteMapper {
    
    private ClienteMapper() {
        // Construtor privado para classe utilitária
    }
    
    /**
     * Converte um ClienteApiDto para ClienteEntity.
     * 
     * @param dto O DTO recebido da API externa
     * @return A entidade de domínio correspondente
     */
    public static ClienteEntity toDomain(ClienteApiDto dto) {
        if (dto == null) {
            return null;
        }
        
        List<CompraEntity> compras = dto.getCompras() != null
            ? dto.getCompras().stream()
                .map(ClienteMapper::toCompraEntity)
                .collect(Collectors.toList())
            : Collections.emptyList();
        
        return new ClienteEntity(
            dto.getNome(),
            dto.getCpf(),
            compras
        );
    }
    
    /**
     * Converte um CompraApiDto para CompraEntity.
     * 
     * @param dto O DTO de compra recebido da API externa
     * @return A entidade de domínio correspondente
     */
    private static CompraEntity toCompraEntity(CompraApiDto dto) {
        if (dto == null) {
            return null;
        }
        
        return new CompraEntity(
            dto.getCodigo(),
            dto.getQuantidade()
        );
    }
}
