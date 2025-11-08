package com.digio.compras.analisecomprasapi.adapter.exception;

import com.digio.compras.analisecomprasapi.domain.exception.ExternalApiException;
import com.digio.compras.analisecomprasapi.domain.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler global de exceções para a aplicação.
 * Captura exceções lançadas pelos controllers e retorna respostas HTTP adequadas.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de recurso não encontrado.
     * 
     * @param ex A exceção capturada
     * @return ResponseEntity com status 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Recurso não encontrado: {}", ex.getMessage());
        return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "Recurso não encontrado",
            ex.getMessage()
        );
    }

    /**
     * Trata exceções de API externa.
     * 
     * @param ex A exceção capturada
     * @return ResponseEntity com status 502
     */
    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<Map<String, Object>> handleExternalApiException(ExternalApiException ex) {
        log.error("Erro na comunicação com API externa: {}", ex.getMessage(), ex);
        return buildErrorResponse(
            HttpStatus.BAD_GATEWAY,
            "Erro na comunicação com serviço externo",
            ex.getMessage()
        );
    }

    /**
     * Trata exceções de validação.
     * 
     * @param ex A exceção capturada
     * @return ResponseEntity com status 400
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(WebExchangeBindException ex) {
        log.warn("Erro de validação: {}", ex.getMessage());
        
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.append(error.getField())
                  .append(": ")
                  .append(error.getDefaultMessage())
                  .append("; ")
        );
        
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Erro de validação",
            errors.toString()
        );
    }

    /**
     * Trata exceções de argumento ilegal.
     * 
     * @param ex A exceção capturada
     * @return ResponseEntity com status 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Argumento inválido",
            ex.getMessage()
        );
    }

    /**
     * Trata exceções genéricas não capturadas.
     * 
     * @param ex A exceção capturada
     * @return ResponseEntity com status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Erro interno no servidor: {}", ex.getMessage(), ex);
        return buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro interno do servidor",
            "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."
        );
    }

    /**
     * Constrói a resposta de erro padronizada.
     * 
     * @param status O status HTTP
     * @param error O tipo de erro
     * @param message A mensagem de erro
     * @return ResponseEntity com o mapa de erro
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        
        return ResponseEntity.status(status).body(body);
    }
}
