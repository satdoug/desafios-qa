package reports;

import dtos.ReportRequestDTO;
import dtos.ResultValidDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static enums.ReportMatrix.consultaCPF;
import static enums.ReportMatrix.consultaEmpresaDefault;
import static enums.ReportMatrix.consultaPessoaDefault;

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
        ReportRequestDTO validReportData = reportsTestData.getValidReportData(consultaPessoaDefault);
        ResultValidDTO result = reportsTestStep.createReport(validReportData);
        reportsTestAssertions.assertValidResult(result);
    }

    @Test
    @Description("Request a report for matrix consultaCPF")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaCpf() {
        ReportRequestDTO validReportData = reportsTestData.getValidReportData(consultaCPF);
        ResultValidDTO result = reportsTestStep.createReport(validReportData);
        reportsTestAssertions.assertValidResult(result);
    }

    @Test
    @Description("Request a report for matrix consultaEmpresaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportMatrixConsultaEmpresaDefault() {
        ReportRequestDTO validReportData = reportsTestData.getValidReportData(consultaEmpresaDefault);
        ResultValidDTO result = reportsTestStep.createReport(validReportData);
        reportsTestAssertions.assertValidResult(result);
    }
}
