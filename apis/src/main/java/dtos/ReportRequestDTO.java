package dtos;

public class ReportRequestDTO {

    private String matriz;
    private ReportParametersDTO parametros;

    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    public ReportParametersDTO getParametros() {
        return parametros;
    }

    public void setParametros(ReportParametersDTO parametros) {
        this.parametros = parametros;
    }
}
