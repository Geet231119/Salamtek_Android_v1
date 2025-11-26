package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.BaseTest;
import Utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class CheckoutPage extends BaseTest {

	ExcelUtils excelUtils = new ExcelUtils();

	private By checkoutLbl = By.xpath(
			"//android.widget.TextView[(@resource-id=\"com.app.salamtek:id/txtTitle\" and @text=\"CHECKOUT\") or @resource-id=\"com.app.salamtek:id/txtCheckoutLabel\"]");
	private By walletBtn = By.id("com.app.salamtek:id/ivMyWallet");
	private By cashModeBtn = By.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/imgRadio\"])[1]");
	private By knetModeBtn = By.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/imgRadio\"])[2]");
	private By ccModeBtn = By.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/imgRadio\"])[3]");
	private By proceedBtn = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/btnSecureCheckout\" or @resource-id=\"com.app.salamtek:id/txtSecure\" or @resource-id=\"com.app.salamtek:id/btnPayNow\"]");
	private By bookedSuccess = By.xpath(
			"//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtAppointmentBooked\" or @resource-id=\"com.app.salamtek:id/txtTopMsg\"]");
	private By bookingID = By.id("com.app.salamtek:id/txtBookingIdVal");
	private By backToHomeBtn = By.id("com.app.salamtek:id/btnBackToHome");
	private By futureAppointmentAlert = By.id("com.app.salamtek:id/txtAlertMessage");
	private By futureAlertProceedBtn = By.id("com.app.salamtek:id/btnAlertPositive");
	private By quickServiceLink = By.id("com.app.salamtek:id/txtHomeType");
	private By reservationFeeChk = By.id("com.app.salamtek:id/imgReservationCheckbox");
	private By rescheduleBtn = By
			.xpath("//android.widget.LinearLayout[@resource-id=\"com.app.salamtek:id/linReschedule\"]");
	private By successMessagePopup = By.id("com.app.salamtek:id/txtMessage");
	private By okBtn = By.id("com.app.salamtek:id/btnProceed");
	private By manageBookingBtn = By.id("com.app.salamtek:id/btnManageBooking");
	private By manageOrderBtn = By.id("com.app.salamtek:id/txtManageOrder");
	private By cancelBtn = By.xpath("//android.widget.LinearLayout[@resource-id=\"com.app.salamtek:id/linCancel\"]");
	private By cancelOrderBtn = By.id("com.app.salamtek:id/txtCancelOrder");
	private By cancelAppointmentHeader = By.id("com.app.salamtek:id/txtTitleCancelAppointment");
	private By refundWalletBtn = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/imgRadio\"])[1]");
	private By refundBankBtn = By
			.xpath("(//android.widget.ImageView[@resource-id=\"com.app.salamtek:id/imgRadio\"])[2]");
	private By cancelProceedBtn = By.id("com.app.salamtek:id/txtSubmit");
	private By refundMessageTxt = By.id("com.app.salamtek:id/txtRefundAmountMsg");
	private By doneBtn = By.id("com.app.salamtek:id/txtClose");
	private By backBtn = By.id("com.app.salamtek:id/ivBackToolbar");
	private By promotionCodeTxt = By.id("com.app.salamtek:id/edtEnterPromoCode");
	private By applyNowBtn = By
			.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtApplyPromoCode\"]");
	private By subTotalTxt = By.id("com.app.salamtek:id/txtBookingAmountVal");
	private By discountTxt = By.id("com.app.salamtek:id/txtDiscountVal");
	private By totalAfterDiscount = By.id("com.app.salamtek:id/txtTotalAmountVal");

	// Elements related to KNET
	By knetHeader = By.id("PayPageEntry");
	By knetBankName = By.xpath("//*[@id=\"FCUseDebitEnable\"]/div[1]/select");
	By knetDebitNumber = By.xpath("//*[@id=\"debitNumber\"]");
	By knetExpiryMonth = By.xpath("//*[@id=\"cardExpdate\"]/select[1]");
	By knetExpiryYear = By.xpath("//*[@id=\"cardExpdate\"]/select[2]");
	By knetPin = By.xpath("//*[@id=\"cardPin\"]");
	By knetSubmitBtn = By.xpath("//*[@id=\"proceed\"]");
	By knetConfirmBtn = By.xpath("//*[@id=\"proceedConfirm\"]");

	// Elements related to VISA
	By visaHeader = By.xpath("//*[@id=\"billing_details\"]/h2[contains(text(),'Billing Information')]");
	By firstNameTxt = By.xpath("//*[@id=\"bill_to_forename\"]");
	By lastNameTxt = By.xpath("//*[@id=\"bill_to_surname\"]");
	By cardType_visa = By.xpath("//*[@id=\"card_type_001\"]");
	By cardType_mastercard = By.xpath("//*[@id=\"card_type_002\"]");
	By visaCardNumber = By.xpath("//*[@id=\"card_number\"]");
	By visaExpiryMonth = By.xpath("//*[@id=\"card_expiry_month\"]");
	By visaExpiryYear = By.xpath("//*[@id=\"card_expiry_year\"]");
	By visaCVV = By.xpath("//*[@id=\"card_cvn\"]");
	By nextBtn = By.xpath("//*[@id=\"payment_details_lower\"]/input[@value='Next']");
	By payBtn1 = By.xpath("//*[@id=\"payment_details_lower\"]/input[@value='Pay']");
	By reviewOrderHeader = By.xpath("//*[@id=\"main\"]/div/div/h2[contains(text(),'Review your Order')]");
	By payBtn = By.xpath("//*[@id=\"main\"]/div/div/div[4]/form[2]/input[@value='Pay']");
	By purchaseAuthHeader = By.xpath("//*[@id=\"content\"]/div[1]/h2[contains(text(),'Purchase Authentication')]");
	By codeTxt = By.xpath("//*[@id=\"content\"]/div[2]/form[1]/input[1]");
	By otp_submitBtn = By.xpath("//*[@id=\"content\"]/div[2]/form[1]/input[@value='SUBMIT']");

	// Elements related to VISANew
	By visaHeaderLbl = By.xpath(
			"//app-payment-detail-form/div/div/div/div/div[1]/div[2]/form/app-email-address/div/div/h5[contains(text(),'Customer information')]");
	By visaEmailId = By.xpath("//*[@id=\"email\"]");
	By visaCardHolderNameTxt = By.xpath("//*[@id=\"nameOnCard\"]");
	By visaCardNumberTxt = By.xpath("//*[@id=\"number\"]");
	By visaExpiryMonth1 = By.xpath("//*[@id=\"expiryMonth\"]");
	By visaExpiryYear1 = By.xpath("//*[@id=\"expiryYear\"]");
	By visaSecurityCode = By.xpath("//*[@id=\"securityCode\"]");
	By visaPayBtn = By.xpath("//*[@id=\"pay-button\"]");

	By acsEmulatorHeader = By
			.xpath("//*[@id=\"ContainerHeader\"]/table/tbody/tr/td/center/h1[contains(text(),'ACS Emulator')]");
	By visaSubmitBtn = By
			.xpath("//*[@id=\"ContainerContent\"]/center/form/table/tbody/tr[13]/td/input[@value='Submit']");

	// Elements of review pop up
	By reviewHeader = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtFeedbackMatters\"]");
	By reviewCancelBtn = By.xpath("//android.widget.TextView[@resource-id=\"com.app.salamtek:id/txtCancel\"]");

	public String dl_bookingID = "";
	public String s_orderID = "";
	Double actualTotalAmountForPharmacy = 0.0;

	public CheckoutPage(AppiumDriver<MobileElement> appiumDriver) {
		super();
	}

	public Boolean verifyPromoCode(String promoCode, String discount, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(promotionCodeTxt));
			driver.findElement(promotionCodeTxt).sendKeys(promoCode);
			driver.findElement(promotionCodeTxt).click();
			explicitWait(driver, driver.findElement(applyNowBtn));
			driver.findElement(applyNowBtn).click();
			String subTotal = driver.findElement(subTotalTxt).getText().trim();
			double actualSubTotalAmount = Double.parseDouble(subTotal.substring(subTotal.lastIndexOf(" ") + 1));
			double promoDiscount = Double.parseDouble(discount);
			String str_discount = driver.findElement(discountTxt).getText().trim();
			double actualDiscount = Double.parseDouble(str_discount.substring(str_discount.lastIndexOf(" ") + 1));
			System.out.println("Actual Discount : " + actualDiscount);
			double expectedDiscount = actualSubTotalAmount * (promoDiscount / 100);
			System.out.println("Expected Discount : " + expectedDiscount);
			String totalAfterDiscountTxt = driver.findElement(totalAfterDiscount).getText().trim()
					.substring(driver.findElement(totalAfterDiscount).getText().trim().lastIndexOf(" ") + 1);
			if (actualDiscount == expectedDiscount) {
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("actualDiscount",
						str_discount.substring(str_discount.lastIndexOf(" ") + 1));
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("totalAmountBeforeDiscount",
						subTotal.substring(subTotal.lastIndexOf(" ") + 1));
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("totalAmountAfterDiscount", totalAfterDiscountTxt);
				result = true;
				takeScreenShot(driver, "PromotionForPharmacy", tcID, sheetName);
			} else {
				result = false;
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyCheckout(String paymentMode, String payOnlyBookingFee, String promoCode, String discount,
			String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			explicitWait(driver, driver.findElement(checkoutLbl));
			if (driver.findElement(checkoutLbl).isDisplayed()) {
				if (!promoCode.equalsIgnoreCase("")) {
					driver.findElement(MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"% Promotion Code\").instance(0))"));
					result = verifyPromoCode(promoCode, discount, sheetName, tcID);
					if (result == false) {
						Assert.assertEquals(result, true);
					} else {
						result = true;
					}
				}
				if (!paymentMode.equalsIgnoreCase("WALLET")) {
					driver.findElement(MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"com.app.salamtek:id/imgPayment\").instance(0))"));
					swipeDown(599, 2050, 608, 1755);
				}
				if (payOnlyBookingFee.equalsIgnoreCase("Yes")) {
					driver.findElement(reservationFeeChk).click();
				}
				switch (paymentMode.trim()) {
				case "CASH":
					explicitWait(driver, driver.findElement(cashModeBtn));
					driver.findElement(cashModeBtn).click();
					break;
				case "KNET":
					if (payOnlyBookingFee.equalsIgnoreCase("Yes")) {
						explicitWait(driver, driver.findElement(cashModeBtn));
						driver.findElement(cashModeBtn).click();
						break;
					}
					explicitWait(driver, driver.findElement(knetModeBtn));
					driver.findElement(knetModeBtn).click();
					break;
				case "VISA":
					if (payOnlyBookingFee.equalsIgnoreCase("Yes")) {
						explicitWait(driver, driver.findElement(knetModeBtn));
						driver.findElement(knetModeBtn).click();
						break;
					}
					driver.findElement(MobileBy
							.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
									+ "new UiSelector().resourceId(\"com.app.salamtek:id/imgRadio\"))"));
					explicitWait(driver, driver.findElement(ccModeBtn));
					driver.findElement(ccModeBtn).click();
					break;
				case "WALLET":
					driver.findElement(MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PAY WITH MY WALLET\").instance(0))"));
					explicitWait(driver, driver.findElement(walletBtn));
					driver.findElement(walletBtn).click();
					break;
				}
				result = true;
				Assert.assertEquals(result, true);
				takeScreenShot(driver, "DoctorCheckoutPage", tcID, sheetName);
				driver.findElement(proceedBtn).click();
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean enterKNETDetails(String bankName, String cardNumber, String expiryMonth, String expiryYear,
			String pin, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			waitSometime();
			click_IfDisplayed(futureAppointmentAlert, futureAlertProceedBtn);
			waitSometime();
			Set<String> contextNames = driver.getContextHandles();
			System.out.println(contextNames);
			for (String conName : contextNames) {
				System.out.println(conName);
				if (conName.contains("WEBVIEW_com.app.salamtek")) {
					driver.context(conName);
					Set<String> windows = driver.context(conName).getWindowHandles();
					ArrayList<String> tabs = new ArrayList<String>(windows);
					System.out.println("How many windows: " + tabs.size());
					for (String t : tabs) {
						// Checking for the current active payment and switching to that window.
						if (driver.switchTo().window(t).getCurrentUrl().contains("PaymentID")) {
							System.out.println("Ready to enter KNET details");
							driver.switchTo().window(t);
							break;
						} else {
							continue;
						}
					}
					break;
				}
			}
			waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetBankName));
			Select banks = new Select(driver.findElement(knetBankName));
			banks.selectByValue(bankName);
			waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetDebitNumber));
			driver.findElement(knetDebitNumber).sendKeys(cardNumber);
			// waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetExpiryMonth));
			Select expiryMonth1 = new Select(driver.findElement(knetExpiryMonth));
			expiryMonth1.selectByValue(expiryMonth);
			// waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetExpiryYear));
			Select expiryYear1 = new Select(driver.findElement(knetExpiryYear));
			expiryYear1.selectByValue(expiryYear);
			// waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetPin));
			driver.findElement(knetPin).sendKeys(pin);
			// waitSometime();
			explicitWaitForWeb(driver, driver.findElement(knetSubmitBtn));
			takeScreenShot(driver, "KNETEntryMainPage", tcID, sheetName);
			driver.findElement(knetSubmitBtn).click();
			// waitForSpecificTime(5000);
			explicitWaitForWeb(driver, driver.findElement(knetConfirmBtn));
			takeScreenShot(driver, "KNETEntryConfirmPage", tcID, sheetName);
			driver.findElement(knetConfirmBtn).click();
			waitForSpecificTime(5000);
			for (String conName : contextNames) {
				System.out.println(conName);
				if (conName.contains("NATIVE")) {
					driver.context(conName);
					break;
				}
			}
			result = driver.findElement(bookedSuccess).isDisplayed();
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean enterVISADetails(String firstName, String lastName, String address, String city, String country,
			String cardNumber, String expiryMonth, String expiryYear, String cardHolderName, String CVV,
			String username, String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			waitSometime();
			click_IfDisplayed(futureAppointmentAlert, futureAlertProceedBtn);
			waitForSpecificTime(15000);
			Set<String> contextNames = driver.getContextHandles();
			System.out.println(contextNames);
			for (String conName : contextNames) {
				System.out.println(conName);
				if (conName.contains("WEBVIEW_com.app.salamtek")) {
					driver.context(conName);
					Set<String> windows = driver.context(conName).getWindowHandles();
					ArrayList<String> tabs = new ArrayList<String>(windows);
					System.out.println("How many windows: " + tabs.size());
					for (String t : tabs) {
						// Checking for the current active payment and switching to that window.
						if (driver.switchTo().window(t).getCurrentUrl().contains("/checkout")) {
							System.out.println("URL: " + driver.switchTo().window(t).getCurrentUrl());
							System.out.println("Ready to enter VISA details");
							driver.switchTo().window(t);
							break;
						} else {
							continue;
						}
					}
					break;
				}
			}
			// waitSometime();
//			explicitWait(driver, driver.findElement(firstNameTxt));
//			driver.findElement(firstNameTxt).sendKeys(firstName);
//			// waitSometime();
//			explicitWait(driver, driver.findElement(lastNameTxt));
//			driver.findElement(lastNameTxt).sendKeys(lastName);
//			// waitSometime();
			explicitWait(driver, driver.findElement(cardType_visa));
			driver.findElement(cardType_visa).click();
			// waitSometime();
			explicitWait(driver, driver.findElement(visaCardNumber));
			driver.findElement(visaCardNumber).sendKeys(cardNumber);
			// waitSometime();
			explicitWait(driver, driver.findElement(visaExpiryMonth));
			Select expiryMonth1 = new Select(driver.findElement(visaExpiryMonth));
			expiryMonth1.selectByValue(expiryMonth);
			// waitSometime();
			explicitWait(driver, driver.findElement(visaExpiryYear));
			Select expiryYear1 = new Select(driver.findElement(visaExpiryYear));
			expiryYear1.selectByValue(expiryYear);
			// waitSometime();
			explicitWait(driver, driver.findElement(visaCVV));
			driver.findElement(visaCVV).sendKeys(CVV);
			waitSometime();
			// driver.findElement(payBtn1).click();
			driver.findElement(nextBtn).click();
			waitSometime();
			waitSometime();
			explicitWait(driver, driver.findElement(reviewOrderHeader));
			driver.findElement(payBtn).click();
			waitSometime();
			waitSometime();
			waitForSpecificTime(15000);
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"Cardinal-CCA-IFrame\"]")));
			explicitWait(driver, driver.findElement(purchaseAuthHeader));
			driver.findElement(codeTxt).sendKeys("1234");
			driver.findElement(otp_submitBtn).click();
			driver.switchTo().defaultContent();
//			explicitWait(driver, driver.findElement(payBtn));
//			takeScreenShot(driver, "VISAReviewPage", tcID, sheetName);
//			driver.findElement(payBtn).click();

//			waitSometime();
//			explicitWaitForWeb(driver, driver.findElement(visaEmailId));
//			driver.findElement(visaEmailId).clear();
//			driver.findElement(visaEmailId).sendKeys(username);
//			waitForSpecificTime(5000);
//			driver.switchTo().frame(0);
//			explicitWaitForWeb(driver, driver.findElement(visaCardHolderNameTxt));
//			driver.findElement(visaCardHolderNameTxt).sendKeys(cardHolderName);
//			driver.switchTo().defaultContent();
//			waitSometime();
//			driver.switchTo().frame(1);
//			driver.findElement(visaCardNumberTxt).sendKeys(cardNumber);
//			driver.switchTo().defaultContent();
//			waitSometime();
//			driver.switchTo().frame(2);
//			driver.findElement(visaExpiryMonth1).sendKeys(expiryMonth);
//			driver.switchTo().defaultContent();
//			waitSometime();
//			driver.switchTo().frame(3);
//			driver.findElement(visaExpiryYear1).sendKeys(expiryYear);
//			driver.switchTo().defaultContent();
//			waitSometime();
//			driver.switchTo().frame(4);
//			driver.findElement(visaSecurityCode).sendKeys(CVV);
//			driver.switchTo().defaultContent();
//			waitSometime();
//			driver.findElement(visaPayBtn).click();
//			waitSometime();
//			waitSometime();
//			explicitWait(driver, driver.findElement(By.xpath(".//iframe[@name='redirectTo3ds1Frame']")));
//			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@name='redirectTo3ds1Frame']")));
//			explicitWaitForWeb(driver, driver.findElement(acsEmulatorHeader));
//			driver.findElement(visaSubmitBtn).click();
//			driver.switchTo().defaultContent();

			waitForSpecificTime(20000);
			for (String conName : contextNames) {
				System.out.println(conName);
				if (conName.contains("NATIVE")) {
					driver.context(conName);
					break;
				}
			}
			result = driver.findElement(bookedSuccess).isDisplayed();
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyBookingSuccess(String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			click_IfDisplayed(futureAppointmentAlert, futureAlertProceedBtn);
			explicitWait(driver, driver.findElement(bookedSuccess));
			if (driver.findElement(bookedSuccess).isDisplayed()) {
				takeScreenShot(driver, "BookingSuccess", tcID, sheetName);
				dl_bookingID = driver.findElement(bookingID).getText().trim();
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("bookingID", dl_bookingID);
				result = driver.findElement(bookedSuccess).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyOrderSuccess(String sheetName, String tcID) throws Exception {
		boolean result = false;
		try {
			click_IfDisplayed(futureAppointmentAlert, futureAlertProceedBtn);
			explicitWait(driver, driver.findElement(bookedSuccess));
			if (driver.findElement(bookedSuccess).isDisplayed()) {
				takeScreenShot(driver, "OrderSuccess", tcID, sheetName);
				s_orderID = driver.findElement(bookingID).getText().trim();
				excelUtils.transferTCID_SheetName(tcID, sheetName);
				excelUtils.updateValueToExcel("orderID", s_orderID);
				result = driver.findElement(bookedSuccess).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Boolean verifyBackHome() {
		boolean result = false;
		try {
			driver.findElement(backToHomeBtn).click();
			verifyReviewCancel();
			explicitWait(driver, driver.findElement(quickServiceLink));
			result = driver.findElement(quickServiceLink).isDisplayed();
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	// Verify rescheduling an appointment
	public Boolean verifyReschedule(String sheetname, String tcID) {
		boolean result = false;
		try {
			if (driver.findElement(bookedSuccess).isDisplayed()) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"MANAGE BOOKING\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(manageBookingBtn));
				driver.findElement(manageBookingBtn).click();
				explicitWait(driver, driver.findElement(this.bookingID));
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"RESCHEDULE\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(rescheduleBtn));
				driver.findElement(rescheduleBtn).click();
				// waitSometime();
				explicitWait(driver, driver.findElement(this.bookingID));
				swipeDown(525, 1991, 488, 1236);
				waitSometime();
				explicitWait(driver, driver.findElement(rescheduleBtn));
				if (driver.findElement(rescheduleBtn).isDisplayed()) {
					for (int i = 7; i <= 10; i++) {
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
							if (i == 10) {
								swipeDown(824, 1805, 9, 1809);
								i = 7;
							}
							continue;
						}
					}

				}
				// waitSometime();
				explicitWait(driver, driver.findElement(rescheduleBtn));
				takeScreenShot(driver, "RescheduleMainPage", tcID, sheetname);
				driver.findElement(rescheduleBtn).click();
				// waitForSpecificTime(5000);
				explicitWait(driver, driver.findElement(successMessagePopup));
				if (driver.findElement(successMessagePopup).getText().contains("appointment resheduled successfully")) {
					result = true;
				}
				takeScreenShot(driver, "Reschedule_Success", tcID, sheetname);
				driver.findElement(okBtn).click();
				// waitSometime();
				explicitWait(driver, driver.findElement(backToHomeBtn));
				driver.findElement(backToHomeBtn).click();
				verifyReviewCancel();
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

	// Verify canceling an appointment
	public Boolean verifyCancelAppointment(String paymentMode, String sheetname, String tcID) {
		boolean result = false;
		try {
			if (driver.findElement(bookedSuccess).isDisplayed()) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"MANAGE BOOKING\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(manageBookingBtn));
				driver.findElement(manageBookingBtn).click();
				explicitWait(driver, driver.findElement(this.bookingID));
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"CANCEL\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(cancelBtn));
				driver.findElement(cancelBtn).click();
				explicitWait(driver, driver.findElement(cancelAppointmentHeader));
				if (driver.findElement(cancelAppointmentHeader).getText().trim()
						.equalsIgnoreCase("CANCEL APPOINTMENT")) {
					if (paymentMode.equalsIgnoreCase("WALLET")) {
						driver.findElement(refundWalletBtn).click();
					} else {
						driver.findElement(refundBankBtn).click();
					}
					driver.findElement(cancelProceedBtn).click();
				}
				explicitWait(driver, driver.findElement(refundMessageTxt));
				String expectedRefundMessage = driver.findElement(refundMessageTxt).getText().trim();
				System.out.println("expectedRefundMessage: " + expectedRefundMessage);
				excelUtils.transferTCID_SheetName(tcID, sheetname);
				excelUtils.updateValueToExcel("refundMessage", expectedRefundMessage);
				if (paymentMode.equalsIgnoreCase("WALLET")) {
					Double expectedRefundAmount = Double
							.parseDouble(expectedRefundMessage.substring(expectedRefundMessage.lastIndexOf(" ") + 1));
					System.out.println("Expected Refund Amount: " + expectedRefundAmount);
					excelUtils.transferTCID_SheetName(tcID, sheetname);
					excelUtils.updateValueToExcel("refundAmount",
							expectedRefundMessage.substring(expectedRefundMessage.lastIndexOf(" ") + 1));
				}
				driver.findElement(doneBtn).click();
				verifyReviewCancel();
				explicitWait(driver, driver.findElement(quickServiceLink));
				result = driver.findElement(quickServiceLink).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	// Verify canceling an appointment
	public Boolean verifyCancelOrder(String paymentMode, String sheetname, String tcID) {
		boolean result = false;
		try {
			if (driver.findElement(bookedSuccess).isDisplayed()) {
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"MANAGE ORDER\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(manageOrderBtn));
				driver.findElement(manageOrderBtn).click();
				explicitWait(driver, driver.findElement(this.bookingID));
				driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"CANCEL\").instance(0))"));
				// waitSometime();
				explicitWait(driver, driver.findElement(cancelOrderBtn));
				driver.findElement(cancelOrderBtn).click();
				explicitWait(driver, driver.findElement(cancelAppointmentHeader));
				if (driver.findElement(cancelAppointmentHeader).getText().trim().equalsIgnoreCase("CANCEL ORDER")) {
					if (paymentMode.equalsIgnoreCase("WALLET")) {
						driver.findElement(refundWalletBtn).click();
					} else {
						driver.findElement(refundBankBtn).click();
					}
					driver.findElement(cancelProceedBtn).click();
				}
				explicitWait(driver, driver.findElement(refundMessageTxt));
				String expectedRefundMessage = driver.findElement(refundMessageTxt).getText().trim();
				System.out.println("expectedRefundMessage: " + expectedRefundMessage);
				excelUtils.transferTCID_SheetName(tcID, sheetname);
				excelUtils.updateValueToExcel("refundMessage", expectedRefundMessage);
				if (paymentMode.equalsIgnoreCase("WALLET")) {
					Double expectedRefundAmount = Double
							.parseDouble(expectedRefundMessage.substring(expectedRefundMessage.lastIndexOf(" ") + 1));
					System.out.println("Expected Refund Amount: " + expectedRefundAmount);
					excelUtils.transferTCID_SheetName(tcID, sheetname);
					excelUtils.updateValueToExcel("refundAmount",
							expectedRefundMessage.substring(expectedRefundMessage.lastIndexOf(" ") + 1));
				}
				driver.findElement(doneBtn).click();
				driver.findElement(backBtn).click();
				driver.findElement(backToHomeBtn).click();
				verifyReviewCancel();
				explicitWait(driver, driver.findElement(quickServiceLink));
				result = driver.findElement(quickServiceLink).isDisplayed();
				Assert.assertEquals(result, true);
			}
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
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
