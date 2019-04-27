package dtos;

public class ResultErrorDTO extends ResultDTO {

    private String error;
    private String message;
    private ValidationDTO validation;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationDTO getValidation() {
        return validation;
    }

    public void setValidation(ValidationDTO validation) {
        this.validation = validation;
    }
}
