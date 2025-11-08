package com.digio.compras.analisecomprasapi.domain.entity;

import java.util.List;
import java.util.Objects;

/**
 * Entidade de domínio representando um Cliente.
 * Esta classe encapsula as regras de negócio relacionadas a um cliente.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClienteEntity {
    
    private final String nome;
    private final String cpf;
    private final List<CompraEntity> compras;

    /**
     * Construtor da entidade Cliente.
     * 
     * @param nome O nome completo do cliente
     * @param cpf O CPF do cliente
     * @param compras A lista de compras realizadas pelo cliente
     * @throws IllegalArgumentException se algum parâmetro for nulo ou inválido
     */
    public ClienteEntity(String nome, String cpf, List<CompraEntity> compras) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do cliente não pode ser nulo ou vazio");
        }
        if (compras == null) {
            throw new IllegalArgumentException("Lista de compras não pode ser nula");
        }
        
        this.nome = nome;
        this.cpf = cpf;
        this.compras = List.copyOf(compras);
    }

    /**
     * Obtém o nome do cliente.
     * 
     * @return O nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o CPF do cliente.
     * 
     * @return O CPF do cliente
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Obtém a lista de compras do cliente.
     * 
     * @return Uma lista imutável de compras
     */
    public List<CompraEntity> getCompras() {
        return compras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", totalCompras=" + compras.size() +
                '}';
    }
}
