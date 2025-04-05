package com.digio.compras.analisecomprasapi.model;

import com.digio.compras.analisecomprasapi.interfaces.ICommandResult;

/**
 *
 * @author Alvaro
 */
public class CommandResultDefault implements ICommandResult {
    private boolean success;
    private String message;
    private Object data;

    // Construtor padrão
    public CommandResultDefault() {
    }

    // Construtor com sucesso e mensagem
    public CommandResultDefault(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Construtor com sucesso, mensagem e dados
    public CommandResultDefault(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters e Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
