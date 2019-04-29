package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ResultParametersDTO {

    private String data_de_nascimento;
    private String nome;
    private String cpf;
    private String cnpj;
    private Boolean requerido;
    private String titulo;
    private List<Object> parametros_auxiliares;

    public String getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(String data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Object> getParametros_auxiliares() {
        return parametros_auxiliares;
    }

    public void setParametros_auxiliares(List<Object> parametros_auxiliares) {
        this.parametros_auxiliares = parametros_auxiliares;
    }
}
