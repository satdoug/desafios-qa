package reports;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import support.ReportsData;
import utilities.ProjectProperties;
import utilities.RequestParams;

import java.util.UUID;

import static enums.ReportEndpoint.*;
import static enums.ReportMatrix.consultaPessoaDefault;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static utilities.ProjectProperties.*;

@Tags({@Tag("all"), @Tag("expectedErrors")})
public class ExpectedErrorsTest {

    private static ReportsData reportsData;

    @BeforeAll
    public static void setUp() {
        reportsData = ReportsData.getInstance();
    }

    @Test
    @Description("Try post report with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void postReportInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .header(CONTENT_TYPE, "application/json")
                .body(reportsData.getValidReportData(consultaPessoaDefault))
        .when()
                .post(REPORT_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try post report with invalid data")
    @Severity(SeverityLevel.NORMAL)
    public void postReportInvalidData() {
        RequestParams.getInstance().getRequestParams()
                .header(CONTENT_TYPE, "application/json")
                .body(reportsData.getInvalidReportData(consultaPessoaDefault))
        .when()
                .post(REPORT_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_BAD_REQUEST);
    }

    @Test
    @Description("Try get report with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getReportInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
        .when()
                .get(REPORT_BASIC.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report with un-existent reportId")
    @Severity(SeverityLevel.NORMAL)
    public void getReportUnExistentReportId() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", ProjectProperties.getProperty(INVALID_REPORT_ID))
        .when()
                .get(REPORT_BASIC.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get report validation with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getReportValidationInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
        .when()
                .get(REPORT_VALIDATION.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report validation with un-existent reportId")
    @Severity(SeverityLevel.NORMAL)
    public void getReportValidationUnExistentReportId() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", ProjectProperties.getProperty(INVALID_REPORT_ID))
        .when()
                .get(REPORT_VALIDATION.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try post manual report with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void postManualReportInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
                .header(CONTENT_TYPE, "application/json")
                .body(reportsData.getManualApproval())
        .when()
                .post(MANUAL_APPROVAL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report data with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getReportDataInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
        .when()
                .get(REPORT_DATA.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report data with un-existent reportId")
    @Severity(SeverityLevel.NORMAL)
    public void getReportDataUnExistentReportId() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", ProjectProperties.getProperty(INVALID_REPORT_ID))
        .when()
                .get(REPORT_DATA.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get report query with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getReportQueryInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
        .when()
                .get(REPORT_QUERY.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report query with un-existent reportId")
    @Severity(SeverityLevel.NORMAL)
    public void getReportQueryUnExistentReportId() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", ProjectProperties.getProperty(INVALID_REPORT_ID))
        .when()
                .get(REPORT_QUERY.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get report parameters with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getReportParametersInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("reportId", UUID.randomUUID())
        .when()
                .get(REPORT_PARAMETERS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get report parameters with un-existent reportId")
    @Severity(SeverityLevel.NORMAL)
    public void getReportParametersUnExistentReportId() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("reportId", ProjectProperties.getProperty(INVALID_REPORT_ID))
        .when()
                .get(REPORT_PARAMETERS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get all reports with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getAllReportsInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth().when()
                .get(REPORT_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get all people report with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getAllPeopleReportInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth().when()
                .get(PEOPLE_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get person details with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getPersonDetailsInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("documentNumber", ProjectProperties.getProperty(VALID_NUMBER))
        .when()
                .get(PEOPLE_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get person details with un-existent document number")
    @Severity(SeverityLevel.NORMAL)
    public void getPersonDetailsUnExistentDocumentNumber() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("documentNumber", ProjectProperties.getProperty(INVALID_DOCUMENT_NUMBER))
        .when()
                .get(PEOPLE_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get all companies report with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getAllCompaniesReportInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth().when()
                .get(COMPANIES_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get company details with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getCompanyDetailsInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("documentNumber", ProjectProperties.getProperty(VALID_COMPANY_NUMBER))
        .when()
                .get(COMPANIES_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get company details with un-existent company number")
    @Severity(SeverityLevel.NORMAL)
    public void getCompanyDetailsUnExistentDocumentNumber() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("documentNumber", ProjectProperties.getProperty(INVALID_COMPANY_NUMBER))
                .when()
        .get(COMPANIES_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get all matrixes with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getMatrixesListInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth().when()
                .get(MATRIX_ALL.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get matrix details with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getMatrixDetailsInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth()
                .pathParam("matrixName", consultaPessoaDefault)
        .when()
                .get(MATRIX_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    @Description("Try get matrix details with un-existent matrix")
    @Severity(SeverityLevel.NORMAL)
    public void getMatrixDetailsUnExistentMatrix() {
        RequestParams.getInstance().getRequestParams()
                .pathParam("matrixName", ProjectProperties.getProperty(INVALID_MATRIX))
        .when()
                .get(MATRIX_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Try get user details with invalid auth")
    @Severity(SeverityLevel.NORMAL)
    public void getUserDetailsInvalidAuth() {
        RequestParams.getInstance().getRequestParamsInvalidAuth().when()
                .get(USER_DETAILS.getReportEndpoint())
                .prettyPeek()
        .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }
}
