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

import static enums.FilterParameters.*;
import static enums.ReportMatrix.consultaPessoaDefault;
import static enums.ReportEndpoint.*;

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
        Map<String, Object> filterParameters = getReportFilterParameters();
        ResultDTO result = reportsSteps.getFilteredReport(REPORT_ALL, filterParameters);
        reportsAssertions.assertFilteredReport(filterParameters, result);
    }

    @Test
    @Description("Filter requested report for matrix consultaPessoaDefault in people list")
    @Severity(SeverityLevel.CRITICAL)
    public void filterConsultaPessoaDefaultReportInPeopleList() {
        Map<String, Object> filterParameters = getPeopleFilterParameters();
        ResultDTO result = reportsSteps.getFilteredReport(PEOPLE_ALL, filterParameters);
        reportsAssertions.assertFilteredPeople(filterParameters, result);
    }

    @Test
    @Description("Get person details after request report for matrix consultaPessoaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void getPersonDetailsAfterConsultaPessoaDefault() {
        ResultDTO personResult = reportsSteps.getReportDetails(PEOPLE_DETAILS, validReportData.getParametros().getCpf_numero());
        reportsAssertions.assertPersonDetails(validReportData, result.getResult().getNumero(), personResult);
    }

    private Map<String, Object> getReportFilterParameters() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put(numero_documento.name(), validReportData.getParametros().getCpf_numero());
        filterParams.put(tipo_pessoa.name(), "Pessoa");
        filterParams.put(numero.name(), result.getResult().getNumero());
        return filterParams;
    }

    private Map<String, Object> getPeopleFilterParameters() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put(cpf.name(), validReportData.getParametros().getCpf_numero());
        return filterParams;
    }
}
