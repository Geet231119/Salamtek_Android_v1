package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePage extends BaseTest {
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	private By doctorsLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[2]");
	private By salamtekDoctorTitle = By.id("com.app.salamtek:id/txtTitleMsg");
	private By labLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[3]");
	private By radiologyLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[4]");
	private By laboratoryCenterTxt = By.xpath("//android.widget.TextView[@text=\"Laboratory Centers\"]");
	private By radiologyCenterTxt = By.xpath("//android.widget.LinearLayout[@content-desc=\"Radiology Centers\"]");
	private By viewMoreLnk = By.id("com.app.salamtek:id/txtHomeTypeViewAll");
	private By uploadPrescriptionLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[12]");
	private By uploadPrescriptionHeader = By.id("com.app.salamtek:id/txtHeader");
	private By shopLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[6]");
	private By offerLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[8]");
	private By offerheader = By.id("com.app.salamtek:id/txtOfferTitle");
	private By beautyLnk = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivServiceImage\"])[7]");
	private By BeautyCenterTxt = By.xpath("//android.widget.TextView[@text=\"Beauty Centers\"]");
	private By pharmaciesTab = By.xpath("//android.widget.TextView[@text=\"Pharmacies\"]");
	private By accountIcon = By.xpath(
			"(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/navigation_bar_item_icon_view\"])[4]");
	private By upcomingAppointmentTxt = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\"NEW UPCOMING APPOINTMENTS\"]");
	
	public HomePage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	// Validate menu selection from home page
	public boolean selectOperation(String operationName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(quickServiceLink));
		try {
			if (driver.findElement(quickServiceLink).isDisplayed()) {
				takeScreenShot(driver, "HomePage", tcID, sheetName);
				switch (operationName) {
				case "doctors":
					explicitWait(driver, driver.findElement(doctorsLnk));
					driver.findElement(doctorsLnk).click();
					explicitWait(driver, driver.findElement(salamtekDoctorTitle));
					result = driver.findElement(salamtekDoctorTitle).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "labs":
					explicitWait(driver, driver.findElement(labLnk));
					driver.findElement(labLnk).click();
					explicitWait(driver, driver.findElement(laboratoryCenterTxt));
					result = driver.findElement(laboratoryCenterTxt).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "shops":
					explicitWait(driver, driver.findElement(shopLnk));
					driver.findElement(shopLnk).click();
					waitSometime();
					explicitWait(driver, driver.findElement(pharmaciesTab));
					result = driver.findElement(pharmaciesTab).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "uploadPrescription":
					explicitWait(driver, driver.findElement(viewMoreLnk));
					driver.findElement(viewMoreLnk).click();
					explicitWait(driver, driver.findElement(uploadPrescriptionLnk));
					driver.findElement(uploadPrescriptionLnk).click();
					explicitWait(driver, driver.findElement(uploadPrescriptionHeader));
					result = driver.findElement(uploadPrescriptionHeader).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "radiology":
					explicitWait(driver, driver.findElement(radiologyLnk));
					driver.findElement(radiologyLnk).click();
					explicitWait(driver, driver.findElement(radiologyCenterTxt));
					takeScreenShot(driver, "RadiologyMainPage", tcID, sheetName);
					result = driver.findElement(radiologyCenterTxt).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "offers":
					explicitWait(driver, driver.findElement(offerLnk));
					driver.findElement(offerLnk).click();
					explicitWait(driver, driver.findElement(offerheader));
					result = driver.findElement(offerheader).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "beauty":
					explicitWait(driver, driver.findElement(beautyLnk));
					driver.findElement(beautyLnk).click();
					explicitWait(driver, driver.findElement(BeautyCenterTxt));
					result = driver.findElement(BeautyCenterTxt).isDisplayed();
					Assert.assertEquals(result, true);
					break;
				case "myAccount":
					explicitWait(driver, driver.findElement(accountIcon));
					driver.findElement(accountIcon).click();
					explicitWait(driver, driver.findElement(upcomingAppointmentTxt));
					result = driver.findElement(upcomingAppointmentTxt).isDisplayed();
					Assert.assertEquals(result, true);
					break;
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
