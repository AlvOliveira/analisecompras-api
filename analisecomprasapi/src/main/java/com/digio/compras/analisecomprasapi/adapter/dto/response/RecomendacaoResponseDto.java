package com.digio.compras.analisecomprasapi.adapter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de resposta para recomendações de vinho.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Schema(description = "Resposta contendo recomendação de vinho para cliente")
public class RecomendacaoResponseDto {
    
    @Schema(description = "Nome do cliente", example = "Pedro Alves")
    private String nome;
    
    @Schema(description = "CPF do cliente", example = "111.222.333-44")
    private String cpf;
    
    @Schema(description = "Tipo de vinho recomendado", example = "Tinto")
    private String tipoRecomendado;
    
    @Schema(description = "Quantidade de compras deste tipo", example = "8")
    private int quantidadeComprasDoTipo;

    public RecomendacaoResponseDto() {
    }

    public RecomendacaoResponseDto(String nome, String cpf, String tipoRecomendado, int quantidadeComprasDoTipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoRecomendado = tipoRecomendado;
        this.quantidadeComprasDoTipo = quantidadeComprasDoTipo;
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
}
