package dtos;

import enums.ReportMatrix;

public class ReportRequestDTO {

    private ReportMatrix matriz;
    private ReportParameters parametros;

    public ReportMatrix getMatriz() {
        return matriz;
    }

    public void setMatriz(ReportMatrix matriz) {
        this.matriz = matriz;
    }

    public ReportParameters getParametros() {
        return parametros;
    }

    public void setParametros(ReportParameters parametros) {
        this.parametros = parametros;
    }
}
