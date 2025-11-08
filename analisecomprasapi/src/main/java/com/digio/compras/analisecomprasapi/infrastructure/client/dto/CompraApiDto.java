package com.digio.compras.analisecomprasapi.infrastructure.client.dto;

/**
 * DTO para deserialização da resposta da API externa de compras.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompraApiDto {
    
    private String codigo;
    private int quantidade;

    public CompraApiDto() {
    }

    public CompraApiDto(String codigo, int quantidade) {
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
