package dtos;

import enums.ValidationResult;

import java.util.UUID;

public class ReportItemDTO {
    private UUID numero;
    private String status;
    private String tipo_pessoa;
    private String nome;
    private String atualizado_em;
    private ValidationResult resultado;
    private String mensagem;
    private String numero_documento;

    public UUID getNumero() {
        return numero;
    }

    public void setNumero(UUID numero) {
        this.numero = numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public ValidationResult getResultado() {
        return resultado;
    }

    public void setResultado(ValidationResult resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
}
