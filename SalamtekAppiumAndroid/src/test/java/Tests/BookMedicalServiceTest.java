package Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Common.BaseTest;
import Pages.AccountPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LabBookingPage;
import Pages.LabMainPage;
import Pages.LabServiceSelectionPage;
import Pages.LoginPage;
import Pages.NotificationPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class BookMedicalServiceTest extends BaseTest {

	LoginPage loginPage = new LoginPage(getDriver());
	HomePage homePage = new HomePage(getDriver());
	LabMainPage labMainPage = new LabMainPage(getDriver());
	LabServiceSelectionPage labServiceSelectionPage = new LabServiceSelectionPage(getDriver());
	LabBookingPage labBookingPage = new LabBookingPage(getDriver());
	CheckoutPage checkoutPage = new CheckoutPage(getDriver());
	NotificationPage notificationPage = new NotificationPage(getDriver());
	AccountPage accountPage = new AccountPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "BookMedicalService";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "verify whether user is able to proceed booking medical service appointment_Profile_Wallet", dataProvider = "dataForMedicalService")
	public void BookingMedicalAppointment_Profile_Wallet(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.selectLab(medicalServiceName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labServiceSelectionPage.selectLab(medicalServiceName, profileName, profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.selectTimeSlot(profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.verifyBookingStatement(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyCheckout(paymentMode, payOnlyBookingFee, promoCode, discount,sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBackHome();
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = notificationPage.verifyNotification(sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	@Test(description = "verify whether user is able to reschedule medical service appointment_Profile_VISA", dataProvider = "dataForMedicalService")
	public void BookingMedicalAppointment_Reschedule(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.selectLab(medicalServiceName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labServiceSelectionPage.selectLab(medicalServiceName, profileName, profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.selectTimeSlot(profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.verifyBookingStatement(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyCheckout(paymentMode, payOnlyBookingFee, promoCode, discount,sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		if (paymentMode.equalsIgnoreCase("KNET")) {
			testResult = checkoutPage.enterKNETDetails(bankName, cardNumber, cardExpiryMonth, cardExpiryYear, pin,
					sheetName, testCaseID);
		} else if(paymentMode.equalsIgnoreCase("VISA")) {
			testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
					cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username, sheetName, testCaseID);
		}
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyReschedule(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = notificationPage.verifyNotification(sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test(description = "verify whether user is able to cancel medical service appointment_Profile_VISA", dataProvider = "dataForMedicalService")
	public void BookingMedicalAppointment_Cancel(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		if (paymentMode.equalsIgnoreCase("WALLET")) {
			testResult = accountPage.verifyFirstWalletValue(sheetName, testCaseID);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.selectLab(medicalServiceName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labServiceSelectionPage.selectLab(medicalServiceName, profileName, profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.selectTimeSlot(profileTestName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labBookingPage.verifyBookingStatement(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyCheckout(paymentMode, payOnlyBookingFee, promoCode, discount,sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		if (paymentMode.equalsIgnoreCase("KNET")) {
			testResult = checkoutPage.enterKNETDetails(bankName, cardNumber, cardExpiryMonth, cardExpiryYear, pin,
					sheetName, testCaseID);
		} else if (paymentMode.equalsIgnoreCase("VISA")) {
			testResult = checkoutPage.enterVISADetails(firstName, lastName, address, city, country, cardNumber,
					cardExpiryMonth, cardExpiryYear, cardHolderName, CVV, username, sheetName, testCaseID);
		}
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyBookingSuccess(sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = checkoutPage.verifyCancelAppointment(paymentMode, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		if (paymentMode.equalsIgnoreCase("WALLET")) {
			testResult = accountPage.verifyWalletValueAfterCancellation(sheetName, testCaseID);
			if (testResult.equals(false)) {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
		}
		testResult = notificationPage.verifyNotification(sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test(description = "verify radiology section", dataProvider = "dataForMedicalService")
	public void RadiologySection(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		clickBack();
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test(description = "Verify whether user gets filtered results while filtering upon each sections", dataProvider = "dataForMedicalService")
	public void Radiology_Filter(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.verifyRadiologyFilter(filterBy, filterValue, filterResult, sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test(description = "Verify whether user gets filtered results while filtering upon each sections", dataProvider = "dataForMedicalService")
	public void Beauty_Filter(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.verifyBeautyFilter(filterBy, filterValue, filterResult, sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test(description = "Verify whether user gets filtered results while filtering upon each sections", dataProvider = "dataForMedicalService")
	public void Lab_Filter(String testCaseID, String testCaseName, String description,
			String executionStatus, String username, String password, String operationName, String sectionName,
			String medicalServiceSearchName, String medicalServiceName,
			String profileName, String profileTestName, String paymentMode,
			String payOnlyBookingFee, String bankName, String firstName, String lastName, String address, String city,
			String country, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardHolderName,
			String CVV, String pin, String service, String filterBy, String filterValue,
			String filterResult, String promoCode, String discount) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = homePage.selectOperation(operationName, sheetName, testCaseID);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = labMainPage.verifyLabFilter(filterBy, filterValue, filterResult, sheetName, testCaseID);
		try {
			if (testResult.equals(true)) {
				excelUtils.updateResult(true);
				Assert.assertEquals(testResult, true);
			} else {
				excelUtils.updateResult(false);
				Assert.assertEquals(testResult, true);
			}
			System.out.println("Test is working fine");
		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}

	}
	@DataProvider(name = "dataForMedicalService")
	public Object[][] getData(Method m) throws IOException {
		int countIteration = 0, cnt = 0;
		String excelSheet = getExcelSheetName();
		System.out.println("ExcelSheetName : " + excelSheet);
		FileInputStream fs = new FileInputStream(excelSheet);
		workbook = new XSSFWorkbook(fs);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				System.out.println("SheetName: " + workbook.getSheetName(i));
				sheet = workbook.getSheetAt(i);
				break;
			}
		}
		row = sheet.getRow(0);
		int lastRow = sheet.getLastRowNum() + 1; // get last RowNum
		int ColNum = row.getLastCellNum(); // get last ColNum
		System.out.println("Last Row of the sheet: " + lastRow);
		for (int i = 1; i < lastRow; i++) {
			String executionStatus = sheet.getRow(i).getCell(3).getStringCellValue();
			String testCaseName = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("Execution Status Value: " + sheet.getRow(i).getCell(3).getStringCellValue());
			if (executionStatus.equalsIgnoreCase("Y") && testCaseName.equalsIgnoreCase(m.getName())) {
				countIteration = countIteration + 1;
			}
		}
		Object[][] data = new Object[countIteration][ColNum - 7];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 7; j++) {
					cell = sheet.getRow(i1).getCell(j);
					if (cell == null)
						data[cnt][j] = "";
					else {
						System.out.println("Value: " + sheet.getRow(i1).getCell(j).getStringCellValue());
						data[cnt][j] = sheet.getRow(i1).getCell(j).getStringCellValue();
					}
				}
				cnt = cnt + 1;
			}
		}
		workbook.close();
		fs.close();
		return data;
	}
}
