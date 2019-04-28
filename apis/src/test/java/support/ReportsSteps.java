package support;

import dtos.*;
import enums.ReportEndpoint;
import enums.ReportMatrix;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.awaitility.Awaitility;
import utilities.RequestParams;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static enums.ReportEndpoint.MATRIX_ALL;
import static enums.ReportEndpoint.MATRIX_DETAILS;
import static enums.ReportEndpoint.USER_DETAILS;
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
    public ResultDTO awaitFinishReportProcessing(ReportEndpoint reportEndpoint, UUID reportId) {
        AtomicReference<ResultDTO> queryResult = new AtomicReference<>();
        Awaitility.pollInSameThread();
        Awaitility.await()
                .atMost(5, MINUTES)
                .pollInterval(5, SECONDS)
                .until(() -> {
                    queryResult.set(getReportProcessing(reportEndpoint, reportId));
                    ResultDetailsDTO result = queryResult.get().getResult();
                    return result.getStatus().equals("CONCLUIDO")
                            || result.getResultado() != null;
                });
        return queryResult.get();
    }

    @Step
    public ResultDTO awaitFinishReportQuery(UUID reportId) {
        AtomicReference<ResultDTO> queryResult = new AtomicReference<>();
        Awaitility.pollInSameThread();
        Awaitility.await()
                .atMost(5, MINUTES)
                .pollInterval(5, SECONDS)
                .until(() -> {
                    queryResult.set(getReportProcessing(ReportEndpoint.REPORT_QUERY, reportId));
                    ResultDetailsDTO result = queryResult.get().getResult();
                    return result.getStatus_protocolo().equals("CONCLUIDO");
                });
        return queryResult.get();
    }

    @Step
    public ResultDTO getFilteredReport(ReportEndpoint endpoint, Map<String, Object> formParams) {
        RestAssuredConfig config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
                .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC));

        return RequestParams.getInstance().getRequestParams()
                .config(config)
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParams(formParams)
        .when()
                .get(endpoint.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    @Step
    public ResultDTO getReportDetails(ReportEndpoint endpoint, String documentNumber) {
        RestAssuredConfig config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
                .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC));
        Map<String, Integer> formParams = new HashMap<>();
        formParams.put("page", 1);
        formParams.put("rows", 100);

        return RequestParams.getInstance().getRequestParams()
                .config(config)
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParams(formParams)
                .pathParam("documentNumber", documentNumber)
        .when()
                .get(endpoint.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    @Step
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

    private ResultDTO getReportProcessing(ReportEndpoint reportEndpoint, UUID reportId) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", reportId)
        .when()
                .get(reportEndpoint.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    @Step
    public ResultDTO getMatrixList() {
        return RequestParams.getInstance().getRequestParams().when()
                .get(MATRIX_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }

    @Step
    public MatrixResultDTO getMatrixDetails(ReportMatrix matrix) {
        return RequestParams.getInstance().getRequestParams()
                .pathParam("matrixName", matrix)
        .when()
                .get(MATRIX_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(MatrixResultDTO.class);
    }

    @Step
    public ResultDTO getUserDetails() {
        return RequestParams.getInstance().getRequestParams().when()
                .get(USER_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultDTO.class);
    }
}
