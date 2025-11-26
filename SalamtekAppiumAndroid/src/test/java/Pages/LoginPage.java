package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage extends BaseTest {

	private By languageSelectLink = By.id("com.app.salamtek:id/txtEnglish");
	private By skipLink = By.id("com.app.salamtek:id/txtSkip");
	private By loginBtn = By.id("com.app.salamtek:id/txtLogin");
	private By emailMobileTxt = By.id("com.app.salamtek:id/edtMobileNo");
	private By submitBtn = By.id("com.app.salamtek:id/txtSubmit");
	private By passwordTxt = By.id("com.app.salamtek:id/edtPassword");
	private By permissionControllerDialog = By.id("com.android.permissioncontroller:id/grant_dialog");
	private By permissionController_Allow = By.id("com.android.permissioncontroller:id/permission_allow_button");
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");

	// Elements for google login
	private By loginGoogleBtn = By.id("com.app.salamtek:id/txtGoogle");
	private By g_emailID = By.id("identifierId");
	private By g_nextBtn = By.xpath("//android.widget.Button[@text=\"NEXT\"]");
	private By g_passwordTxt = By.xpath("//android.widget.EditText[@hint=\"Enter your password\"]");

	//Elements of review pop up
	By reviewHeader = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFeedbackMatters\"]");
	By reviewCancelBtn = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtCancel\"]");
	
	public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifyLogin(String userName, String password) throws Exception {
		boolean result = false;
		try {
			waitForSpecificTime(7000);
			boolean languageDisplay = displayedOrNot(languageSelectLink);
			if (languageDisplay == false) {
					explicitWait(driver, driver.findElement(quickServiceLink));
					result = driver.findElement(quickServiceLink).isDisplayed();
					Assert.assertEquals(result, true);
			} else {
				explicitWait(driver, driver.findElement(languageSelectLink));
				driver.findElement(languageSelectLink).click();
				waitSometime();
				click_IfDisplayed(permissionControllerDialog, permissionController_Allow);
				// waitForSpecificTime(7000);
				explicitWait(driver, driver.findElement(skipLink));
				driver.findElement(skipLink).click();
				// waitSometime();
				explicitWait(driver, driver.findElement(loginBtn));
				driver.findElement(loginBtn).click();
				// waitForSpecificTime(7000);
				explicitWait(driver, driver.findElement(emailMobileTxt));
				driver.findElement(emailMobileTxt).sendKeys(userName);
				driver.findElement(submitBtn).click();
				// waitSometime();
				explicitWait(driver, driver.findElement(passwordTxt));
				driver.findElement(passwordTxt).sendKeys(password);
				driver.hideKeyboard();
				explicitWait(driver, driver.findElement(submitBtn));
				driver.findElement(submitBtn).click();
				swipeDown(463, 508, 389, 1429);
				verifyReviewCancel();
				explicitWait(driver, driver.findElement(quickServiceLink));
				result = driver.findElement(quickServiceLink).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
//			if (e.getMessage()
//					.contains("An element could not be located on the page using the given search parameters")) {
//				explicitWait(driver, driver.findElement(quickServiceLink));
//				result = driver.findElement(quickServiceLink).isDisplayed();
//				Assert.assertEquals(result, true);
//			}
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean verifyReviewCancel() {
		boolean result = false;
		waitSometime();
		List<MobileElement> targetElement = driver.findElements(reviewHeader);
		try {
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						driver.findElement(reviewCancelBtn).click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return result;
	}
}
