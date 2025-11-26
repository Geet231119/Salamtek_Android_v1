package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class LabMainPage extends BaseTest {

	private By laboratoryCenterTxt = By.xpath("//android.widget.TextView[@text=\"Laboratory Centers\"]");
	private By searchedLabTitle = By.id("com.app.salamtek:id/txtLabName");
	private By radiologyCenterTxt = By.xpath("//android.widget.LinearLayout[@content-desc=\"Radiology Centers\"]");
	private By BeautyCenterTxt = By.xpath("//android.widget.TextView[@text=\"Beauty Centers\"]");
	private By filterBtn = By.id("com.app.salamtek:id/imgSettingFilter");
	private By insuranceDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterType\" and @text=\"Insurances\"]");
	private By locationDrpDwn = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterType\" and @text=\"Location\"]");
	private By ratingDrpDwn = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterType\" and @text=\"Ratings\"]");
	private By filterSubmit = By.id("com.app.salamtek:id/relApplyButtonFilterDoc");
	private By expectedFilterResult = By.id("com.app.salamtek:id/txtTitle");

	public LabMainPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean selectLab(String medicalServiceName, String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(laboratoryCenterTxt));
		try {
			takeScreenShot(driver, "LabMainPage", tcID, sheetName);
			if (driver.findElement(laboratoryCenterTxt).getText().trim().contains("Laboratory Centers")) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
								+ medicalServiceName + "\").instance(0))"));
				waitSometime();
				if (driver.findElement(
						By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\""
								+ medicalServiceName + "\"]"))
						.isDisplayed()) {
					driver.findElement(By.xpath(
							"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\""
									+ medicalServiceName + "\"]"))
							.click();
					explicitWait(driver, driver.findElement(searchedLabTitle));
					String expectedlabName = driver.findElement(searchedLabTitle).getText().trim();
					Assert.assertEquals(expectedlabName, medicalServiceName);
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

	public Boolean verifyRadiologyFilter(String filterBy, String filterValue, String filterResult,String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(radiologyCenterTxt));
		try {
			driver.findElement(filterBtn).click();
			//Filter By Insurance
			if(filterBy.equalsIgnoreCase("Insurances")) {
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByInsurance", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Location
			if(filterBy.equalsIgnoreCase("Location")) {
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByLocation", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Ratings
			if(filterBy.equalsIgnoreCase("Ratings")) {
				driver.findElement(ratingDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByRatings", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
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
	
	public Boolean verifyBeautyFilter(String filterBy, String filterValue, String filterResult,String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(BeautyCenterTxt));
		try {
			driver.findElement(filterBtn).click();
			//Filter By Insurance
			if(filterBy.equalsIgnoreCase("Insurances")) {
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByInsurance", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Location
			if(filterBy.equalsIgnoreCase("Location")) {
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByLocation", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Ratings
			if(filterBy.equalsIgnoreCase("Ratings")) {
				driver.findElement(ratingDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByRatings", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
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
	
	public Boolean verifyLabFilter(String filterBy, String filterValue, String filterResult,String sheetName, String tcID) throws Exception {
		boolean result = false;
		explicitWait(driver, driver.findElement(laboratoryCenterTxt));
		try {
			driver.findElement(filterBtn).click();
			//Filter By Insurance
			if(filterBy.equalsIgnoreCase("Insurances")) {
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByInsurance", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Location
			if(filterBy.equalsIgnoreCase("Location")) {
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByLocation", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
					result = true;
				}
			}
			//Filter By Ratings
			if(filterBy.equalsIgnoreCase("Ratings")) {
				driver.findElement(ratingDrpDwn).click();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterValue\" and @text=\""+filterValue+"\"]")).click();
				takeScreenShot(driver, "FilterByRatings", tcID, sheetName);
				driver.findElement(filterSubmit).click();
				explicitWait(driver, driver.findElement(expectedFilterResult));
				String expectedResult = driver.findElement(expectedFilterResult).getText().trim();
				if(expectedResult.equalsIgnoreCase(filterResult)) {
					takeScreenShot(driver, "FilterResult", tcID, sheetName);
					clickBack();
					Assert.assertEquals(expectedResult, filterResult);
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
