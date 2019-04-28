package support;

import dtos.*;
import enums.ReportMatrix;
import enums.ValidationResult;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static enums.ValidationResult.MANUAL_APPROVAL;
import static enums.ValidationResult.VALID;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportsAssertions {

    private static class SingletonHolder {
        public static final ReportsAssertions instance = new ReportsAssertions();
    }

    public static ReportsAssertions getInstance() {
        return SingletonHolder.instance;
    }

    @Step
    public void assertValidResult(ResultDTO result) {
        assertThat(result.getStatus_code(), is(HTTP_OK));
        assertThat(result.getResult(), not(nullValue()));
        assertThat(result.getResult().getNumero(), not(nullValue()));
    }

    @Step
    public void assertReportBasic(ResultDTO result) {
        ResultDetailsDTO resultDetails = result.getResult();
        assertThat(resultDetails.getResultado(), is(VALID));
        assertThat(resultDetails.getValidado_manualmente(), is(false));
    }

    @Step
    public void assertReportValidation(ResultDTO result) {
        assertReportBasic(result);
        List<ValidationResult> allResults = result.getResult().getValidacoes().stream()
                .map(ValidationRulesDTO::getResultado)
                .collect(Collectors.toList());
        assertThat(allResults, everyItem(is(VALID)));
    }

    @Step
    public void assertReportDataConsultaPessoaDefault(ResultDTO result) {
        assertReportBasic(result);
        assertThat(result.getResult(), allOf(
                hasProperty("certidoes_negativas", not(nullValue())),
                hasProperty("cpf", not(nullValue())),
                hasProperty("processos", not(nullValue())),
                hasProperty("protestos", not(nullValue()))
        ));
    }

    @Step
    public void assertReportDataConsultaCpf(ResultDTO result) {
        assertReportBasic(result);
        assertThat(result.getResult(), hasProperty("cpf", not(nullValue())));
    }

    @Step
    public void assertReportDataConsultaEmpresaDefault(ResultDTO result) {
        assertReportBasic(result);
        assertThat(result.getResult(), allOf(
                hasProperty("blacklists", not(nullValue())),
                hasProperty("certidoes_negativas", not(nullValue())),
                hasProperty("cnpj", not(nullValue())),
                hasProperty("divida_ativa", not(nullValue())),
                hasProperty("processos", not(nullValue())),
                hasProperty("protestos", not(nullValue()))
        ));
    }

    @Step
    public void assertReportQueryConsultaCpf(ResultDTO result) {
        assertThat(result.getResult().getNome_matriz(), is(ReportMatrix.consultaCPF));
        assertThat(result.getResult().getConsultas(), hasSize(greaterThan(0)));
        Set<String> queryNames = assertQueryDetails(result);
        assertThat(queryNames, hasItem("CPF Receita Federal"));
    }

    @Step
    public void assertReportQueryConsultaPessoaDefault(ResultDTO result) {
        assertThat(result.getResult().getNome_matriz(), is(ReportMatrix.consultaPessoaDefault));
        assertThat(result.getResult().getConsultas(), hasSize(greaterThan(0)));
        Set<String> queryNames = assertQueryDetails(result);
        assertThat(queryNames, hasItems("Processos SP", "CPF Receita Federal", "Antecedentes Federal", "Protestos"));
    }

    @Step
    public void assertReportQueryConsultaEmpresaDefault(ResultDTO result) {
        assertThat(result.getResult().getNome_matriz(), is(ReportMatrix.consultaEmpresaDefault));
        assertThat(result.getResult().getConsultas(), hasSize(greaterThan(0)));
        Set<String> queryNames = assertQueryDetails(result);
        assertThat(queryNames, hasItems("Regularidade FGTS na Caixa", "Processos SP", "CEIS Tranparência",
                "CNEP Transparência", "Certidão Negativa de Débitos Trabalhistas", "Divida Ativa na Fazenda",
                "CNPJ Receita Federal", "Protestos"));
    }

    private Set<String> assertQueryDetails(ResultDTO result) {
        Map<String, Integer> queryDetails = result.getResult().getConsultas().stream()
                .collect(Collectors.toMap(QueryDetailsDTO::getNome, query -> query.getTentativas().size()));
        assertThat(queryDetails.values(), everyItem(is(lessThanOrEqualTo(5))));
        return queryDetails.keySet();
    }

    @Step
    public void assertReportParametersConsultaCpf(ReportRequestDTO reportData, ResultDTO result) {
        assertThat(result.getResult().getResultado(), is(VALID));
        assertThat(result.getResult().getNome(), is(ReportMatrix.consultaCPF));
        assertThat(result.getResult().getParametros(), allOf(
                hasProperty("cpf", is(reportData.getParametros().getCpf_numero())),
                hasProperty("nome", is(reportData.getParametros().getCpf_nome())),
                hasProperty("data_de_nascimento", is(reportData.getParametros().getCpf_data_de_nascimento()))
        ));
    }

    @Step
    public void assertReportParametersConsultaPessoaDefault(ReportRequestDTO reportData, ResultDTO result) {
        assertThat(result.getResult().getResultado(), is(VALID));
        assertThat(result.getResult().getNome(), is(ReportMatrix.consultaPessoaDefault));
        assertThat(result.getResult().getParametros(), allOf(
                hasProperty("cpf", is(reportData.getParametros().getCpf_numero())),
                hasProperty("nome", is(reportData.getParametros().getCpf_nome())),
                hasProperty("data_de_nascimento", is(reportData.getParametros().getCpf_data_de_nascimento()))
        ));
    }

    @Step
    public void assertReportParametersConsultaEmpresaDefault(ReportRequestDTO reportData, ResultDTO result) {
        assertThat(result.getResult().getResultado(), is(VALID));
        assertThat(result.getResult().getNome(), is(ReportMatrix.consultaEmpresaDefault));
        assertThat(result.getResult().getParametros().getCnpj(), is(reportData.getParametros().getCnpj_numero()));
    }

    @Step
    public void assertReportValidatedManually(ResultDTO validationResult) {
        ResultDetailsDTO result = validationResult.getResult();
        assertThat(result.getResultado(), is(MANUAL_APPROVAL));
        assertThat(result.getValidado_manualmente(), is(true));
    }
}
