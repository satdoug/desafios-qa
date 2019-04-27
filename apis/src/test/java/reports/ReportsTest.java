package reports;

import dtos.ResultValidDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags({@Tag("all"), @Tag("reports")})
public class ReportsTest {

    private static ReportsTestStep reportsTestStep;
    private static ReportsTestData reportsTestData;
    private static ReportTestAssertions reportsTestAssertions;

    @BeforeAll
    public static void setUp() {
        reportsTestStep = ReportsTestStep.getInstance();
        reportsTestData = ReportsTestData.getInstance();
        reportsTestAssertions = ReportTestAssertions.getInstance();
    }

    @Test
    @Description("Request a report with status VALID")
    @Tag("blocker")
    @Severity(SeverityLevel.BLOCKER)
    public void requestReportStatusValid() {
        ResultValidDTO result = reportsTestStep.createReport(reportsTestData.getValidReportData());
        reportsTestAssertions.assertValidResult(result);
    }
}
