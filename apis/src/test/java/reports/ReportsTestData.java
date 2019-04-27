package reports;

import dtos.ReportParametersDTO;
import dtos.ReportRequestDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utilities.ProjectProperties.*;

public class ReportsTestData {

    private static class SingletonHolder {
        public static final ReportsTestData instance = new ReportsTestData();
    }

    public static ReportsTestData getInstance() {
        return SingletonHolder.instance;
    }

    public ReportRequestDTO getValidReportData() {
        ReportParametersDTO parameters = getValidParameters();
        ReportRequestDTO report = new ReportRequestDTO();
        report.setMatriz("consultaPessoaDefault");
        report.setParametros(parameters);
        return report;
    }

    private ReportParametersDTO getValidParameters() {
        ReportParametersDTO parameter = new ReportParametersDTO();
        parameter.setCpf_nome(getProperty(VALID_NAME));
        parameter.setCpf_numero(getProperty(VALID_NUMBER));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String birthday = LocalDate.parse(getProperty(VALID_BIRTHDAY)).format(formatter);
        parameter.setCpf_data_de_nascimento(birthday);
        return parameter;
    }
}
