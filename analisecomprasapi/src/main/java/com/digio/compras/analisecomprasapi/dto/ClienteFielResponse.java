package com.digio.compras.analisecomprasapi.dto;

import java.math.BigDecimal;

/**
 *
 * @author Alvaro
 */
public class ClienteFielResponse {

    public ClienteFielResponse()
    {
    }
    
    public ClienteFielResponse(String nome, String cpf, int qtdCompras, BigDecimal valorTotalGasto){
        this.nome = nome;
        this.cpf = cpf;
        this.quantidadeCompras = qtdCompras;
        this.valorTotalGasto = valorTotalGasto;
    }
            
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public BigDecimal getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(BigDecimal valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }
    private String nome;
    private String cpf;
    private int quantidadeCompras;
    private BigDecimal valorTotalGasto;
}
