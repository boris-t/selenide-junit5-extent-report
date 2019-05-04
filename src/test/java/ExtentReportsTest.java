import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(MyTestWatcher.class)
public class ExtentReportsTest {

    private static ExtentReports reports;
    private static ExtentTest test;
    private static String filename = System.getProperty("user.dir") + "/test-output/HtmlResults.html";


    @BeforeAll
    static void setUp() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @Test
    void openUrl() {
        String baseUrl = "http://google.com";
        test = reports.createTest("TC_1: Open Chrome Browser");
        test.log(Status.INFO, "Test Started");
        open(baseUrl);
        test.log(Status.PASS, "Google page opened");
    }

    @Test
    void search() {
        test = reports.createTest("TC_2: Search for Selenide");
        $(By.name("q")).val("Selenide");
        $("div center:nth-child(2) >input[name='btnK']").click();
        $$(".g").get(0).shouldHave(text("Selenide: concise UI tests in JAVA"));
        test.log(Status.PASS, "Search page opened");

    }

    @Test
    void goToSelenidePage() {
        test = reports.createTest("TC_3: Open Selenide Home Page");
        $$(".g h3").get(0).click();
        $("div[class='short wiki'] h3").shouldHave(text("ЧТО ТАКОЕ SELENIDE?"));
        test.log(Status.PASS, "Selenide home page opened");
    }

    @AfterAll
    static void tearDown() {
        reports.flush();
        Selenide.close();
    }


//    @Rule
//    public TestRule watcher = new TestWatcher() {
//        @Override
//        protected void succeeded(Description description) {
//            test.log(Status.PASS, "Test Case Passed");
//
//        }
//
//        @Override
//        protected void failed(Throwable e, Description description) {
//            test.log(Status.FAIL, "Test Case failed because: ");
//            test.log(Status.FAIL, e);
//
//        }
//
//        @Override
//        protected void skipped(AssumptionViolatedException e, Description description) {
//            test.log(Status.SKIP, "Test Case skipped");
//            test.log(Status.SKIP, e);
//        }
//
//    };


}
