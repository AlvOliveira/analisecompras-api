package com.digio.compras.analisecomprasapi.infrastructure.client.dto;

import java.util.List;

/**
 * DTO para deserialização da resposta da API externa de clientes.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClienteApiDto {
    
    private String nome;
    private String cpf;
    private List<CompraApiDto> compras;

    public ClienteApiDto() {
    }

    public ClienteApiDto(String nome, String cpf, List<CompraApiDto> compras) {
        this.nome = nome;
        this.cpf = cpf;
        this.compras = compras;
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

    public List<CompraApiDto> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraApiDto> compras) {
        this.compras = compras;
    }
}
