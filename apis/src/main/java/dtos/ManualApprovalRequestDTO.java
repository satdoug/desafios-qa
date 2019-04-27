package dtos;

public class ManualApprovalRequestDTO {

    private Boolean aprovar;
    private String mensagem;

    public Boolean getAprovar() {
        return aprovar;
    }

    public void setAprovar(Boolean aprovar) {
        this.aprovar = aprovar;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
