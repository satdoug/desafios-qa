package reports;

import dtos.ReportRequestDTO;
import dtos.ResultBatchNumberDTO;
import dtos.ResultReportValidationDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import support.ReportsAssertions;
import support.ReportsData;
import support.ReportsSteps;

import java.util.UUID;

import static enums.ReportMatrix.consultaEmpresaDefault;

@Tags({@Tag("all"), @Tag("consultaEmpresaDefault")})
public class ConsultaEmpresaDefaultTest {

    private static ReportsSteps reportsSteps;
    private static ReportsData reportsData;
    private static ReportsAssertions reportsAssertions;
    private ResultBatchNumberDTO result;

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
    @Description("Request a report for matrix consultaEmpresaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultReportValidationDTO validationResult = reportsSteps.awaitFinishReportValidation(reportId);
        reportsAssertions.assertReportValidation(validationResult);
    }
}
