package reports;

import dtos.ReportRequestDTO;
import dtos.ResultDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import support.ReportsAssertions;
import support.ReportsData;
import support.ReportsSteps;

import java.util.UUID;

import static enums.ReportMatrix.consultaEmpresaDefault;
import static enums.ReportQuery.REPORT_DATA;
import static enums.ReportQuery.REPORT_VALIDATION;

@Tags({@Tag("all"), @Tag("consultaEmpresaDefault")})
public class ConsultaEmpresaDefaultTest {

    private static ReportsSteps reportsSteps;
    private static ReportsData reportsData;
    private static ReportsAssertions reportsAssertions;
    private ResultDTO result;

    @BeforeAll
    public static void setUp() {
        reportsSteps = ReportsSteps.getInstance();
        reportsData = ReportsData.getInstance();
        reportsAssertions = ReportsAssertions.getInstance();
    }

    @BeforeEach
    public void postAndAssertValidReport() {
        if (result == null) {
            ReportRequestDTO validReportData = reportsData.getValidReportData(consultaEmpresaDefault);
            result = reportsSteps.createReport(validReportData);
            reportsAssertions.assertValidResult(result);
        }
    }

    @Test
    @Description("Request validation report for matrix consultaEmpresaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestValidationReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportValidation(REPORT_VALIDATION, reportId);
        reportsAssertions.assertReportValidation(validationResult);
    }

    @Test
    @Description("Request data report for matrix consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestDataReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportValidation(REPORT_DATA, reportId);
        reportsAssertions.assertReportData(validationResult);
    }
}
