package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GoToPaymentPage extends BaseTest {
	
	private By pickupOrDeliveryTxt = By.id("com.app.salamtek:id/txtPickUpDelivery");
	private By deliveryBtn = By.id("com.app.salamtek:id/txtDelivery");
	private By insuranceSelect = By.id("com.app.salamtek:id/switchInsurance");
	private By goToPaymentBtn = By.id("com.app.salamtek:id/btnGoToPayment");
	private By checkoutTxt = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\"CHECKOUT\"]");
	
	public GoToPaymentPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifyGoToPayment(String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(pickupOrDeliveryTxt));
		try {
			takeScreenShot(driver, "GoToPaymentPage", tcID, sheetName);
			driver.findElement(deliveryBtn).click();
			driver.findElement(goToPaymentBtn).click();
			explicitWait(driver, driver.findElement(checkoutTxt));
			result = driver.findElement(checkoutTxt).isDisplayed();
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
