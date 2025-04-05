package com.digio.compras.analisecomprasapi.dto;

import java.math.BigDecimal;

/**
 *
 * @author Alvaro
 */
public class Produto {

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String id) {
        this.codigo = id;
    }

    public String getTipo_vinho() {
        return tipo_vinho;
    }

    public void setTipo_vinho(String tipo_vinho) {
        this.tipo_vinho = tipo_vinho;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String marca) {
        this.safra = marca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public int getAno_compra() {
        return ano_compra;
    }

    public void setAno_compra(int ano_compra) {
        this.ano_compra = ano_compra;
    }

    private String codigo;
    private String tipo_vinho;
    private String safra;
    private BigDecimal preco;
    private int ano_compra;
}
