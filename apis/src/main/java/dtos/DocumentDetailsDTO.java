package dtos;

import java.util.List;

public class DocumentDetailsDTO {

    private String cpf;
    private String cnpj;
    private String nome;
    private String atualizado_em;
    private List<ReportItemDTO> relatorios;

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

    public List<ReportItemDTO> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<ReportItemDTO> relatorios) {
        this.relatorios = relatorios;
    }
}
