package com.digio.compras.analisecomprasapi.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alvaro
 */
public class CompraResponse {

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<DetalheCompra> getItens() {
        return itens;
    }

    public void setItens(List<DetalheCompra> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
       this.valorTotal = valorTotal;
    }
    private String nomeCliente;
    private String cpf;
    private List<DetalheCompra> itens;
    private BigDecimal valorTotal;

    public static final class DetalheCompra {
        private String tipo;
        private String safra;
        private BigDecimal precoUnitario;
        private int quantidade;
        private BigDecimal subtotal;
        private int anoCompra;

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = Objects.requireNonNull(tipo, "Tipo não pode ser nulo");
        }

        public String getSafra() {
            return safra;
        }

        public void setSafra(String safra) {
            this.safra = safra; // Safra pode ser opcional (não requer not null)
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(BigDecimal precoUnitario) {
            this.precoUnitario = Objects.requireNonNull(precoUnitario, "Preço unitário não pode ser nulo")
                .setScale(2, RoundingMode.HALF_UP);
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            if (quantidade <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser positiva");
            }
            this.quantidade = quantidade;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = Objects.requireNonNull(subtotal, "Subtotal não pode ser nulo")
                .setScale(2, RoundingMode.HALF_UP);
        }

        public int getAnoCompra() {
            return anoCompra;
        }

        public void setAnoCompra(int anoCompra) {
            this.anoCompra = anoCompra;
        }
    }
}