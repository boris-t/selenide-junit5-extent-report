import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementShould;
import org.junit.jupiter.api.extension.*;

public class BaseTestClass implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {
    static ExtentReports reports;
    static ExtentTest test;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String filename = System.getProperty("user.dir") + "/test-output/" + context.getDisplayName() + "_Results.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://google.com";
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        test = reports.createTest(context.getDisplayName());

        test.log(Status.INFO, context.getDisplayName() + " - started");

    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
            test.addScreenCaptureFromPath(((ElementShould) context.getExecutionException().get()).getScreenshot());
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
        Selenide.close();
    }


}
