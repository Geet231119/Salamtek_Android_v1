package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Common.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class SearchPage extends BaseTest {

	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	private By searchIcon = By.id("com.app.salamtek:id/ivSearch");
	private By searchTxt = By.id("com.app.salamtek:id/edtSearch");
	private By allTab = By.xpath("//android.widget.TextView[@text=\"All\"]");
	private By backBtn = By.xpath("//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/ivBackToolbar\"]");
	private By cancelVideo = By.id("com.app.salamtek:id/ivCancel");
	private By advancedSearchIcon = By.id("com.app.salamtek:id/imgSettingHomeSearch");
	private By specialtyBtn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtSpeciality\" and @text=\"Speciality\"]");
	private By searchBtn = By.id("com.app.salamtek:id/txtSearch");
	private By expectedSearchResult_ME = By.id("com.app.salamtek:id/txtTitleMsg");
	private By expectedSearchResult_MS = By.id("com.app.salamtek:id/txtFeaturedLabsTitle");
	private By expectedSearchResult_OF = By.id("com.app.salamtek:id/txtOfferTitle");
	private By expectedSearchResult_O = By.id("com.app.salamtek:id/txtTitleText");
	private By spec_sympDrpDwn = By.id("com.app.salamtek:id/txtFilterSelectedTab");
	private By genderDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Gender\"]");
	private By languageDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Language\"]");
	private By consultationFeeDrpDwn = By.id("com.app.salamtek:id/txtPriceRange");
	private By productCategoryDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Product Category\"]");
	private By deliveryOrPickUpDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Delivery/pickup\"]");
	private By priceDrpDwn = By.id("com.app.salamtek:id/clPriceRange");
	private By storeCategoryDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Stores Category\"]");
	private By insuranceDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Insurance\"]");
	private By locationDrpDwn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFilterName\" and @text=\"Location\"]");
	private By medicalCategoryDrpDwn = By.id("com.app.salamtek:id/txtFilterSelectedTab");
	private By offerCategoryDrpDwn = By.id("com.app.salamtek:id/txtFilterName");
	private By proceedBtn = By.id("com.app.salamtek:id/txtFindMeSpecialist");
	private By searchName = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtTitle\" or @resource-id=\"com.app.salamtek:id/txtName\" or @resource-id=\"com.app.salamtek:id/txtArticleTitle\" or @resource-id=\"com.app.salamtek:id/txtDoctorName\"]");
	private By searchImage = By.id("com.app.salamtek:id/ivImage");

	// Elements of review pop up
	By reviewHeader = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFeedbackMatters\"]");
	By reviewCancelBtn = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtCancel\"]");

	public SearchPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifySearch(String typeOfSearch, String searchSection, String searchValue, String sheetName,
			String tcID) throws Exception {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(quickServiceLink));
			driver.findElement(searchIcon).click();
			explicitWait(driver, driver.findElement(searchTxt));
			driver.findElement(searchTxt).click();
			waitSometime();
			new Actions(driver).sendKeys(searchValue).perform();
			driver.hideKeyboard();
			explicitWait(driver, driver.findElement(allTab));
			List<MobileElement> searchItems = driver.findElements(
					By.xpath("//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clMainHolder\"]"));
			System.out.println("Count of items: " + searchItems.size());
			if (searchItems.size() == 1) {
				if (driver.findElement(By.xpath(
						"//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clMainHolder\"]/android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtSearchTitle\"]"))
						.getText().trim().equalsIgnoreCase(searchValue)) {
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					if (typeOfSearch.equalsIgnoreCase("video")) {
						driver.findElement(By.xpath(
								"//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clMainHolder\"]/android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtSearchTitle\"]"))
								.click();
						waitForSpecificTime(7000);
						takeScreenShot(driver, "VideoPlayed", tcID, sheetName);
						driver.findElement(cancelVideo).click();
					}
					explicitWait(driver, driver.findElement(backBtn));
					driver.findElement(backBtn).click();
					verifyReviewCancel();
					explicitWait(driver, driver.findElement(quickServiceLink));
					result = true;
					Assert.assertEquals(result, true);
				}
			} else {
				for (int i = 1; i <= searchItems.size(); i++) {
					if (driver.findElement(By
							.xpath("(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clMainHolder\"])[" + i
									+ "]/android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtSearchTitle\"]"))
							.getText().trim().equalsIgnoreCase(searchValue)) {
						takeScreenShot(driver, "SearchResult", tcID, sheetName);
						if (typeOfSearch.equalsIgnoreCase("video")) {
							driver.findElement(By.xpath(
									"(//android.view.ViewGroup[@resource-id=\"com.app.salamtek:id/clMainHolder\"])[" + i
											+ "]/android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtSearchTitle\"]"))
									.click();
							waitForSpecificTime(7000);
							takeScreenShot(driver, "VideoPlayed", tcID, sheetName);
							driver.findElement(cancelVideo).click();
						}
						driver.findElement(backBtn).click();
						verifyReviewCancel();
						explicitWait(driver, driver.findElement(quickServiceLink));
						result = true;
						Assert.assertEquals(result, true);
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyAdvancedSearch_All(String typeOfSearch, String searchValue, String productCategory,
			String deliveryOrPickup, String location, String price, String sheetName, String tcID) {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(quickServiceLink));
			driver.findElement(advancedSearchIcon).click();
			explicitWait(driver, driver.findElement(specialtyBtn));
			for (int i = 0; i <= 2; i++) {
				boolean status = displayedOrNot(
						MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + typeOfSearch + "\")"));
				if (status == true) {
					driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + typeOfSearch + "\")"))
							.click();
					explicitWait(driver, driver.findElement(searchBtn));
					driver.findElement(searchBtn).click();
					break;
				} else {
					if (i % 2 == 0) {
						swipeDown(900, 752, 144, 759);
						i = 0;
						continue;
					}
				}
			}
			if (typeOfSearch.contains("Medical Equipment") || typeOfSearch.equalsIgnoreCase("Products")
					|| typeOfSearch.equalsIgnoreCase("Speciality") || typeOfSearch.equalsIgnoreCase("Symptoms")
					|| typeOfSearch.equalsIgnoreCase("Stores")) {
				explicitWait(driver, driver.findElement(expectedSearchResult_ME));
				if (driver.findElement(expectedSearchResult_ME).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
			}
			if (typeOfSearch.equalsIgnoreCase("Medical Service")) {
				explicitWait(driver, driver.findElement(expectedSearchResult_MS));
				if (driver.findElement(expectedSearchResult_MS).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
			}
			if (typeOfSearch.equalsIgnoreCase("Offers")) {
				explicitWait(driver, driver.findElement(expectedSearchResult_OF));
				if (driver.findElement(expectedSearchResult_OF).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
			}
			if (typeOfSearch.equalsIgnoreCase("Others")) {
				explicitWait(driver, driver.findElement(expectedSearchResult_O));
				if (driver.findElement(expectedSearchResult_O).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		if (typeOfSearch.equalsIgnoreCase("Offers") || typeOfSearch.equalsIgnoreCase("Others")) {
			waitSometime();
			clickBack();
			waitSometime();
		}
		clickBack();
		return result;
	}

	public Boolean verifyAdvancedSearch_Specific(String typeOfSearch, String searchValue, String category,
			String deliveryOrPickup, String insurance, String location, String price, String gender, String language,
			String sheetName, String tcID) {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(quickServiceLink));
			driver.findElement(advancedSearchIcon).click();
			explicitWait(driver, driver.findElement(specialtyBtn));
			for (int i = 0; i <= 2; i++) {
				boolean status = displayedOrNot(
						MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + typeOfSearch + "\")"));
				if (status == true) {
					driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + typeOfSearch + "\")"))
							.click();
					break;
				} else {
					if (i % 2 == 0) {
						swipeDown(900, 752, 144, 759);
						i = 0;
						continue;
					}
				}
			}
			switch (typeOfSearch) {
			case "Stores":
				explicitWait(driver, driver.findElement(storeCategoryDrpDwn));
				driver.findElement(storeCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(insuranceDrpDwn));
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ insurance + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Medical Equipment’s":
				explicitWait(driver, driver.findElement(productCategoryDrpDwn));
				driver.findElement(productCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(deliveryOrPickUpDrpDwn));
				driver.findElement(deliveryOrPickUpDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ deliveryOrPickup + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(priceDrpDwn));
				driver.findElement(priceDrpDwn).click();
				swipeDown(930, 1803, 539, 1806);
				waitSometime();
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Medical Service":
				explicitWait(driver, driver.findElement(medicalCategoryDrpDwn));
				driver.findElement(medicalCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(insuranceDrpDwn));
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ insurance + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Offers":
				explicitWait(driver, driver.findElement(offerCategoryDrpDwn));
				driver.findElement(offerCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				waitForSpecificTime(8000);
				explicitWait(driver, driver.findElement(searchImage));
				if (driver.findElement(searchImage).isDisplayed()) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Others":
				explicitWait(driver, driver.findElement(offerCategoryDrpDwn));
				driver.findElement(offerCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Products":
				explicitWait(driver, driver.findElement(productCategoryDrpDwn));
				driver.findElement(productCategoryDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(deliveryOrPickUpDrpDwn));
				driver.findElement(deliveryOrPickUpDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ deliveryOrPickup + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(priceDrpDwn));
				driver.findElement(priceDrpDwn).click();
				swipeDown(930, 1803, 539, 1806);
				waitSometime();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Speciality":
				explicitWait(driver, driver.findElement(spec_sympDrpDwn));
				driver.findElement(spec_sympDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(genderDrpDwn));
				driver.findElement(genderDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ gender + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(insuranceDrpDwn));
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ insurance + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(languageDrpDwn));
				driver.findElement(languageDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ language + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(consultationFeeDrpDwn));
				driver.findElement(consultationFeeDrpDwn).click();
				swipeDown(954, 1720, 545, 1720);
				waitSometime();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			case "Symptoms":
				explicitWait(driver, driver.findElement(spec_sympDrpDwn));
				driver.findElement(spec_sympDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ category + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(locationDrpDwn));
				driver.findElement(locationDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ location + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(genderDrpDwn));
				driver.findElement(genderDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ gender + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(insuranceDrpDwn));
				driver.findElement(insuranceDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ insurance + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(languageDrpDwn));
				driver.findElement(languageDrpDwn).click();
				driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtBackPainVal\" and @text=\""
								+ language + "\"]"))
						.click();
				driver.findElement(proceedBtn).click();
				explicitWait(driver, driver.findElement(consultationFeeDrpDwn));
				driver.findElement(consultationFeeDrpDwn).click();
				swipeDown(954, 1720, 545, 1720);
				waitSometime();
				explicitWait(driver, driver.findElement(searchBtn));
				takeScreenShot(driver, "SearchPage", tcID, sheetName);
				driver.findElement(searchBtn).click();
				explicitWait(driver, driver.findElement(searchName));
				if (driver.findElement(searchName).getText().trim().contains(searchValue)) {
					waitSometime();
					takeScreenShot(driver, "SearchResult", tcID, sheetName);
					result = true;
					Assert.assertEquals(result, true);
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		if (typeOfSearch.equalsIgnoreCase("Offers") || typeOfSearch.equalsIgnoreCase("Others")) {
			waitSometime();
			clickBack();
			waitSometime();
		}
		clickBack();
		return result;
	}

	public boolean verifyReviewCancel() {
		boolean result = false;
		waitSometime();
		List<MobileElement> targetElement = driver.findElements(reviewHeader);
		try {
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						driver.findElement(reviewCancelBtn).click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return result;
	}
}
