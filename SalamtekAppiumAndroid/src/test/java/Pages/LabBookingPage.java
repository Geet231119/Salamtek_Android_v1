package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LabBookingPage extends BaseTest {

	private By searchTestName = By.id("com.app.salamtek:id/txtTestName");
	private By bookNowBtn = By.id("com.app.salamtek:id/txtProceed");
	private By bookingStatementLbl = By.id("com.app.salamtek:id/txtBookingStatement");
	private By proceedBtn = By.id("com.app.salamtek:id/btnProceed");
	private By checkoutLbl = By.id("com.app.salamtek:id/txtCheckoutLabel");

	public LabBookingPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectTimeSlot(String profileTestName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(searchTestName));
			if (driver.findElement(searchTestName).getText().trim().equalsIgnoreCase(profileTestName)) {
				swipeDown(562, 1271, 548, 1004);
				waitSometime();
				for (int i = 7; i <= 11; i++) {
					if (driver.findElementByXPath(
							"(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/rootLayout\"])[" + i + "]")
							.isEnabled()) {
						driver.findElementByXPath(
								"(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/rootLayout\"])[" + i
										+ "]")
								.click();
						break;
					} else {
						if (i == 11) {
							swipeDown(824, 1805, 9, 1809);
							i = 8;
						}
						continue;
					}
				}
				explicitWait(driver, driver.findElement(bookNowBtn));
				driver.findElement(bookNowBtn).click();
				explicitWait(driver, driver.findElement(bookingStatementLbl));
				result = driver.findElement(bookingStatementLbl).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyBookingStatement(String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(bookingStatementLbl));
			if (driver.findElement(bookingStatementLbl).isDisplayed()) {
				takeScreenShot(driver, "LabBookingStatementPage", tcID, sheetName);
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(checkoutLbl));
				result = driver.findElement(checkoutLbl).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
