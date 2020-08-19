package eon.qa.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/*
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 05 2020
	 */

	private static Sheet excelWSheet;
	private static Workbook excelWBook;
	private static Cell excelCell;
	private static Row excelRow;
	private static FileInputStream fin;
	private static String excelPath;
	private static HashMap<String, String> rowHashMap;
	private static DataFormatter dataFormatter;

	/*
	 * Method to set/load excel from file path
	 * 
	 * @param sheetName
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public static void setExcelFile(String workBookName, String sheetName) {
		try {
			setExcelPath(Data.EXCEL_FOLDER + workBookName + ".xlsx");
			fin = new FileInputStream(new File(getExcelPath()));
			excelWBook = new XSSFWorkbook(fin);
			excelWSheet = excelWBook.getSheet(sheetName);
			System.out.println("Opend excel workbook with sheetname: " + sheetName);
		} catch (FileNotFoundException e) {
			System.err.println("Excel File Not Found. Exception Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to load excel. Exception Message: " + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * Method to retrieve excel data in a hash map
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 */
	public static void getRowHashMapForSpecificColumn(int rowNum, int colNum) {
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(),
					dataFormatter.formatCellValue(excelWSheet.getRow(curRow).getCell(colNum)));
			curRow++;
		}
		System.out.println("Excel Data Map: " + rowHashMap);
	}

	/*
	 * Method to retrieve excel data in a hash map
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 */
	public static void getRowHashMapForSpecificColumn(int rowNum, String recordName) {
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(), dataFormatter
					.formatCellValue(excelWSheet.getRow(curRow).getCell(getColumnIndexForRecord(rowNum, recordName))));
			curRow++;
		}
		System.out.println("Excel Data Map: " + rowHashMap);
	}

	/*
	 * Method to retrieve excel data in a hash map
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 */
	public static void getColumnHashMapForSpecificRow(int rowNum, int colNum) {
		dataFormatter = new DataFormatter();
		HashMap<String, String> hashMap = new HashMap<>();
		for (int i = 0; i < getColumnUsed(rowNum); i++) {
			hashMap.put(excelWSheet.getRow(0).getCell(i).getStringCellValue(),
					dataFormatter.formatCellValue(excelWSheet.getRow(rowNum).getCell(i)));
		}
		System.out.println("Excel Data Map: " + hashMap);
	}

	/**
	 * Open Excel And Retrieve Values As JSON String
	 * 
	 * @param workbookName
	 * @param sheetName
	 * @param rowNum
	 * @param recordName
	 * @return
	 */
	public static String openExcelAndGetColumnValuesToJson(String workbookName, String sheetName, int rowNum,
			String recordName) {
		System.out.println("Opening Excel");
		setExcelFile(workbookName, sheetName);
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(), dataFormatter
					.formatCellValue(excelWSheet.getRow(curRow).getCell(getColumnIndexForRecord(rowNum, recordName))));
			curRow++;
		}
		System.out.println("Excel Data Map: " + rowHashMap);
		return CommonUtil.convertHashMapToJson(rowHashMap);
	}

	/**
	 * Open Excel And Retrieve Values As MAP
	 * 
	 * @param workbookName
	 * @param sheetName
	 * @param rowNum
	 * @param recordName
	 * @return
	 */
	public static void openExcelAndGetColumnValuesToMap(String workbookName, String sheetName, int rowNum,
			String recordName) {
		System.out.println("Opening Excel");
		setExcelFile(workbookName, sheetName);
		dataFormatter = new DataFormatter();
		int curRow = rowNum;
		Iterator<Row> iterator = excelWSheet.iterator();
		rowHashMap = new HashMap<>();
		while (iterator.hasNext()) {
			iterator.next();
			rowHashMap.put(excelWSheet.getRow(curRow).getCell(0).getStringCellValue(), dataFormatter
					.formatCellValue(excelWSheet.getRow(curRow).getCell(getColumnIndexForRecord(rowNum, recordName))));
			curRow++;
		}
		System.out.println("Excel Data Map: " + rowHashMap);
	}

	/*
	 * Method to retrieve excel column index using row number and cell value
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @return Cell Value
	 */
	public static int getColumnIndexForRecord(int rowNum, String recordName) {
		int colIndex;
		for (colIndex = 0; colIndex < getColumnUsed(rowNum); colIndex++) {
			if (getCellData(rowNum, colIndex).equalsIgnoreCase(recordName)) {
				break;
			}
		}
		return colIndex;
	}

	/*
	 * Method to retrieve excel cell data using row and column number
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @return Cell Value
	 */
	public static String getCellData(int rowNum, int colNum) {
		return excelWSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}

	/*
	 * Get Last Cell Number From Sheet
	 * 
	 * @return excelWSheet.getLastCellNum()
	 */
	public static int getColumnUsed(int rowNum) {
		return excelWSheet.getRow(rowNum).getLastCellNum();
	}

	/*
	 * Get Last Row Number From Sheet
	 * 
	 * @return excelWSheet.getLastRowNum()
	 */
	public static int getRowUsed() {
		return excelWSheet.getLastRowNum();
	}

	/*
	 * Get User Row Count From Sheet
	 * 
	 * @return rowCount
	 */
	public static int getRowCount() { // Row Count: excelSheet.getLastRowNum()-excelSheet,getFirstRowNum();
		Iterator<Row> iterator = excelWSheet.iterator();
		int rowCount = 0;
		while (iterator.hasNext()) {
			iterator.next();
			rowCount++;
		}

		return rowCount;
	}

	/*
	 * Method to close excel workbook
	 * 
	 * @param key
	 * 
	 * @return value
	 */
	public static String getValueForKey(String key) {
		System.out.println("Getting row value '" + rowHashMap.get(key) + "' for Key '" + key + "'");
		return rowHashMap.get(key);
	}

	/*
	 * Method to close excel workbook
	 * 
	 * @return hashMap
	 */
	public static HashMap<String, String> getRowHashMap() {
		return rowHashMap;
	}

	/*
	 * Method to set value to excel sheet
	 * 
	 * @param rowNum
	 * 
	 * @param colNum
	 * 
	 * @param value
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public static void setCellData(String excelPath, int rowNum, int colNum, String value) {
		System.out.println("Setting '" + value + "' to excel at row [" + rowNum + "] and column [" + colNum + "]");
		if (getRowUsed() < rowNum) {
			excelRow = excelWSheet.createRow(rowNum);
		}
		excelRow = excelWSheet.getRow(rowNum);
		excelCell = excelRow.getCell(colNum);

		if (excelCell == null) {
			excelCell = excelRow.createCell(colNum);
			excelCell.setCellValue(value);
		} else {
			excelCell.setCellValue(value);
		}

		// WRITING DATA TO EXCEL
		// TODO: Update Writing to Worksheet
		try {
			setExcelPath(excelPath);
			FileOutputStream fos = new FileOutputStream(getExcelPath());
			excelWBook.write(fos);
			fos.flush();
			fos.close();
			System.out.println("Saved '" + value + "' to excel at row [" + rowNum + "] and column [" + colNum + "]");
		} catch (FileNotFoundException e) {
			System.err.println("Excel file not found. Error Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to write to exel file. Error Message: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Method to close excel workbook
	 * 
	 * @exception IOException
	 */
	public static void closeWorkbook() {
		try {
			fin.close();
			System.out.println("Closed Workbook");
		} catch (IOException e) {
			System.err.println("Failed to close excel workbook. Error Message: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * /* Method to retrieve excel workbook location
	 * 
	 * @return excelPath
	 */
	public static String getExcelPath() {
		System.out.println("Getting Excel File Path: " + excelPath);
		return excelPath;
	}

	/*
	 * Method to set excel workbook location
	 * 
	 * @param excelPath
	 */
	public static void setExcelPath(String excelPath) {
		System.out.println("Setting Excel File Path Value To: " + excelPath);
		ExcelUtil.excelPath = excelPath;
	}

	public static void loadExcelData(String workBookName, String sheetName, String recordName) {
		setExcelFile(workBookName, sheetName);
		getRowHashMapForSpecificColumn(0, recordName);
		closeWorkbook();
	}

	/*
	 * Main method to test the utility
	 */
	public static void main(String args[]) {
		setExcelFile("", "");
		getRowHashMapForSpecificColumn(0, "Record-001");
		closeWorkbook();
		System.out.println("User Name: " + getValueForKey("User Name"));
	}
}
