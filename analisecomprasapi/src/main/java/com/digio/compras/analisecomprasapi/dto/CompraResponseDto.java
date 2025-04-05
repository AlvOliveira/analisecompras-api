package com.digio.compras.analisecomprasapi.dto;

/**
 *
 * @author Alvaro
 */
public class CompraResponseDto {

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
    private String codigo;
    private int quantidade;
}
