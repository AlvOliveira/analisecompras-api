package com.digio.compras.analisecomprasapi.application.dto;

/**
 * DTO da camada de aplicação representando uma recomendação de vinho.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class RecomendacaoVinho {
    
    private final String nome;
    private final String cpf;
    private final String tipoRecomendado;
    private final int quantidadeComprasDoTipo;

    public RecomendacaoVinho(String nome, String cpf, String tipoRecomendado, int quantidadeComprasDoTipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoRecomendado = tipoRecomendado;
        this.quantidadeComprasDoTipo = quantidadeComprasDoTipo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipoRecomendado() {
        return tipoRecomendado;
    }

    public int getQuantidadeComprasDoTipo() {
        return quantidadeComprasDoTipo;
    }
}
