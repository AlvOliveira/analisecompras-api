package com.digio.compras.analisecomprasapi.infrastructure.repository;

import com.digio.compras.analisecomprasapi.domain.entity.ProdutoEntity;
import com.digio.compras.analisecomprasapi.domain.repository.ProdutoRepository;
import com.digio.compras.analisecomprasapi.infrastructure.client.dto.ProdutoApiDto;
import com.digio.compras.analisecomprasapi.infrastructure.mapper.ProdutoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementação do repositório de produtos que consome dados de uma API externa.
 * Esta classe pertence à camada de infraestrutura e implementa a interface definida no domínio.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    
    private static final Logger log = LoggerFactory.getLogger(ProdutoRepositoryImpl.class);
    
    private final WebClient webClient;
    private final String produtosUrl;

    /**
     * Construtor que injeta o WebClient e a URL da API.
     * 
     * @param webClientBuilder Builder para criar o WebClient
     * @param produtosUrl URL da API de produtos
     */
    public ProdutoRepositoryImpl(
            WebClient.Builder webClientBuilder,
            @Value("${api.produtos.url}") String produtosUrl) {
        this.webClient = webClientBuilder.build();
        this.produtosUrl = produtosUrl;
    }

    /**
     * {@inheritDoc}
     * 
     * Busca os produtos da API externa de forma reativa e converte para entidades de domínio.
     */
    @Override
    public Flux<ProdutoEntity> findAll() {
        log.info("Buscando produtos da API: {}", produtosUrl);
        
        return webClient
            .get()
            .uri(produtosUrl)
            .retrieve()
            .bodyToFlux(ProdutoApiDto.class)
            .map(ProdutoMapper::toDomain)
            .doOnError(error -> log.error("Erro ao buscar produtos da API", error))
            .doOnComplete(() -> log.info("Produtos recuperados com sucesso da API"))
            .onErrorResume(error -> {
                log.error("Erro na comunicação com a API de produtos", error);
                return Mono.error(new com.digio.compras.analisecomprasapi.domain.exception.ExternalApiException(
                    "Falha ao buscar produtos da API externa", error));
            });
    }
}
