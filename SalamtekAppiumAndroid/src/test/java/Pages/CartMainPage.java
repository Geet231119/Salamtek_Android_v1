package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CartMainPage extends BaseTest {

	private By myCartHeader = By.xpath("(//android.widget.TextView[@text=\"MY CART\"])[2]");
	private By pickupOrDeliveryTxt = By.id("com.app.salamtek:id/txtPickUpDelivery");
	
	public CartMainPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifyProductListInCart(String pharmacyName,String productName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(myCartHeader));
		try {
			takeScreenShot(driver, "CartPage", tcID, sheetName);
			List<MobileElement> cartList = driver.findElements(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.app.salamtek:id/relMainHolder\"]"));
			int cartListCnt = cartList.size();
			if(cartListCnt == 1) {
				waitSometime();
				String cartItem = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\"]")).getText();
				System.out.println(cartItem);
				waitSometime();
				if(cartItem.trim().toUpperCase().equals(pharmacyName.toUpperCase())) {
					driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/btnViewCart\"]")).click();
				}
			}
			else {
				for(int i=1;i<=cartListCnt;i++) {
					waitSometime();
					String cartItem = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\""+pharmacyName+"\"]")).getText();
					System.out.println(cartItem);
					waitSometime();
					if(cartItem.trim().toUpperCase().equals(pharmacyName.toUpperCase())) {
						driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.app.salamtek:id/btnViewCart\"])["+i+"]")).click();
						break;
					}
				}
			}
			explicitWait(driver, driver.findElement(pickupOrDeliveryTxt));
			result = driver.findElement(pickupOrDeliveryTxt).isDisplayed();
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
