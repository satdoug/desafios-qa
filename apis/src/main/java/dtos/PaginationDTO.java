package dtos;

public class PaginationDTO {

    private Integer atual;
    private Integer linhas;
    private Integer total;

    public Integer getAtual() {
        return atual;
    }

    public void setAtual(Integer atual) {
        this.atual = atual;
    }

    public Integer getLinhas() {
        return linhas;
    }

    public void setLinhas(Integer linhas) {
        this.linhas = linhas;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
