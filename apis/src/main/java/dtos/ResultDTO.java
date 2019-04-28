package dtos;

public class ResultDTO {

    private ResultDetailsDTO result;
    private Integer status_code;

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public ResultDetailsDTO getResult() {
        return result;
    }

    public void setResult(ResultDetailsDTO result) {
        this.result = result;
    }
}
