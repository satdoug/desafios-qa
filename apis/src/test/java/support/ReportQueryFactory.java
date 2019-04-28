package support;

import dtos.ResultDTO;
import enums.ReportQuery;
import utilities.RequestParams;

import java.util.UUID;

import static enums.ReportQuery.REPORT_DATA;
import static enums.ReportQuery.REPORT_VALIDATION;
import static java.net.HttpURLConnection.HTTP_OK;

public class ReportQueryFactory {

    private static class SingletonHolder {
        public static final ReportQueryFactory instance = new ReportQueryFactory();
    }

    public static ReportQueryFactory getInstance() {
        return SingletonHolder.instance;
    }

    public ResultDTO getReportQuery(ReportQuery reportQuery, UUID reportId) {
        if (reportQuery.equals(REPORT_VALIDATION)) {
            return getReportValidation(reportId);
        }
        if (reportQuery.equals(REPORT_DATA)) {
            return getReportData(reportId);
        }
        return null;
    }

    private ResultDTO getReportData(UUID reportId) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
        .when()
                .get("/relatorios/{reportId}")
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    private ResultDTO getReportValidation(UUID reportId) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
        .when()
                .get("/relatorios/{reportId}/validacoes")
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }
}
