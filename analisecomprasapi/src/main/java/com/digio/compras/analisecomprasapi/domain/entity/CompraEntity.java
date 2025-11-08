package com.digio.compras.analisecomprasapi.domain.entity;

import java.util.Objects;

/**
 * Entidade de domínio representando uma Compra.
 * Esta classe encapsula as regras de negócio relacionadas a uma compra.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompraEntity {
    
    private final String codigoProduto;
    private final int quantidade;

    /**
     * Construtor da entidade Compra.
     * 
     * @param codigoProduto O código do produto comprado
     * @param quantidade A quantidade comprada
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public CompraEntity(String codigoProduto, int quantidade) {
        if (codigoProduto == null || codigoProduto.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do produto não pode ser nulo ou vazio");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    /**
     * Obtém o código do produto.
     * 
     * @return O código do produto
     */
    public String getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * Obtém a quantidade comprada.
     * 
     * @return A quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraEntity that = (CompraEntity) o;
        return quantidade == that.quantidade && 
               Objects.equals(codigoProduto, that.codigoProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoProduto, quantidade);
    }

    @Override
    public String toString() {
        return "CompraEntity{" +
                "codigoProduto='" + codigoProduto + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
