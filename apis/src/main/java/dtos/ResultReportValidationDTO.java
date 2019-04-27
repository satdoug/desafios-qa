package dtos;

public class ResultReportValidationDTO extends ResultDTO {

    private ValidationResultDTO result;

    public ValidationResultDTO getResult() {
        return result;
    }

    public void setResult(ValidationResultDTO result) {
        this.result = result;
    }
}
