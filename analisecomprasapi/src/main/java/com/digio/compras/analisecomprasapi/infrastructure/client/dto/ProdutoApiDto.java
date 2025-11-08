package com.digio.compras.analisecomprasapi.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * DTO para deserialização da resposta da API externa de produtos.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProdutoApiDto {
    
    private String codigo;
    
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    
    private String safra;
    
    private BigDecimal preco;
    
    @JsonProperty("ano_compra")
    private int anoCompra;

    public ProdutoApiDto() {
    }

    public ProdutoApiDto(String codigo, String tipoVinho, String safra, BigDecimal preco, int anoCompra) {
        this.codigo = codigo;
        this.tipoVinho = tipoVinho;
        this.safra = safra;
        this.preco = preco;
        this.anoCompra = anoCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoVinho() {
        return tipoVinho;
    }

    public void setTipoVinho(String tipoVinho) {
        this.tipoVinho = tipoVinho;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getAnoCompra() {
        return anoCompra;
    }

    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }
}
