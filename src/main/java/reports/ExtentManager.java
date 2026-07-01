package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getReport() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/Reports/AutomationReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("OrangeHRM Automation");
            spark.config().setReportName("Playwright Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Mariam");
            extent.setSystemInfo("Framework", "Playwright + TestNG");
            extent.setSystemInfo("Language", "Java");

        }

        return extent;
    }

}