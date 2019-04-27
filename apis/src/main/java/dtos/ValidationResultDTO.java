package dtos;

import enums.ReportMatrix;
import enums.ValidationResult;

import java.util.List;
import java.util.UUID;

public class ValidationResultDTO {

    private String atualizado_em;
    private String mensagem;
    private ReportMatrix nome;
    private UUID numero;
    private ValidationResult resultado;
    private String status;
    private List<ValidationRulesDTO> validacoes;
    private String validado_em;
    private Boolean validado_manualmente;
    private String validado_por;

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ReportMatrix getNome() {
        return nome;
    }

    public void setNome(ReportMatrix nome) {
        this.nome = nome;
    }

    public UUID getNumero() {
        return numero;
    }

    public void setNumero(UUID numero) {
        this.numero = numero;
    }

    public ValidationResult getResultado() {
        return resultado;
    }

    public void setResultado(ValidationResult resultado) {
        this.resultado = resultado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ValidationRulesDTO> getValidacoes() {
        return validacoes;
    }

    public void setValidacoes(List<ValidationRulesDTO> validacoes) {
        this.validacoes = validacoes;
    }

    public String getValidado_em() {
        return validado_em;
    }

    public void setValidado_em(String validado_em) {
        this.validado_em = validado_em;
    }

    public Boolean getValidado_manualmente() {
        return validado_manualmente;
    }

    public void setValidado_manualmente(Boolean validado_manualmente) {
        this.validado_manualmente = validado_manualmente;
    }

    public String getValidado_por() {
        return validado_por;
    }

    public void setValidado_por(String validado_por) {
        this.validado_por = validado_por;
    }
}
