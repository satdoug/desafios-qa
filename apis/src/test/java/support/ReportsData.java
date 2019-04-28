package support;

import dtos.*;
import enums.ReportMatrix;
import io.qameta.allure.Step;
import utilities.ManualApprovalRandomizer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utilities.ProjectProperties.*;

public class ReportsData {

    private static class SingletonHolder {
        public static final ReportsData instance = new ReportsData();
    }

    public static ReportsData getInstance() {
        return SingletonHolder.instance;
    }

    @Step
    public ReportRequestDTO getValidReportData(ReportMatrix reportMatrix) {
        ReportParameters parameters = getValidParameters(reportMatrix);
        ReportRequestDTO report = new ReportRequestDTO();
        report.setMatriz(reportMatrix);
        report.setParametros(parameters);
        return report;
    }

    @Step
    public ManualApprovalRequestDTO getManualApproval() {
        return ManualApprovalRandomizer.getInstance().getRandomValue();
    }

    private ReportParameters getValidParameters(ReportMatrix reportMatrix) {
        return reportMatrix.equals(ReportMatrix.consultaEmpresaDefault)
                ? getValidCompanyParameters()
                : getValidPersonParameters();
    }

    private ReportParametersPersonDTO getValidPersonParameters() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String birthday = LocalDate.parse(getProperty(VALID_BIRTHDAY)).format(formatter);
        ReportParametersPersonDTO parameter = new ReportParametersPersonDTO();
        parameter.setCpf_nome(getProperty(VALID_NAME));
        parameter.setCpf_numero(getProperty(VALID_NUMBER));
        parameter.setCpf_data_de_nascimento(birthday);
        return parameter;
    }

    private ReportParametersCompanyDTO getValidCompanyParameters() {
        ReportParametersCompanyDTO parameter = new ReportParametersCompanyDTO();
        parameter.setCnpj_numero(getProperty(VALID_COMPANY_NUMBER));
        return parameter;
    }
}
