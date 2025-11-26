package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class TestListener extends BaseTest implements ITestListener {
	
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
	
	@Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
       // iTestContext.setAttribute("AppiumDriver", getDriver());
    }
	  @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
	        //ExtentReports log operation for passed tests.
	        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	    }
	
	@Override
	 public void onTestFailure(ITestResult result) {
		
		Log.info(getTestMethodName(result) + " test is failed.");
        //Get driver from BaseTest and assign to local appium driver variable.
        AppiumDriver<MobileElement> driver = getDriver();
        //Take base64Screenshot screenshot for extent reports
        //String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage(),
        		ExtentTestManager.getTest().addScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).getModel().getMedia().get(0));
	}
	
	   @Override
	    public void onFinish(ITestContext iTestContext) {
	        Log.info("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for ExtentReports reporting!
	        ExtentManager.extentReports.flush();
	   }

}
