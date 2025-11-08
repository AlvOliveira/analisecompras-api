package com.digio.compras.analisecomprasapi.application.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO da camada de aplicação representando uma compra detalhada.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompraDetalhada {
    
    private final String nomeCliente;
    private final String cpf;
    private final List<ItemCompra> itens;
    private final BigDecimal valorTotal;

    public CompraDetalhada(String nomeCliente, String cpf, List<ItemCompra> itens, BigDecimal valorTotal) {
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * DTO representando um item de compra.
     */
    public static class ItemCompra {
        private final String tipo;
        private final String safra;
        private final BigDecimal precoUnitario;
        private final int quantidade;
        private final int anoCompra;
        private final BigDecimal subtotal;

        public ItemCompra(String tipo, String safra, BigDecimal precoUnitario, 
                         int quantidade, int anoCompra, BigDecimal subtotal) {
            this.tipo = tipo;
            this.safra = safra;
            this.precoUnitario = precoUnitario;
            this.quantidade = quantidade;
            this.anoCompra = anoCompra;
            this.subtotal = subtotal;
        }

        public String getTipo() {
            return tipo;
        }

        public String getSafra() {
            return safra;
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public int getAnoCompra() {
            return anoCompra;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }
    }
}
