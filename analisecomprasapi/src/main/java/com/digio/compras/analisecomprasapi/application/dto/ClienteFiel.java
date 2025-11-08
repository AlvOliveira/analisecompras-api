package com.digio.compras.analisecomprasapi.application.dto;

import java.math.BigDecimal;

/**
 * DTO da camada de aplicação representando um cliente fiel.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClienteFiel {
    
    private final String nome;
    private final String cpf;
    private final int quantidadeCompras;
    private final BigDecimal valorTotalGasto;

    public ClienteFiel(String nome, String cpf, int quantidadeCompras, BigDecimal valorTotalGasto) {
        this.nome = nome;
        this.cpf = cpf;
        this.quantidadeCompras = quantidadeCompras;
        this.valorTotalGasto = valorTotalGasto;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public BigDecimal getValorTotalGasto() {
        return valorTotalGasto;
    }
}
