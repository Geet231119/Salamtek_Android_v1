package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DoctorBookingPage extends BaseTest {

	private By searchedDoctorImg = By.id("com.app.salamtek:id/ivDoc");
	private By selectserviceDrpDwn = By.id("com.app.salamtek:id/btnSelectService");
	private By selectYourServiceLbl = By.id("com.app.salamtek:id/txtTitle");
	private By inPersonConsultationBtn = By
			.xpath("(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clHomeTypes\"])[1]");
	private By videoConsultationBtn = By
			.xpath("(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clHomeTypes\"])[2]");
	private By proceedBtn = By.id("com.app.salamtek:id/txtBookNow");
	private By bookingStatementLbl = By.id("com.app.salamtek:id/txtBookingStatement");
	private By checkoutLbl = By.id("com.app.salamtek:id/txtCheckoutLabel");

	public DoctorBookingPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectTimeSlot(String serviceName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			if (driver.findElement(searchedDoctorImg).isDisplayed()) {
				driver.findElement(selectserviceDrpDwn).click();
				explicitWait(driver, driver.findElement(selectYourServiceLbl));
				if (driver.findElement(selectYourServiceLbl).getText().equalsIgnoreCase("SELECT YOUR SERVICE")) {
					switch (serviceName.trim()) {
					case "In Person Consultation":
						driver.findElement(inPersonConsultationBtn).click();
						break;
					case "Video Consultation":
						driver.findElement(videoConsultationBtn).click();
						break;
					}
					waitSometime();
					for (int i = 7; i <= 11; i++) {
						if (driver.findElementByXPath(
								"(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/rootLayout\"])[" + i
										+ "]")
								.isEnabled()) {
							driver.findElementByXPath(
									"(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/rootLayout\"])[" + i
											+ "]")
									.click();
							break;
						} else {
							if(i == 11) {
								swipeDown(824, 1805, 9, 1809);
								i = 8;
							}
							continue;
						}
					}

					takeScreenShot(driver, "DoctorBookingPage", tcID, sheetName);
					driver.findElement(proceedBtn).click();
					explicitWait(driver, driver.findElement(bookingStatementLbl));
					result = driver.findElement(bookingStatementLbl).isDisplayed();
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

	public Boolean verifyBookingStatement(String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			if (driver.findElement(bookingStatementLbl).isDisplayed()) {
				takeScreenShot(driver, "DoctorBookingStatementPage", tcID, sheetName);
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(checkoutLbl));
				result = driver.findElement(checkoutLbl).isDisplayed();
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
