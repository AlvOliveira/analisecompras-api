package com.digio.compras.analisecomprasapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI/Swagger para documentação da API.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura as informações do OpenAPI.
     * 
     * @return OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        String description = "Microsserviço para análise de compras de vinhos.\n\n" +
                "Esta API fornece endpoints para:\n" +
                "- Listar compras ordenadas por valor\n" +
                "- Buscar maior compra por ano\n" +
                "- Identificar clientes mais fiéis\n" +
                "- Recomendar vinhos baseado no histórico de compras\n\n" +
                "A API consome dados de fontes externas e processa as informações " +
                "para fornecer insights sobre as compras de vinhos.";
        
        return new OpenAPI()
            .info(new Info()
                .title("API de Análise de Compras de Vinhos")
                .version("1.0.0")
                .description(description)
                .contact(new Contact()
                    .name("Alvaro Oliveira")
                    .email("contato@example.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT"))
            );
    }
}
