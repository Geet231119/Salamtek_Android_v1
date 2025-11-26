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
import Pages.LoginPage;
import Pages.SearchPage;
import Utils.ExcelUtils;
import Utils.ExtentTestManager;

public class SearchTest extends BaseTest {

	LoginPage loginPage = new LoginPage(getDriver());
	SearchPage searchPage = new SearchPage(getDriver());

	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "Search";

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	@Test(description = "Verify whether the user is able to get the searched offer when providing the offer name in landing search", dataProvider = "dataForSearch")
	public void Search(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String typeOfSearch, String searchSection, String searchValue,
			String category, String deliveryOrPickup, String location, String price, String insurance, String gender, String language) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = searchPage.verifySearch(typeOfSearch, searchSection, searchValue, sheetName, testCaseID);
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
	
	@Test(description = "Verify whether the user is able to get the searched result from advanced search_All", dataProvider = "dataForSearch")
	public void AdvancedSearch_ALL(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String typeOfSearch, String searchSection, String searchValue,
			String category, String deliveryOrPickup, String location, String price, String insurance, String gender, String language) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = searchPage.verifyAdvancedSearch_All(typeOfSearch, searchValue, category, deliveryOrPickup, location, price, sheetName, testCaseID);
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
	
	@Test(description = "Verify whether the user is able to get the searched result from advanced search_specific", dataProvider = "dataForSearch")
	public void AdvancedSearch_Specific(String testCaseID, String testCaseName, String description, String executionStatus,
			String username, String password, String typeOfSearch, String searchSection, String searchValue,
			String category, String deliveryOrPickup, String location, String price, String insurance, String gender, String language) throws Exception {
		Boolean testResult = false;
		ExtentTestManager.startTest(testCaseID + "_" + description, description);
		excelUtils.transferTCID_SheetName(testCaseID, sheetName);
		testResult = loginPage.verifyLogin(username, password);
		if (testResult.equals(false)) {
			excelUtils.updateResult(false);
			Assert.assertEquals(testResult, true);
		}
		testResult = searchPage.verifyAdvancedSearch_Specific(typeOfSearch, searchValue, category, deliveryOrPickup, insurance, location, price, gender, language, sheetName, testCaseID);
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

	@DataProvider(name = "dataForSearch")
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
		Object[][] data = new Object[countIteration][ColNum - 1];
		for (int i1 = 1; i1 < lastRow; i1++) {
			String executionStatus1 = sheet.getRow(i1).getCell(3).getStringCellValue();
			String testCaseName1 = sheet.getRow(i1).getCell(1).getStringCellValue();
			if (executionStatus1.equalsIgnoreCase("Y") && testCaseName1.equalsIgnoreCase(m.getName())) {
				for (int j = 0; j < ColNum - 1; j++) {
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
