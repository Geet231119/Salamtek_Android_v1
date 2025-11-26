package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class ShopMainPage extends BaseTest {

	private By shopHeader = By.id("com.app.salamtek:id/txtTitleMsg");
	private By pharmacyTitleTxt = By.id("com.app.salamtek:id/txtTitleMsg");
	private By productTitleTxt = By.id("com.app.salamtek:id/txtName");
	private By addToCartBtn = By.id("com.app.salamtek:id/linAddToCart");
	private By awesomeChoicePopUp = By.id("com.app.salamtek:id/txtTitle");
	private By viewCartBtn = By.id("com.app.salamtek:id/txtViewCart");
	private By myCartHeader = By.xpath("(//android.widget.TextView[@text=\"MY CART\"])[2]");
	
	public ShopMainPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectPharmacy(String pharmacyName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(shopHeader));
		try {
			takeScreenShot(driver, "ShopMainPage", tcID, sheetName);
			if (driver.findElement(shopHeader).getText().trim().contains("SHOP")) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+pharmacyName+"\").instance(0))"));
				waitSometime();
				if (driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""+pharmacyName+"\"]")).isDisplayed()) {
					driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""+pharmacyName+"\"]")).click();
					explicitWait(driver, driver.findElement(pharmacyTitleTxt));
					if(driver.findElement(pharmacyTitleTxt).getText().trim().contains(pharmacyName.toUpperCase())) {
						result = driver.findElement(pharmacyTitleTxt).isDisplayed();
					}
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
	
	public Boolean selectProduct(String pharmacyName, String productName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(pharmacyTitleTxt));
		try {
			takeScreenShot(driver, "PharmacyProfilePage", tcID, sheetName);
			if (driver.findElement(pharmacyTitleTxt).getText().trim().contains(pharmacyName.toUpperCase())) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+productName+"\").instance(0))"));
				waitSometime();
				//swipeDown(656, 766, 656, 1617);
				explicitWait(driver, driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""+productName+"\"]")));
				if (driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""+productName+"\"]")).isDisplayed()) {
					driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtName\" and @text=\""+productName+"\"]")).click();
					explicitWait(driver, driver.findElement(productTitleTxt));
					if(driver.findElement(productTitleTxt).getText().trim().equalsIgnoreCase(productName)) {
						takeScreenShot(driver, "ProductPage", tcID, sheetName);
						driver.findElement(addToCartBtn).click();
						explicitWait(driver, driver.findElement(awesomeChoicePopUp));
						driver.findElement(viewCartBtn).click();
						explicitWait(driver, driver.findElement(myCartHeader));
						result = driver.findElement(myCartHeader).isDisplayed();
					}
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
