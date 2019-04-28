package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.ReportMatrix;
import enums.ValidationResult;

import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ResultDetailsDTO {

    private UUID numero;
    private String status;
    private ReportMatrix nome;
    private String mensagem;
    private ValidationResult resultado;
    private String validado_em;
    private String validado_por;
    private Boolean validado_manualmente;
    private String atualizado_em;
    private String criado_em;
    private String criado_por;
    private List<ValidationRulesDTO> validacoes;
    private Object blacklists;
    private Object certidoes_negativas;
    private Object cpf;
    private Object cnpj;
    private Object divida_ativa;
    private Object processos;
    private Object protestos;
    private ReportMatrix nome_matriz;
    private String status_protocolo;
    private List<QueryDetailsDTO> consultas;
    private ResultParametersDTO parametros;
    private List<Object> itens;
    private PaginationDTO paginacao;
    private String busca;

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

    public ReportMatrix getNome() {
        return nome;
    }

    public void setNome(ReportMatrix nome) {
        this.nome = nome;
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

    public String getValidado_em() {
        return validado_em;
    }

    public void setValidado_em(String validado_em) {
        this.validado_em = validado_em;
    }

    public String getValidado_por() {
        return validado_por;
    }

    public void setValidado_por(String validado_por) {
        this.validado_por = validado_por;
    }

    public Boolean getValidado_manualmente() {
        return validado_manualmente;
    }

    public void setValidado_manualmente(Boolean validado_manualmente) {
        this.validado_manualmente = validado_manualmente;
    }

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public String getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(String criado_em) {
        this.criado_em = criado_em;
    }

    public String getCriado_por() {
        return criado_por;
    }

    public void setCriado_por(String criado_por) {
        this.criado_por = criado_por;
    }

    public List<ValidationRulesDTO> getValidacoes() {
        return validacoes;
    }

    public void setValidacoes(List<ValidationRulesDTO> validacoes) {
        this.validacoes = validacoes;
    }

    public Object getCertidoes_negativas() {
        return certidoes_negativas;
    }

    public void setCertidoes_negativas(Object certidoes_negativas) {
        this.certidoes_negativas = certidoes_negativas;
    }

    public Object getCpf() {
        return cpf;
    }

    public void setCpf(Object cpf) {
        this.cpf = cpf;
    }

    public Object getProcessos() {
        return processos;
    }

    public void setProcessos(Object processos) {
        this.processos = processos;
    }

    public Object getProtestos() {
        return protestos;
    }

    public void setProtestos(Object protestos) {
        this.protestos = protestos;
    }

    public Object getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(Object blacklists) {
        this.blacklists = blacklists;
    }

    public Object getCnpj() {
        return cnpj;
    }

    public void setCnpj(Object cnpj) {
        this.cnpj = cnpj;
    }

    public Object getDivida_ativa() {
        return divida_ativa;
    }

    public void setDivida_ativa(Object divida_ativa) {
        this.divida_ativa = divida_ativa;
    }

    public ReportMatrix getNome_matriz() {
        return nome_matriz;
    }

    public void setNome_matriz(ReportMatrix nome_matriz) {
        this.nome_matriz = nome_matriz;
    }

    public String getStatus_protocolo() {
        return status_protocolo;
    }

    public void setStatus_protocolo(String status_protocolo) {
        this.status_protocolo = status_protocolo;
    }

    public List<QueryDetailsDTO> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<QueryDetailsDTO> consultas) {
        this.consultas = consultas;
    }

    public ResultParametersDTO getParametros() {
        return parametros;
    }

    public void setParametros(ResultParametersDTO parametros) {
        this.parametros = parametros;
    }

    public List<Object> getItens() {
        return itens;
    }

    public void setItens(List<Object> itens) {
        this.itens = itens;
    }

    public PaginationDTO getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(PaginationDTO paginacao) {
        this.paginacao = paginacao;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}
