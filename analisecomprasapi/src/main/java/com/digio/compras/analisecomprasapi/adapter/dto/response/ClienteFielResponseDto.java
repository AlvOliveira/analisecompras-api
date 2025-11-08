package com.digio.compras.analisecomprasapi.adapter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * DTO de resposta para clientes fiéis.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Schema(description = "Resposta contendo informações de cliente fiel")
public class ClienteFielResponseDto {
    
    @Schema(description = "Nome do cliente", example = "Maria Santos")
    private String nome;
    
    @Schema(description = "CPF do cliente", example = "987.654.321-00")
    private String cpf;
    
    @Schema(description = "Quantidade de compras realizadas", example = "15")
    private int quantidadeCompras;
    
    @Schema(description = "Valor total gasto", example = "5400.00")
    private BigDecimal valorTotalGasto;

    public ClienteFielResponseDto() {
    }

    public ClienteFielResponseDto(String nome, String cpf, int quantidadeCompras, BigDecimal valorTotalGasto) {
        this.nome = nome;
        this.cpf = cpf;
        this.quantidadeCompras = quantidadeCompras;
        this.valorTotalGasto = valorTotalGasto;
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

    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(int quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public BigDecimal getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(BigDecimal valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }
}
