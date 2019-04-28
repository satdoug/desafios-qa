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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static enums.DocumentType.PESSOA;
import static enums.FilterParameters.*;
import static enums.ReportMatrix.consultaPessoaDefault;
import static enums.ReportQuery.*;

@Tags({@Tag("all"), @Tag("consultaPessoaDefault")})
public class ConsultaPessoaDefaultTest {

    private static ReportsSteps reportsSteps;
    private static ReportsData reportsData;
    private static ReportsAssertions reportsAssertions;
    private ResultDTO result;
    private ReportRequestDTO validReportData;

    @BeforeAll
    public static void setUp() {
        reportsSteps = ReportsSteps.getInstance();
        reportsData = ReportsData.getInstance();
        reportsAssertions = ReportsAssertions.getInstance();
    }

    @BeforeEach
    public void postAndAssertValidReport() {
        if (result == null) {
            validReportData = reportsData.getValidReportData(consultaPessoaDefault);
            result = reportsSteps.createReport(validReportData);
            reportsAssertions.assertValidResult(result);
        }
    }

    @Test
    @Description("Request validation report for matrix consultaPessoaDefault")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestValidationReportConsultaPessoaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_VALIDATION, reportId);
        reportsAssertions.assertReportValidation(validationResult);
    }

    @Test
    @Description("Request basic report for matrix consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestBasicReportConsultaPessoaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_BASIC, reportId);
        reportsAssertions.assertReportBasic(validationResult);
    }

    @Test
    @Description("Request data report for matrix consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestDataReportConsultaPessoaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_DATA, reportId);
        reportsAssertions.assertReportDataConsultaPessoaDefault(validationResult);
    }

    @Test
    @Description("Request query report for matrix consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestQueryReportConsultaPessoaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportQuery(reportId);
        reportsAssertions.assertReportQueryConsultaPessoaDefault(validationResult);
    }

    @Test
    @Description("Request parameters report for matrix consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestParametersReportConsultaPessoaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_PARAMETERS, reportId);
        reportsAssertions.assertReportParametersConsultaPessoaDefault(validReportData, validationResult);
    }

    @Test
    @Description("Filter requested report for matrix consultaPessoaDefault in reports list")
    @Severity(SeverityLevel.CRITICAL)
    public void filterConsultaPessoaDefaultReportInList() {
        Map<String, Object> filterParameters = getFilterParameters();
        ResultDTO result = reportsSteps.getFilteredReport(filterParameters);
        reportsAssertions.assertFilteredReport(filterParameters, result);
    }

    private Map<String, Object> getFilterParameters() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put(numero_documento.name(), validReportData.getParametros().getCpf_numero());
        filterParams.put(tipo_pessoa.name(), PESSOA);
        filterParams.put(matriz.name(), consultaPessoaDefault);
        filterParams.put(numero.name(), result.getResult().getNumero());
        return filterParams;
    }
}
