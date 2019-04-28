package dtos;

import enums.ReportMatrix;

public class ReportRequestDTO {

    private ReportMatrix matriz;
    private RequestParametersDTO parametros;

    public ReportMatrix getMatriz() {
        return matriz;
    }

    public void setMatriz(ReportMatrix matriz) {
        this.matriz = matriz;
    }

    public RequestParametersDTO getParametros() {
        return parametros;
    }

    public void setParametros(RequestParametersDTO parametros) {
        this.parametros = parametros;
    }
}
