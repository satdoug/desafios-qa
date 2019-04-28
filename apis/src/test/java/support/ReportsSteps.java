package support;

import dtos.*;
import enums.ReportQuery;
import io.qameta.allure.Step;
import org.awaitility.Awaitility;
import utilities.RequestParams;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class ReportsSteps {

    private static class SingletonHolder {
        public static final ReportsSteps instance = new ReportsSteps();
    }

    public static ReportsSteps getInstance() {
        return SingletonHolder.instance;
    }

    @Step
    public ResultDTO createReport(ReportRequestDTO request) {
        return RequestParams.getInstance().getRequestParams()
                .header(CONTENT_TYPE, "application/json")
                .body(request)
            .when()
                .post("/relatorios")
                .prettyPeek()
            .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    @Step
    public ResultDTO awaitFinishReportQuery(ReportQuery reportQuery, UUID reportId) {
        AtomicReference<ResultDTO> queryResult = new AtomicReference<>();
        Awaitility.pollInSameThread();
        Awaitility.await()
                .atMost(5, MINUTES)
                .pollInterval(5, SECONDS)
                .until(() -> {
                    queryResult.set(getReportQuery(reportQuery, reportId));
                    ResultDetailsDTO result = queryResult.get().getResult();
                    return result.getStatus().equals("CONCLUIDO")
                            || result.getResultado() != null;
                });
        return queryResult.get();
    }

    private ResultDTO getReportQuery(ReportQuery reportQuery, UUID reportId) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
        .when()
                .get(reportQuery.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    public ResultDTO manualApproval(UUID reportId, ManualApprovalRequestDTO request) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
                .header(CONTENT_TYPE, "application/json")
                .body(request)
        .when()
                .post("/relatorios/validar/{reportId}")
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }
}
