package reports;

import dtos.ManualApprovalRequestDTO;
import dtos.ReportRequestDTO;
import dtos.ResultDTO;
import enums.ReportMatrix;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import support.ReportsAssertions;
import support.ReportsData;
import support.ReportsSteps;

import java.util.UUID;

import static enums.ReportMatrix.*;
import static enums.ReportEndpoint.REPORT_VALIDATION;

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
        ResultDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    @Test
    @Description("Approve the report consultaCpf manually")
    @Tag("consultaCPF")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportConsultaCpfManually() {
        UUID reportId = postAndAssertValidReport(consultaCPF).getResult().getNumero();
        ResultDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    @Test
    @Description("Approve the report consultaEmpresaDefault manually")
    @Tag("consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void approveReportConsultaEmpresaDefaultManually() {
        UUID reportId = postAndAssertValidReport(consultaEmpresaDefault).getResult().getNumero();
        ResultDTO manualReportResult = postAndAssertManualApproval(reportId);
        getAndAssertReportValidatedManually(manualReportResult);
    }

    private ResultDTO postAndAssertValidReport(ReportMatrix reportMatrix) {
        ReportRequestDTO validReportData = reportsData.getValidReportData(reportMatrix);
        ResultDTO result = reportsSteps.createReport(validReportData);
        reportsAssertions.assertValidResult(result);
        return result;
    }

    private ResultDTO postAndAssertManualApproval(UUID reportId) {
        ManualApprovalRequestDTO validReportData = reportsData.getManualApproval();
        ResultDTO manualReportResult = reportsSteps.manualApproval(reportId, validReportData);
        reportsAssertions.assertValidResult(manualReportResult);
        return manualReportResult;
    }

    private void getAndAssertReportValidatedManually(ResultDTO manualReportResult) {
        UUID manualReportId = manualReportResult.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_VALIDATION, manualReportId);
        reportsAssertions.assertReportValidatedManually(validationResult);
    }
}
