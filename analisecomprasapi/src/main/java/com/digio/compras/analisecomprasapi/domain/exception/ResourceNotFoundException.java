package com.digio.compras.analisecomprasapi.domain.exception;

/**
 * Exceção lançada quando um recurso não é encontrado.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Construtor com mensagem.
     * 
     * @param message A mensagem de erro
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Construtor com mensagem e causa.
     * 
     * @param message A mensagem de erro
     * @param cause A causa da exceção
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
