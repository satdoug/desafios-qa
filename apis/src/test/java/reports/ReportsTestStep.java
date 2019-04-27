package reports;

import dtos.ReportRequestDTO;
import dtos.ResultValidDTO;
import utilities.RequestParams;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class ReportsTestStep {

    private static class SingletonHolder {
        public static final ReportsTestStep instance = new ReportsTestStep();
    }

    public static ReportsTestStep getInstance() {
        return SingletonHolder.instance;
    }

    public ResultValidDTO createReport(ReportRequestDTO request) {
        return RequestParams.getInstance().getRequestParams()
                .header(CONTENT_TYPE, "application/json")
                .body(request)
            .when()
                .post("/relatorios")
                .prettyPeek()
            .then()
                .statusCode(HTTP_OK)
                .extract().as(ResultValidDTO.class);
    }
}
