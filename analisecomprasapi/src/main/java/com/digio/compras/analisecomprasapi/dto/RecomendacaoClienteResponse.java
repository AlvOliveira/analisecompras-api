package com.digio.compras.analisecomprasapi.dto;

/**
 *
 * @author Alvaro
 */
public class RecomendacaoClienteResponse {

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

    public String getTipoRecomendado() {
        return tipoRecomendado;
    }

    public void setTipoRecomendado(String tipoRecomendado) {
        this.tipoRecomendado = tipoRecomendado;
    }

    public int getQuantidadeComprasDoTipo() {
        return quantidadeComprasDoTipo;
    }

    public void setQuantidadeComprasDoTipo(int quantidadeComprasDoTipo) {
        this.quantidadeComprasDoTipo = quantidadeComprasDoTipo;
    }
    private String nome;
    private String cpf;
    private String tipoRecomendado;
    private int quantidadeComprasDoTipo;

}