package com.digio.compras.analisecomprasapi.adapter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO de resposta para informações de compras.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Schema(description = "Resposta contendo informações detalhadas de uma compra")
public class CompraResponseDto {
    
    @Schema(description = "Nome do cliente", example = "João Silva")
    private String nomeCliente;
    
    @Schema(description = "CPF do cliente", example = "123.456.789-00")
    private String cpf;
    
    @Schema(description = "Lista de itens da compra")
    private List<DetalheCompraDto> itens;
    
    @Schema(description = "Valor total da compra", example = "450.00")
    private BigDecimal valorTotal;

    public CompraResponseDto() {
    }

    public CompraResponseDto(String nomeCliente, String cpf, List<DetalheCompraDto> itens, BigDecimal valorTotal) {
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

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

    public List<DetalheCompraDto> getItens() {
        return itens;
    }

    public void setItens(List<DetalheCompraDto> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * DTO representando os detalhes de um item da compra.
     */
    @Schema(description = "Detalhes de um item específico da compra")
    public static class DetalheCompraDto {
        
        @Schema(description = "Tipo do vinho", example = "Tinto")
        private String tipo;
        
        @Schema(description = "Safra do vinho", example = "2018")
        private String safra;
        
        @Schema(description = "Preço unitário", example = "75.00")
        private BigDecimal precoUnitario;
        
        @Schema(description = "Quantidade comprada", example = "3")
        private int quantidade;
        
        @Schema(description = "Ano da compra", example = "2020")
        private int anoCompra;
        
        @Schema(description = "Subtotal do item", example = "225.00")
        private BigDecimal subtotal;

        public DetalheCompraDto() {
        }

        public DetalheCompraDto(String tipo, String safra, BigDecimal precoUnitario, 
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

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getSafra() {
            return safra;
        }

        public void setSafra(String safra) {
            this.safra = safra;
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(BigDecimal precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public int getAnoCompra() {
            return anoCompra;
        }

        public void setAnoCompra(int anoCompra) {
            this.anoCompra = anoCompra;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}
