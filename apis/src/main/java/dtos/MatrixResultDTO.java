package dtos;

public class MatrixResultDTO {

    private MatrixDetailsDTO result;
    private Integer status_code;

    public MatrixDetailsDTO getResult() {
        return result;
    }

    public void setResult(MatrixDetailsDTO result) {
        this.result = result;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }
}
