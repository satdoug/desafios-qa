package dtos;

import enums.ReportMatrix;

import java.util.List;

public class MatrixDetailsDTO {

    private String descricao;
    private Integer creditos;
    private List<Object> regras;
    private String tipo;
    private String titulo;
    private ReportMatrix nome;
    private List<ResultParametersDTO> parametros;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public List<Object> getRegras() {
        return regras;
    }

    public void setRegras(List<Object> regras) {
        this.regras = regras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ReportMatrix getNome() {
        return nome;
    }

    public void setNome(ReportMatrix nome) {
        this.nome = nome;
    }

    public List<ResultParametersDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<ResultParametersDTO> parametros) {
        this.parametros = parametros;
    }
}
