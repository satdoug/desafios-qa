package reports;

import dtos.MatrixResultDTO;
import dtos.ResultDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import support.ReportsAssertions;
import support.ReportsData;
import support.ReportsSteps;

import static enums.ReportMatrix.consultaPessoaDefault;

@Tags({@Tag("all"), @Tag("matrixReports")})
public class MatrixReportsTest {

    private static ReportsSteps reportsSteps;
    private static ReportsData reportsData;
    private static ReportsAssertions reportsAssertions;

    @BeforeAll
    public static void setUp() {
        reportsSteps = ReportsSteps.getInstance();
        reportsAssertions = ReportsAssertions.getInstance();
    }

    @Test
    @Description("Get matrix list allowed for the token")
    @Severity(SeverityLevel.NORMAL)
    public void getMatrixList() {
        ResultDTO result = reportsSteps.getMatrixList();
        reportsAssertions.assertMatrixList(result);
    }

    @Test
    @Description("Get matrix details")
    @Severity(SeverityLevel.NORMAL)
    public void getMatrixDetails() {
        MatrixResultDTO result = reportsSteps.getMatrixDetails(consultaPessoaDefault);
        reportsAssertions.assertMatrixDetailsConsultaPessoaDefault(result);
    }
}
