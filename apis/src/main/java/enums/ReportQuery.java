package enums;

public enum ReportQuery {
    REPORT_VALIDATION("/relatorios/{reportId}/validacoes"),
    REPORT_BASIC("/relatorios/{reportId}"),
    REPORT_DATA("/relatorios/{reportId}/dados");

    private final String reportEndpoint;

    ReportQuery(String reportEndpoint) {
        this.reportEndpoint = reportEndpoint;
    }

    public String getReportEndpoint() {
        return this.reportEndpoint;
    }
}
