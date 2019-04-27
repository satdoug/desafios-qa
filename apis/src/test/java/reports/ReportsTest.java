package reports;

import dtos.ManualApprovalRequestDTO;
import dtos.ReportRequestDTO;
import dtos.ResultBatchNumberDTO;
import dtos.ResultReportValidationDTO;
import enums.ReportMatrix;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static enums.ReportMatrix.*;

@Tags({@Tag("all"), @Tag("reports")})
public class ReportsTest {

    private static ReportsTestStep reportsTestStep;
    private static ReportsTestData reportsTestData;
    private static ReportTestAssertions reportsTestAssertions;

    @BeforeAll
    public static void setUp() {
        reportsTestStep = ReportsTestStep.getInstance();
        reportsTestData = ReportsTestData.getInstance();
        reportsTestAssertions = ReportTestAssertions.getInstance();
    }

    @Test
    @Description("Request a report for matrix consultaPessoaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaPessoaDefault() {
        ResultBatchNumberDTO result = postAndAssertValidReport(consultaPessoaDefault);
        getAndAssertReportValidation(result);
    }

    @Test
    @Description("Request a report for matrix consultaCPF")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaCpf() {
        ResultBatchNumberDTO result = postAndAssertValidReport(consultaCPF);
        getAndAssertReportValidation(result);
    }

    @Test
    @Description("Request a report for matrix consultaEmpresaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaEmpresaDefault() {
        ResultBatchNumberDTO result = postAndAssertValidReport(consultaEmpresaDefault);
        getAndAssertReportValidation(result);
    }

    @Test
    @Description("Approve a report manually")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportManually() {
        ResultBatchNumberDTO result = postAndAssertValidReport(consultaPessoaDefault);
        ResultBatchNumberDTO approvalResult = postAndAssertManualApproval(result);
        getAndAssertReportValidatedManually(approvalResult);
    }

    private ResultBatchNumberDTO postAndAssertValidReport(ReportMatrix consultaPessoaDefault) {
        ReportRequestDTO validReportData = reportsTestData.getValidReportData(consultaPessoaDefault);
        ResultBatchNumberDTO result = reportsTestStep.createReport(validReportData);
        reportsTestAssertions.assertValidResult(result);
        return result;
    }

    private ResultBatchNumberDTO postAndAssertManualApproval(ResultBatchNumberDTO result) {
        UUID reportId = result.getResult().getNumero();
        ManualApprovalRequestDTO validReportData = reportsTestData.getManualApproval();
        ResultBatchNumberDTO approvalResult = reportsTestStep.manualApproval(reportId, validReportData);
        reportsTestAssertions.assertValidResult(approvalResult);
        return approvalResult;
    }

    private void getAndAssertReportValidation(ResultBatchNumberDTO result) {
        UUID reportId = result.getResult().getNumero();
        ResultReportValidationDTO validationResult = reportsTestStep.awaitFinishReportValidation(reportId);
        reportsTestAssertions.assertReportValidation(validationResult);
    }

    private void getAndAssertReportValidatedManually(ResultBatchNumberDTO approvalResult) {
        UUID reportId = approvalResult.getResult().getNumero();
        ResultReportValidationDTO validationResult = reportsTestStep.awaitFinishReportValidation(reportId);
        reportsTestAssertions.assertReportValidatedManually(validationResult);
    }
}
