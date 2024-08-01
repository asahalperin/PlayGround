package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import reports.ExtentReportManager;


public class BaseTestApi {

    @BeforeClass
    public void setUp() {
        ExtentReportManager.initReports();
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flushReports();
    }
}
