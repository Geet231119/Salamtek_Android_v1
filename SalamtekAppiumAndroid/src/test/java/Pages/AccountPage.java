package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class AccountPage extends BaseTest {

	ExcelUtils excelUtils = new ExcelUtils();

	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	private By accountLnk = By.xpath(
			"(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/navigation_bar_item_icon_view\"])[4]");
	private By newUpcomingAppointmentTxt = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\"NEW UPCOMING APPOINTMENTS\"]");
	private By walletValueTxt = By.id("com.app.salamtek:id/txtWalletVal");
	private By homeLnk = By.xpath(
			"(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/navigation_bar_item_icon_view\"])[1]");
	private By upcomingAppointmentTxt = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\"NEW UPCOMING APPOINTMENTS\"]");
	private By reminderPage = By.id("com.app.salamtek:id/rvMedReminder");

	public AccountPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	// Validate menu selection from home page
	public boolean verifyFirstWalletValue(String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(quickServiceLink));
		try {
			if (driver.findElement(quickServiceLink).isDisplayed()) {
				driver.findElement(accountLnk).click();
				explicitWait(driver, driver.findElement(newUpcomingAppointmentTxt));
				String firstWalletValue = driver.findElement(walletValueTxt).getText().trim();
				System.out.println("First Wallet Value: " + firstWalletValue);
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("firstWalletValue", firstWalletValue);
				takeScreenShot(driver, "WalletValueBefore", tcID, sheetName);
				driver.findElement(homeLnk).click();
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

	// Validate menu selection from home page
	public boolean verifyWalletValueAfterCancellation(String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(quickServiceLink));
		try {
			if (driver.findElement(quickServiceLink).isDisplayed()) {
				driver.findElement(accountLnk).click();
				explicitWait(driver, driver.findElement(newUpcomingAppointmentTxt));
				String walletValueAfterCancellation = driver.findElement(walletValueTxt).getText().trim();
				System.out.println("Wallet Value After Cancellation: " + walletValueAfterCancellation);
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("valueAfterCancellation", walletValueAfterCancellation);
				Double refundAmt = Double.parseDouble(excelUtils.getValue("refundAmount"));
				System.out.println("refundAmount: " + refundAmt);
				Double firstVal = Double.parseDouble(excelUtils.getValue("firstWalletValue"));
				System.out.println("firstVal: " + firstVal);
				Double finalVal = Double.parseDouble(excelUtils.getValue("valueAfterCancellation"));
				System.out.println("finalVal: " + finalVal);
				Double diffBtwVals = firstVal - finalVal;
				System.out.println("diffBtwVals: " + diffBtwVals);
				if (diffBtwVals >= 0) {
					takeScreenShot(driver, "WalletValueBefore", tcID, sheetName);
					driver.findElement(homeLnk).click();
					explicitWait(driver, driver.findElement(quickServiceLink));
					result = driver.findElement(quickServiceLink).isDisplayed();
					Assert.assertEquals(result, true);
				}
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	public boolean selectPillReminderTab() {
		boolean result = false;
		explicitWait(driver, driver.findElement(upcomingAppointmentTxt));
		for (int i = 0; i <= 2; i++) {
			boolean status = displayedOrNot(
					MobileBy.AndroidUIAutomator("new UiSelector().text(\"Pill Reminder\")"));
			if (status == true) {
				driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Pill Reminder\")"))
						.click();
				break;
			} else {
				swipeDown(1023, 322, 92, 327);
				continue;
			}
		}
		result = driver.findElement(reminderPage).isDisplayed();
		Assert.assertEquals(result, true);
		return result;
	}
}
