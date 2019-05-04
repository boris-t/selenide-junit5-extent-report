import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class MyTestWatcher implements TestWatcher {


    private ExtentTest test;


    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable e) {
        test.log(Status.SKIP, "Test Case skipped");
        test.log(Status.SKIP, e);
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        // do something
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable e) {
        test.log(Status.FAIL, "Test Case failed because: ");
        test.log(Status.FAIL, e);
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        test.log(Status.PASS, "Test Case Passed");
    }
}
