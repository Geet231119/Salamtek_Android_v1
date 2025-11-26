package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import Common.BaseTest;

public class ExcelUtils extends BaseTest {
	String tcID, sheetName;
	Fillo fillo = new Fillo();
	Connection connection;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	// To retrieve test case ID from TestData excel sheet
	public List<String> getTestCaseID(String tcName, String sheetName) {
		List<String> tcList = new ArrayList<String>();
		String excelSheet = getExcelSheetName();
		System.out.println("Excel sheet Path: " + excelSheet);
		try {
			FileInputStream fs = new FileInputStream(excelSheet);
			try {
				workbook = new XSSFWorkbook(fs);
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
						System.out.println("SheetName: " + workbook.getSheetName(i));
						sheet = workbook.getSheetAt(i);
					}
				}
				int lastRow = sheet.getLastRowNum() + 1;
				System.out.println("Last Row of the sheet: " + lastRow);
				for (int i = 1; i < lastRow; i++) {
					String executionStatus = sheet.getRow(i).getCell(3).getStringCellValue();
					String testCaseName = sheet.getRow(i).getCell(1).getStringCellValue();
					System.out.println("Execution Status Value: " + sheet.getRow(i).getCell(3).getStringCellValue());
					if (executionStatus.equalsIgnoreCase("Y") && testCaseName.equalsIgnoreCase(tcName)) {
						tcList.add(sheet.getRow(i).getCell(0).getStringCellValue());
					}
				}
				workbook.close();
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tcList;
	}

	// Store testCase ID and sheet name from the TestData excel
	public void transferTCID_SheetName(String testCaseID, String sheetName) {
		tcID = testCaseID;
		this.sheetName = sheetName;
	}

	// Retrieve value from TestData excel corresponding to the specified column name
	public String getValue(String searchValue) {
		try {
			connection = fillo.getConnection(getExcelSheetName());
		} catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String value = null;
		String query = "select * from " + sheetName + " where TestCaseID='" + tcID + "'";
		System.out.println(query);
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery(query);
			while (recordset.next()) {
				value = recordset.getField(searchValue);
				System.out.println(value);
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recordset.close();
		connection.close();
		return value;

	}

	// Update the excel with the result as PASS or FAIL
	public void updateResult(Boolean testResult) {
		String query;
		try {
			connection = fillo.getConnection(getExcelSheetName());
		} catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (testResult.equals(true))
			query = "Update " + sheetName + " set Status='PASS' where TestCaseID='" + tcID + "'";
		else
			query = "Update " + sheetName + " set Status='FAIL' where TestCaseID='" + tcID + "'";
		System.out.println(query);
		try {
			connection.executeUpdate(query);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();

	}

	// Update excel with the actual value after test execution to corresponding
	// fields
	public void updateValueToExcel(String field, String value) {
		String query;
		try {
			connection = fillo.getConnection(getExcelSheetName());
		} catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		query = "Update " + sheetName + " set " + field + "='" + value + "' where TestCaseID='" + tcID + "'";
		System.out.println(query);
		try {
			connection.executeUpdate(query);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();

	}
}
