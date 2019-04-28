package support;

import dtos.ResultDTO;
import dtos.ResultDetailsDTO;
import dtos.ValidationRulesDTO;
import enums.ValidationResult;
import io.qameta.allure.Step;

import java.util.List;
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
    public void assertReportValidatedManually(ResultDTO validationResult) {
        ResultDetailsDTO result = validationResult.getResult();
        assertThat(result.getResultado(), is(MANUAL_APPROVAL));
        assertThat(result.getValidado_manualmente(), is(true));
    }
}
