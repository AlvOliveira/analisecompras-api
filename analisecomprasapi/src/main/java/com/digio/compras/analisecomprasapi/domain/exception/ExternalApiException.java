package com.digio.compras.analisecomprasapi.domain.exception;

/**
 * Exceção lançada quando ocorre um erro na integração com APIs externas.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExternalApiException extends RuntimeException {
    
    /**
     * Construtor com mensagem.
     * 
     * @param message A mensagem de erro
     */
    public ExternalApiException(String message) {
        super(message);
    }
    
    /**
     * Construtor com mensagem e causa.
     * 
     * @param message A mensagem de erro
     * @param cause A causa da exceção
     */
    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
