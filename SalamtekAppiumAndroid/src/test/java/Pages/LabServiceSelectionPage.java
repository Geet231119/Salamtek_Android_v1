package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class LabServiceSelectionPage extends BaseTest {
	private By searchedLabTitle = By.id("com.app.salamtek:id/txtLabName");
	private By bookNowBtn = By.id("com.app.salamtek:id/btnBookAppointment");
	private By searchTestName = By.id("com.app.salamtek:id/txtTestName");

	public LabServiceSelectionPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectLab(String medicalServiceName, String profileName, String profileTestName, String sheetName,
			String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(searchedLabTitle));
		try {
			if (driver.findElement(searchedLabTitle).getText().trim().contains(medicalServiceName)) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PICK YOUR MEDICAL SERVICES\").instance(0))"));
				waitSometime();
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+profileName.trim()+"\").instance(0))"));
				waitSometime();
				if (driver.findElement(
						By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""
								+ profileName + "\"]"))
						.isDisplayed()) {
					driver.findElement(By.xpath(
							"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""
									+ profileName + "\"]"))
							.click();
					explicitWait(driver, driver.findElement(searchedLabTitle));
					driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\""+profileTestName+"\"]/following-sibling::android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBookNow\" and @text=\"ADD\"]")).click();
					explicitWait(driver, driver.findElement(bookNowBtn));
					takeScreenShot(driver, "TestSelection", tcID, sheetName);
					driver.findElement(bookNowBtn).click();
					explicitWait(driver, driver.findElement(searchTestName));
					String expectedlabName = driver.findElement(searchTestName).getText().trim();
					Assert.assertEquals(expectedlabName, profileTestName);
					result = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
