package com.digio.compras.analisecomprasapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * Configuração do WebClient para comunicação com APIs externas.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class WebClientConfig {

    /**
     * Configura o WebClient.Builder com timeouts e configurações otimizadas.
     * 
     * @return WebClient.Builder configurado
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        HttpClient httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(30))
            .keepAlive(true);
        
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
