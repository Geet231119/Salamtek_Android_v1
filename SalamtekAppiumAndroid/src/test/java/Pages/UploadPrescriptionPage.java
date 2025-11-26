package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class UploadPrescriptionPage extends BaseTest {
	private By uploadPrescriptionHeader = By.id("com.app.salamtek:id/txtHeader");
	private By addBtn = By.id("com.app.salamtek:id/txtAdd");
	private By galleryBtn = By.id("com.app.salamtek:id/txtGallery");
	private By photoBtn = MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");
	private By firstPhoto = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[5]/android.view.View[1]/android.view.View[2]/android.view.View");
	private By deleteBtn = By.id("com.app.salamtek:id/ivRemove");
	private By permissionControllerDialog = By.id("com.android.permissioncontroller:id/grant_dialog");
	private By permissionController_Allow = By.id("com.android.permissioncontroller:id/permission_allow_button");
	private By dismissDialog = By.xpath("//android.widget.TextView[@text=\"Salamtek will only have access to the photos that you select\"]");
	private By dismissBtn = MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	private By proceedBtn = By.id("com.app.salamtek:id/btnProceed");
	private By successMessage = By.id("com.app.salamtek:id/txtSuccess");
	private By backToHomeBtn = By.id("com.app.salamtek:id/btnBackToHome");
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	
	public UploadPrescriptionPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	// Validate menu selection from home page
	public boolean verifyUploadPrescription(String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(uploadPrescriptionHeader));
		try {
			if (driver.findElement(uploadPrescriptionHeader).isDisplayed()) {
				driver.findElement(addBtn).click();
				explicitWait(driver, driver.findElement(galleryBtn));
				driver.findElement(galleryBtn).click();
				click_IfDisplayed(permissionControllerDialog, permissionController_Allow);
				click_IfDisplayed(dismissDialog, dismissBtn);
				explicitWait(driver, driver.findElement(photoBtn));
				driver.findElement(firstPhoto).click();
				explicitWait(driver, driver.findElement(deleteBtn));
				takeScreenShot(driver, "UploadPrescriptionMainPage", tcID, sheetName);
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(successMessage));
				takeScreenShot(driver, "UploadPrescriptionSuccess", tcID, sheetName);
				result = driver.findElement(successMessage).isDisplayed();
				driver.findElement(backToHomeBtn).click();
				explicitWait(driver, driver.findElement(quickServiceLink));
				Assert.assertEquals(driver.findElement(quickServiceLink).isDisplayed(), true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
