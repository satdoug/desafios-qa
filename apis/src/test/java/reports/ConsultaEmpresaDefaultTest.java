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
import static enums.ReportMatrix.consultaEmpresaDefault;
import static enums.ReportEndpoint.*;

@Tags({@Tag("all"), @Tag("consultaEmpresaDefault")})
public class ConsultaEmpresaDefaultTest {

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
            validReportData = reportsData.getValidReportData(consultaEmpresaDefault);
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
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_VALIDATION, reportId);
        reportsAssertions.assertReportValidation(validationResult);
    }

    @Test
    @Description("Request basic report for matrix consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestBasicReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_BASIC, reportId);
        reportsAssertions.assertReportBasic(validationResult);
    }

    @Test
    @Description("Request data report for matrix consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestDataReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_DATA, reportId);
        reportsAssertions.assertReportDataConsultaEmpresaDefault(validationResult);
    }

    @Test
    @Description("Request query report for matrix consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestQueryReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportQuery(reportId);
        reportsAssertions.assertReportQueryConsultaEmpresaDefault(validationResult);
    }

    @Test
    @Description("Request parameters report for matrix consultaEmpresaDefault")
    @Severity(SeverityLevel.CRITICAL)
    public void requestParametersReportConsultaEmpresaDefault() {
        UUID reportId = result.getResult().getNumero();
        ResultDTO validationResult = reportsSteps.awaitFinishReportProcessing(REPORT_PARAMETERS, reportId);
        reportsAssertions.assertReportParametersConsultaEmpresaDefault(validReportData, validationResult);
    }

    @Test
    @Description("Filter requested report for matrix consultaEmpresaDefault in reports list")
    @Severity(SeverityLevel.CRITICAL)
    public void filterConsultaEmpresaDefaultReportInList() {
        Map<String, Object> filterParameters = getReportFilterParameters();
        ResultDTO result = reportsSteps.getFilteredReport(REPORT_ALL, filterParameters);
        reportsAssertions.assertFilteredReport(filterParameters, result);
    }

    @Test
    @Description("Filter requested report for matrix consultaEmpresaDefault in companies list")
    @Severity(SeverityLevel.CRITICAL)
    public void filterConsultaEmpresaDefaultReportInCompaniesList() {
        Map<String, Object> filterParameters = getCompaniesFilterParameters();
        ResultDTO result = reportsSteps.getFilteredReport(COMPANIES_ALL, filterParameters);
        reportsAssertions.assertFilteredCompanies(filterParameters, result);
    }

    @Test
    @Description("Get company details after request report for matrix consultaEmpresaDetails")
    @Severity(SeverityLevel.CRITICAL)
    public void getCompanyDetailsAfterConsultaEmpresaDetails() {
        ResultDTO companyResult = reportsSteps.getReportDetails(COMPANIES_DETAILS, validReportData.getParametros().getCnpj_numero());
        reportsAssertions.assertCompanyDetails(validReportData, result.getResult().getNumero(), companyResult);
    }

    private Map<String, Object> getReportFilterParameters() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put(numero_documento.name(), validReportData.getParametros().getCnpj_numero());
        filterParams.put(tipo_pessoa.name(), "Empresa");
        filterParams.put(numero.name(), result.getResult().getNumero());
        return filterParams;
    }

    private Map<String, Object> getCompaniesFilterParameters() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put(cnpj.name(), validReportData.getParametros().getCnpj_numero());
        return filterParams;
    }
}
