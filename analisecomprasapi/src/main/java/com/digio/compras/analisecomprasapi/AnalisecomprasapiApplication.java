package com.digio.compras.analisecomprasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação de Análise de Compras de Vinhos.
 * 
 * <p>Esta aplicação é um microsserviço desenvolvido com Spring Boot WebFlux
 * que fornece endpoints REST para análise de compras de vinhos.</p>
 * 
 * <p>Principais funcionalidades:</p>
 * <ul>
 *   <li>Listagem de compras ordenadas por valor</li>
 *   <li>Busca da maior compra por ano</li>
 *   <li>Identificação dos clientes mais fiéis</li>
 *   <li>Recomendação de vinhos baseada no histórico de compras</li>
 * </ul>
 * 
 * <p>A aplicação segue os princípios de Clean Architecture e SOLID,
 * com separação clara de responsabilidades em camadas:</p>
 * <ul>
 *   <li>Domain: Entidades e interfaces de repositório</li>
 *   <li>Application: Casos de uso e lógica de negócio</li>
 *   <li>Infrastructure: Implementações de repositórios e integrações externas</li>
 *   <li>Adapter: Controllers, DTOs e mapeadores</li>
 * </ul>
 * 
 * @author Alvaro Oliveira
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
public class AnalisecomprasapiApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 * 
	 * @param args Argumentos de linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnalisecomprasapiApplication.class, args);
	}
}
