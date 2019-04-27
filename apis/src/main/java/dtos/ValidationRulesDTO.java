package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.ValidationResult;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ValidationRulesDTO {
    private String regra;
    private String nome;
    private String descricao;
    private String mensagem;
    private ValidationResult resultado;

    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ValidationResult getResultado() {
        return resultado;
    }

    public void setResultado(ValidationResult resultado) {
        this.resultado = resultado;
    }
}
