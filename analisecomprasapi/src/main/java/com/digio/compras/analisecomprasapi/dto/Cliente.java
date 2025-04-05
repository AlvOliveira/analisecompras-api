package com.digio.compras.analisecomprasapi.dto;

import java.util.List;

/**
 *
 * @author Alvaro
 */
public class Cliente {

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

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    private String nome;
    private String cpf;
    private List<Compra> compras;

}