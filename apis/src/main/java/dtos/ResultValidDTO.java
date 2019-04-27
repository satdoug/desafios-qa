package dtos;

public class ResultValidDTO extends ResultDTO {

    private BatchNumberDTO result;

    public BatchNumberDTO getResult() {
        return result;
    }

    public void setResult(BatchNumberDTO result) {
        this.result = result;
    }
}
