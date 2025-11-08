package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.varia.NullAppender;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import com.aventstack.extentreports.ExtentTest;


import static Utils.Util.getCurrentDate;
import static io.restassured.RestAssured.baseURI;

public class BaseClass {
        protected ExtentHtmlReporter htmlReporter;
        protected ExtentReports extent;
        protected ExtentTest test;
        private static int caseNumber = 0;
        protected String currentDate = getCurrentDate();
        private String reportPath;
        @BeforeSuite
        public void setReport(){
            String suitName = this.getClass().getSimpleName();
            reportPath = "./src/report/" + suitName +"_"+ currentDate + ".html" ;
            htmlReporter =  new ExtentHtmlReporter(reportPath);
            htmlReporter.config().setEncoding("uft-8");
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Automation Report");
            htmlReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Automation Tester","Nicolas Maranesi");
            extent.setSystemInfo("organization","Claro Pgs");
            org.apache.log4j.BasicConfigurator.configure(new NullAppender());
            String ANSI_PURPLE = "\u001B[35m";
            System.out.println( ANSI_PURPLE +"Test suite execution starts. "  + ANSI_PURPLE);
        }

        @BeforeClass
        public void setUp() throws InterruptedException {
            caseNumber++;
            Thread.sleep(1000);
        }

        @AfterClass
        public void getResult(ITestContext context) throws Exception {
            String ANSI_YELLOW = "\u001B[33m";
            String ANSI_GREEN = "\u001B[32m";
            String ANSI_RED = "\u001B[31m";

            // Access results of individual test methods from ITestContext
            for (ITestResult result : context.getPassedTests().getAllResults()) {
                test.log(Status.PASS,"The Test "+ result.getName() + " passed. ");
                System.out.println(ANSI_GREEN+"Scenario Nro:" + caseNumber + " passed the execution."  +ANSI_GREEN);
            }

            for (ITestResult result : context.getFailedTests().getAllResults()) {
                test.log(Status.FAIL,"The Test "+ result.getName() + " failed.");
                System.out.println(ANSI_RED+"Scenario Nro:" + caseNumber + " failed the execution."+ ANSI_RED);
            }

            for (ITestResult result : context.getSkippedTests().getAllResults()) {
                test.log(Status.SKIP,"The Test "+ result.getName() + " skipped.");
                System.out.println(ANSI_YELLOW+"Scenario Nro:" + caseNumber + " skipped the execution." +ANSI_YELLOW);

            }
        }

        @AfterSuite
        public void endSuite() throws IOException {
            String ANSI_PURPLE = "\u001B[35m";
            extent.flush();
            System.out.println( ANSI_PURPLE +"Test suite execution end. " + ANSI_PURPLE);

        }
    }

