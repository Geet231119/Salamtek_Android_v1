package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DoctorMainPage extends BaseTest {

	private By salamtekDoctorTitle = By.id("com.app.salamtek:id/txtTitleMsg");
	private By searchDoctorTxt = By.id("com.app.salamtek:id/edtSearch");
	private By searchedDoctorTxt = By.id("com.app.salamtek:id/txtDoctorName");
	private By consultationType_InPerson = By
			.xpath("//android.widget.LinearLayout[@resource-id=\"com.app.salamtek:id/linInPerson\"]");
	private By consultationType_VideoCall = By
			.xpath("//android.widget.LinearLayout[@resource-id=\"com.app.salamtek:id/linVideoCall\"]");
	private By searchedDoctorImg = By.id("com.app.salamtek:id/ivDoc");

	public DoctorMainPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectDoctor(String doctorSearchName, String doctorName, String serviceName, String sheetName,
			String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(salamtekDoctorTitle));
		try {
			takeScreenShot(driver, "DoctorMainPage", tcID, sheetName);
			if (driver.findElement(salamtekDoctorTitle).getText().trim().contains("SALAMTEK DOCTORS")) {
				explicitWait(driver, driver.findElement(searchDoctorTxt));
				driver.findElement(searchDoctorTxt).click();
				waitSometime();
				new Actions(driver).sendKeys(doctorSearchName).perform();
				driver.hideKeyboard();
				explicitWait(driver, driver.findElement(searchedDoctorTxt));
				if (driver.findElement(searchedDoctorTxt).isDisplayed()) {
					switch (serviceName.trim()) {
					case "In Person Consultation":
						driver.findElement(consultationType_InPerson).click();
						break;
					case "Video Consultation":
						driver.findElement(consultationType_VideoCall).click();
						break;
					}
					explicitWait(driver, driver.findElement(searchedDoctorImg));
					result = driver.findElement(searchedDoctorImg).isDisplayed();
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
}
