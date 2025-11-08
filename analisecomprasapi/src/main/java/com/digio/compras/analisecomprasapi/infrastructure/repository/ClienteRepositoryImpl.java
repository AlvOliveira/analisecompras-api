package com.digio.compras.analisecomprasapi.infrastructure.repository;

import com.digio.compras.analisecomprasapi.domain.entity.ClienteEntity;
import com.digio.compras.analisecomprasapi.domain.repository.ClienteRepository;
import com.digio.compras.analisecomprasapi.infrastructure.client.dto.ClienteApiDto;
import com.digio.compras.analisecomprasapi.infrastructure.mapper.ClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementação do repositório de clientes que consome dados de uma API externa.
 * Esta classe pertence à camada de infraestrutura e implementa a interface definida no domínio.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    
    private static final Logger log = LoggerFactory.getLogger(ClienteRepositoryImpl.class);
    
    private final WebClient webClient;
    private final String clientesUrl;

    /**
     * Construtor que injeta o WebClient e a URL da API.
     * 
     * @param webClientBuilder Builder para criar o WebClient
     * @param clientesUrl URL da API de clientes
     */
    public ClienteRepositoryImpl(
            WebClient.Builder webClientBuilder,
            @Value("${api.clientes.url}") String clientesUrl) {
        this.webClient = webClientBuilder.build();
        this.clientesUrl = clientesUrl;
    }

    /**
     * {@inheritDoc}
     * 
     * Busca os clientes da API externa de forma reativa e converte para entidades de domínio.
     */
    @Override
    public Flux<ClienteEntity> findAll() {
        log.info("Buscando clientes da API: {}", clientesUrl);
        
        return webClient
            .get()
            .uri(clientesUrl)
            .retrieve()
            .bodyToFlux(ClienteApiDto.class)
            .map(ClienteMapper::toDomain)
            .doOnError(error -> log.error("Erro ao buscar clientes da API", error))
            .doOnComplete(() -> log.info("Clientes recuperados com sucesso da API"))
            .onErrorResume(error -> {
                log.error("Erro na comunicação com a API de clientes", error);
                return Mono.error(new com.digio.compras.analisecomprasapi.domain.exception.ExternalApiException(
                    "Falha ao buscar clientes da API externa", error));
            });
    }
}
