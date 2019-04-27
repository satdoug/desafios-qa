package reports;

import dtos.*;
import io.qameta.allure.Step;
import org.awaitility.Awaitility;
import utilities.RequestParams;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class ReportsTestStep {

    private static class SingletonHolder {
        public static final ReportsTestStep instance = new ReportsTestStep();
    }

    public static ReportsTestStep getInstance() {
        return SingletonHolder.instance;
    }

    @Step
    public ResultBatchNumberDTO createReport(ReportRequestDTO request) {
        return RequestParams.getInstance().getRequestParams()
                .header(CONTENT_TYPE, "application/json")
                .body(request)
            .when()
                .post("/relatorios")
                .prettyPeek()
            .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultBatchNumberDTO.class);
    }

    @Step
    public ResultReportValidationDTO awaitFinishReportValidation(UUID reportId) {
        AtomicReference<ResultReportValidationDTO> validationResult = new AtomicReference<>();
        Awaitility.pollInSameThread();
        Awaitility.await()
                .atMost(5, MINUTES)
                .pollInterval(5, SECONDS)
                .until(() -> {
                    validationResult.set(getReportValidation(reportId));
                    ValidationResultDTO result = validationResult.get().getResult();
                    return result.getStatus().equals("CONCLUIDO")
                            || result.getResultado() != null;
                });
        return validationResult.get();
    }

    private ResultReportValidationDTO getReportValidation(UUID reportId) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
        .when()
                .get("/relatorios/{reportId}/validacoes")
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultReportValidationDTO.class);
    }

    public ResultBatchNumberDTO manualApproval(UUID reportId, ManualApprovalRequestDTO request) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
                .header(CONTENT_TYPE, "application/json")
                .body(request)
        .when()
                .post("/relatorios/validar/{reportId}")
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultBatchNumberDTO.class);
    }
}
