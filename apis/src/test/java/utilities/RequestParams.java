package utilities;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static utilities.ProjectProperties.*;

public class RequestParams {

    private static class SingletonHolder {
        public static final RequestParams instance = new RequestParams();
    }

    public static RequestParams getInstance() {
        return SingletonHolder.instance;
    }

    public RequestSpecification getRequestParams() {
        return given()
                .baseUri(getProperty(BASE_URL))
                .header(AUTHORIZATION, getProperty(TOKEN))
                .log().all();
    }
}
