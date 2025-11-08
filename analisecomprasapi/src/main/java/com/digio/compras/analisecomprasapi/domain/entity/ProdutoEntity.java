package com.digio.compras.analisecomprasapi.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidade de domínio representando um Produto (Vinho).
 * Esta classe encapsula as regras de negócio relacionadas a um produto.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProdutoEntity {
    
    private final String codigo;
    private final String tipoVinho;
    private final String safra;
    private final BigDecimal preco;
    private final int anoCompra;

    /**
     * Construtor da entidade Produto.
     * 
     * @param codigo O código único do produto
     * @param tipoVinho O tipo de vinho
     * @param safra A safra do vinho
     * @param preco O preço do produto
     * @param anoCompra O ano da compra
     * @throws IllegalArgumentException se algum parâmetro for nulo ou inválido
     */
    public ProdutoEntity(String codigo, String tipoVinho, String safra, BigDecimal preco, int anoCompra) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do produto não pode ser nulo ou vazio");
        }
        if (tipoVinho == null || tipoVinho.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de vinho não pode ser nulo ou vazio");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser nulo ou negativo");
        }
        if (anoCompra < 1900 || anoCompra > 2100) {
            throw new IllegalArgumentException("Ano da compra inválido");
        }
        
        this.codigo = codigo;
        this.tipoVinho = tipoVinho;
        this.safra = safra;
        this.preco = preco;
        this.anoCompra = anoCompra;
    }

    /**
     * Obtém o código do produto.
     * 
     * @return O código do produto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtém o tipo de vinho.
     * 
     * @return O tipo de vinho
     */
    public String getTipoVinho() {
        return tipoVinho;
    }

    /**
     * Obtém a safra do vinho.
     * 
     * @return A safra do vinho
     */
    public String getSafra() {
        return safra;
    }

    /**
     * Obtém o preço do produto.
     * 
     * @return O preço do produto
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Obtém o ano da compra.
     * 
     * @return O ano da compra
     */
    public int getAnoCompra() {
        return anoCompra;
    }

    /**
     * Calcula o valor total baseado na quantidade.
     * 
     * @param quantidade A quantidade de produtos
     * @return O valor total (preço * quantidade)
     */
    public BigDecimal calcularValorTotal(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "codigo='" + codigo + '\'' +
                ", tipoVinho='" + tipoVinho + '\'' +
                ", safra='" + safra + '\'' +
                ", preco=" + preco +
                ", anoCompra=" + anoCompra +
                '}';
    }
}
