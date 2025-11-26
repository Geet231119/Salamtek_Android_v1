package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class NotificationPage extends BaseTest {

	ExcelUtils excelUtils = new ExcelUtils();

	private By notificationBtn = By.id("com.app.salamtek:id/imgOne");
	private By notificationHeader = By.id("com.app.salamtek:id/txtTitle");
	private By firstNotificationTxt = By.xpath(
			"(//android.widget.RelativeLayout[@resource-id=\"com.app.salamtek:id/relMainHolder\"])[1]/android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtMsg\"]");
	private By backBtn = By.id("com.app.salamtek:id/ivBackToolbar");
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");

	public String notification = "";

	public NotificationPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	// Validate menu selection from home page
	public boolean verifyNotification(String sheetName, String tcID) throws Exception {
		boolean result = false;
		//waitSometime();
		explicitWait(driver, driver.findElement(notificationBtn));
		driver.findElement(notificationBtn).click();
		//waitSometime();
		explicitWait(driver, driver.findElement(notificationHeader));
		try {
			if (driver.findElement(notificationHeader).getText().equalsIgnoreCase("NOTIFICATIONS")) {
				takeScreenShot(driver, "NotificationPage", tcID, sheetName);
				notification = driver.findElement(firstNotificationTxt).getText().trim();
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("notification", notification);
				driver.findElement(backBtn).click();
				explicitWait(driver, driver.findElement(quickServiceLink));
				result = driver.findElement(quickServiceLink).isDisplayed();
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
