package com.digio.compras.analisecomprasapi.domain.repository;

import com.digio.compras.analisecomprasapi.domain.entity.ProdutoEntity;
import reactor.core.publisher.Flux;

/**
 * Interface de repositório para operações relacionadas a produtos.
 * Esta interface segue o padrão Repository e define o contrato para acesso a dados de produtos.
 * A implementação desta interface ficará na camada de infraestrutura.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProdutoRepository {
    
    /**
     * Busca todos os produtos disponíveis.
     * 
     * @return Um Flux contendo todos os produtos
     */
    Flux<ProdutoEntity> findAll();
}
