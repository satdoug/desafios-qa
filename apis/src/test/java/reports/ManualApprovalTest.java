package reports;

import dtos.ManualApprovalRequestDTO;
import dtos.ReportRequestDTO;
import dtos.ResultBatchNumberDTO;
import dtos.ResultReportValidationDTO;
import enums.ReportMatrix;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import support.ReportsAssertions;
import support.ReportsData;
import support.ReportsSteps;

import java.util.UUID;

import static enums.ReportMatrix.consultaCPF;
import static enums.ReportMatrix.consultaEmpresaDefault;
import static enums.ReportMatrix.consultaPessoaDefault;

@Tags({@Tag("all"), @Tag("manualApproval")})
public class ManualApprovalTest {

    private static ReportsSteps reportsSteps;
    private static ReportsData reportsData;
    private static ReportsAssertions reportsAssertions;

    @BeforeAll
    public static void setUp() {
        reportsSteps = ReportsSteps.getInstance();
        reportsData = ReportsData.getInstance();
        reportsAssertions = ReportsAssertions.getInstance();
    }

    @Test
    @Description("Approve the report consultaPessoaDefault manually")
    @Tag("consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportConsultaPessoaDefaultManually() {
        UUID reportId = postAndAssertValidReport(consultaPessoaDefault).getResult().getNumero();
        ResultBatchNumberDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    @Test
    @Description("Approve the report consultaCpf manually")
    @Tag("consultaCPF")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportConsultaCpfManually() {
        UUID reportId = postAndAssertValidReport(consultaCPF).getResult().getNumero();
        ResultBatchNumberDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    @Test
    @Description("Approve the report consultaEmpresaDefault manually")
    @Tag("consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportConsultaEmpresaDefaultManually() {
        UUID reportId = postAndAssertValidReport(consultaEmpresaDefault).getResult().getNumero();
        ResultBatchNumberDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    private ResultBatchNumberDTO postAndAssertValidReport(ReportMatrix reportMatrix) {
        ReportRequestDTO validReportData = reportsData.getValidReportData(reportMatrix);
        ResultBatchNumberDTO result = reportsSteps.createReport(validReportData);
        reportsAssertions.assertValidResult(result);
        return result;
    }

    private ResultBatchNumberDTO postAndAssertManualApproval(UUID reportId) {
        ManualApprovalRequestDTO validReportData = reportsData.getManualApproval();
        ResultBatchNumberDTO manualReportResult = reportsSteps.manualApproval(reportId, validReportData);
        reportsAssertions.assertValidResult(manualReportResult);
        return manualReportResult;
    }

    private void getAndAssertReportValidatedManually(ResultBatchNumberDTO manualReportResult) {
        UUID manualReportId = manualReportResult.getResult().getNumero();
        ResultReportValidationDTO validationResult = reportsSteps.awaitFinishReportValidation(manualReportId);
        reportsAssertions.assertReportValidatedManually(validationResult);
    }
}
