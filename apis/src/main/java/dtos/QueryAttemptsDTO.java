package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class QueryAttemptsDTO {
    private Double duracao_tentativa;
    private String hora_fim_tentativa;
    private String hora_inicio_tentativa;
    private String msg_erro_tentativa;
    private String nome_fonte;
    private String status_fonte;
    private String status_tentativa;
    private String tipo_erro_tentativa;

    public Double getDuracao_tentativa() {
        return duracao_tentativa;
    }

    public void setDuracao_tentativa(Double duracao_tentativa) {
        this.duracao_tentativa = duracao_tentativa;
    }

    public String getHora_fim_tentativa() {
        return hora_fim_tentativa;
    }

    public void setHora_fim_tentativa(String hora_fim_tentativa) {
        this.hora_fim_tentativa = hora_fim_tentativa;
    }

    public String getHora_inicio_tentativa() {
        return hora_inicio_tentativa;
    }

    public void setHora_inicio_tentativa(String hora_inicio_tentativa) {
        this.hora_inicio_tentativa = hora_inicio_tentativa;
    }

    public String getMsg_erro_tentativa() {
        return msg_erro_tentativa;
    }

    public void setMsg_erro_tentativa(String msg_erro_tentativa) {
        this.msg_erro_tentativa = msg_erro_tentativa;
    }

    public String getNome_fonte() {
        return nome_fonte;
    }

    public void setNome_fonte(String nome_fonte) {
        this.nome_fonte = nome_fonte;
    }

    public String getStatus_fonte() {
        return status_fonte;
    }

    public void setStatus_fonte(String status_fonte) {
        this.status_fonte = status_fonte;
    }

    public String getStatus_tentativa() {
        return status_tentativa;
    }

    public void setStatus_tentativa(String status_tentativa) {
        this.status_tentativa = status_tentativa;
    }

    public String getTipo_erro_tentativa() {
        return tipo_erro_tentativa;
    }

    public void setTipo_erro_tentativa(String tipo_erro_tentativa) {
        this.tipo_erro_tentativa = tipo_erro_tentativa;
    }
}
