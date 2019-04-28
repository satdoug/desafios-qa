package dtos;

public class ReportItemWrapper {

    private ReportItemDTO reportItemDTO;

    public ReportItemWrapper(ReportItemDTO reportItemDTO) {
        this.reportItemDTO = reportItemDTO;
    }

    public ReportItemDTO getReportItemDTO() {
        return reportItemDTO;
    }

    public void setReportItemDTO(ReportItemDTO reportItemDTO) {
        this.reportItemDTO = reportItemDTO;
    }
}
