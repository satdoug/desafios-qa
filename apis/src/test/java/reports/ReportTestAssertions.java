package reports;

import dtos.ResultValidDTO;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class ReportTestAssertions {

    private static class SingletonHolder {
        public static final ReportTestAssertions instance = new ReportTestAssertions();
    }

    public static ReportTestAssertions getInstance() {
        return SingletonHolder.instance;
    }

    public void assertValidResult(ResultValidDTO result) {
        assertThat(result.getStatus_code(), is(HTTP_OK));
        assertThat(result.getResult(), not(nullValue()));
        assertThat(result.getResult().getNumero(), not(nullValue()));
    }
}
