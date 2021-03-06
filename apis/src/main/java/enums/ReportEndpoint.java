package enums;

public enum ReportEndpoint {
    REPORT_VALIDATION("/relatorios/{reportId}/validacoes"),
    REPORT_BASIC("/relatorios/{reportId}"),
    REPORT_DATA("/relatorios/{reportId}/dados"),
    REPORT_QUERY("/relatorios/{reportId}/consultas"),
    REPORT_PARAMETERS("/relatorios/{reportId}/parametros"),
    REPORT_ALL("/relatorios"),
    PEOPLE_ALL("/pessoas"),
    PEOPLE_DETAILS("/pessoas/{documentNumber}"),
    COMPANIES_ALL("/empresas"),
    COMPANIES_DETAILS("/empresas/{documentNumber}"),
    MATRIX_ALL("/matrizes"),
    MATRIX_DETAILS("/matrizes/{matrixName}"),
    USER_DETAILS("/usuario"),
    MANUAL_APPROVAL("/relatorios/validar/{reportId}");

    private final String reportEndpoint;

    ReportEndpoint(String reportEndpoint) {
        this.reportEndpoint = reportEndpoint;
    }

    public String getReportEndpoint() {
        return this.reportEndpoint;
    }
}
