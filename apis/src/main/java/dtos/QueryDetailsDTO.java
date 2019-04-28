package dtos;

import java.util.List;

public class QueryDetailsDTO {

    private String nome;
    private List<QueryAttemptsDTO> tentativas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<QueryAttemptsDTO> getTentativas() {
        return tentativas;
    }

    public void setTentativas(List<QueryAttemptsDTO> tentativas) {
        this.tentativas = tentativas;
    }
}
