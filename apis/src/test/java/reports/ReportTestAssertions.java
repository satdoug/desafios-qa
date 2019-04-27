package reports;

import dtos.ResultBatchNumberDTO;
import dtos.ResultReportValidationDTO;
import dtos.ValidationResultDTO;
import dtos.ValidationRulesDTO;
import enums.ValidationResult;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static enums.ValidationResult.VALID;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReportTestAssertions {

    private static class SingletonHolder {
        public static final ReportTestAssertions instance = new ReportTestAssertions();
    }

    public static ReportTestAssertions getInstance() {
        return SingletonHolder.instance;
    }

    @Step
    public void assertValidResult(ResultBatchNumberDTO result) {
        assertThat(result.getStatus_code(), is(HTTP_OK));
        assertThat(result.getResult(), not(nullValue()));
        assertThat(result.getResult().getNumero(), not(nullValue()));
    }

    @Step
    public void assertReportValidation(ResultReportValidationDTO validationResult) {
        ValidationResultDTO result = validationResult.getResult();
        assertThat(result.getResultado(), is(VALID));
        assertThat(result.getValidado_manualmente(), is(false));
        List<ValidationResult> allResults = result.getValidacoes().stream()
                .map(ValidationRulesDTO::getResultado)
                .collect(Collectors.toList());
        assertThat(allResults, everyItem(is(VALID)));
    }
}
