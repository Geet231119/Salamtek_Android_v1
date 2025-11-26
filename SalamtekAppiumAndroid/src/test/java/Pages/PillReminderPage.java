package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PillReminderPage extends BaseTest {

	private By reminderPage = By.id("com.app.salamtek:id/rvMedReminder");
	private By reminderNameTxt = By.id("com.app.salamtek:id/edtReminderName");
	private By reminderForMedicineBtn = By.id("com.app.salamtek:id/txtMedicine");
	private By reminderForOthersBtn = By.id("com.app.salamtek:id/txtOther");
	private By selectTimeDrpDwn = By.id("com.app.salamtek:id/txtSelectTime");
	private By selectBtn = By.id("com.app.salamtek:id/btnAlertPositive");
	private By dailyBtn = By.id("com.app.salamtek:id/txtDaily");
	private By saveReminderBtn = By.id("com.app.salamtek:id/btnSave");
	private By reminderCreated = By.id("com.app.salamtek:id/txtName");
	private By deleteAlarmBtn = By.id("com.app.salamtek:id/ivTrash");
	private By deleteAlarmAlert = By.id("com.app.salamtek:id/txtAlertMessage");
	private By deleteAlarmYesBtn = By.id("com.app.salamtek:id/btnAlertPositive");
	private By homePageIcon = By.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/navigation_bar_item_icon_view\"])[1]");
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	
	private By notificationTitle = By.id("android:id/title");
	private By timeToTakeTxt = By.id("com.app.salamtek:id/txtTitle");
	private By dismissBtn = By.id("com.app.salamtek:id/txtSave");

	
	public PillReminderPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifyPillReminder(String pillReminderName, String reminderFor, String repeatAlarmValue, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
				explicitWait(driver, driver.findElement(reminderPage));
				tapAnElement(521, 1410);
				explicitWait(driver, driver.findElement(reminderNameTxt));
				driver.findElement(reminderNameTxt).sendKeys(pillReminderName);
				if(reminderFor.equalsIgnoreCase("Medicine"))
					driver.findElement(reminderForMedicineBtn).click();
				else
					driver.findElement(reminderForOthersBtn).click();
				driver.findElement(selectTimeDrpDwn).click();
				explicitWait(driver, driver.findElement(selectBtn));
				driver.findElement(selectBtn).click();
				explicitWait(driver, driver.findElement(dailyBtn));
				driver.findElement(dailyBtn).click();
				takeScreenShot(driver, "ReminderMainPage", tcID, sheetName);
				driver.findElement(saveReminderBtn).click();
				explicitWait(driver, driver.findElement(reminderCreated));
				if(driver.findElement(reminderCreated).getText().trim().equalsIgnoreCase(pillReminderName)) {
					takeScreenShot(driver, "ReminderCreated", tcID, sheetName);
					result = true;
				}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean openNotification_pill(String sheetName, String tcID) {
		boolean result = false;
		swipeDown(458, 28, 503, 1405);
		explicitWait(driver, driver.findElement(notificationTitle));
		if(driver.findElement(notificationTitle).getText().trim().equalsIgnoreCase("Medicine Reminder")) {
			driver.findElement(notificationTitle).click();
			if(driver.findElement(timeToTakeTxt).getText().trim().equalsIgnoreCase("TIME TO TAKE YOUR MEDICINE")) {
				takeScreenShot(driver, "AlarmFromNotification", tcID, sheetName);
				driver.findElement(dismissBtn).click();
				waitSometime();
				explicitWait(driver, driver.findElement(reminderCreated));
				result = driver.findElement(reminderCreated).isDisplayed();
			}
		}
		driver.findElement(deleteAlarmBtn).click();
		if(driver.findElement(deleteAlarmAlert).getText().trim().equalsIgnoreCase("You want to delete this reminder?")){
			driver.findElement(deleteAlarmYesBtn).click();
			explicitWait(driver, driver.findElement(reminderPage));
			driver.findElement(homePageIcon).click();
			explicitWait(driver, driver.findElement(quickServiceLink));
			Assert.assertEquals(driver.findElement(quickServiceLink).isDisplayed(), true);
		}
		return result;
	}
}
