package com.digio.compras.analisecomprasapi.domain.repository;

import com.digio.compras.analisecomprasapi.domain.entity.ClienteEntity;
import reactor.core.publisher.Flux;

/**
 * Interface de repositório para operações relacionadas a clientes.
 * Esta interface segue o padrão Repository e define o contrato para acesso a dados de clientes.
 * A implementação desta interface ficará na camada de infraestrutura.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ClienteRepository {
    
    /**
     * Busca todos os clientes disponíveis.
     * 
     * @return Um Flux contendo todos os clientes
     */
    Flux<ClienteEntity> findAll();
}
