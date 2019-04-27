package dtos;

public class ReportParametersDTO {

    private String cpf_data_de_nascimento;
    private String cpf_nome;
    private String cpf_numero;

    public String getCpf_data_de_nascimento() {
        return cpf_data_de_nascimento;
    }

    public void setCpf_data_de_nascimento(String cpf_data_de_nascimento) {
        this.cpf_data_de_nascimento = cpf_data_de_nascimento;
    }

    public String getCpf_nome() {
        return cpf_nome;
    }

    public void setCpf_nome(String cpf_nome) {
        this.cpf_nome = cpf_nome;
    }

    public String getCpf_numero() {
        return cpf_numero;
    }

    public void setCpf_numero(String cpf_numero) {
        this.cpf_numero = cpf_numero;
    }
}
