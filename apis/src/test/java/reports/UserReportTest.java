package reports;

import dtos.ResultDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import support.ReportsAssertions;
import support.ReportsSteps;

@Tags({@Tag("all"), @Tag("userReport")})
public class UserReportTest {

    private static ReportsSteps reportsSteps;
    private static ReportsAssertions reportsAssertions;

    @BeforeAll
    public static void setUp() {
        reportsSteps = ReportsSteps.getInstance();
        reportsAssertions = ReportsAssertions.getInstance();
    }

    @Test
    @Description("Get user details")
    @Severity(SeverityLevel.NORMAL)
    public void getUserDetails() {
        ResultDTO result = reportsSteps.getUserDetails();
        reportsAssertions.assertUserDetails(result);
    }
}
