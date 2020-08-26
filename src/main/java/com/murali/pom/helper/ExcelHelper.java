package com.murali.pom.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper {
	private Logger log = Logger.getLogger(ExcelHelper.class);

	public Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object dataSets[][]= null;
			FileInputStream file = new FileInputStream(new File(excelLocation));

			//create workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//get sheet name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			//count no of active rows in excel sheet
			int totalRows = sheet.getLastRowNum();

			// count no of active columns in excel sheet
			int totalColumns = sheet.getRow(0).getLastCellNum();

			dataSets = new Object[totalRows+1][totalColumns];

			//iterate through each row one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while(rowIterator.hasNext()) {
				i++;
				// for every row we need to iterate over columns
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while(cellIterator.hasNext()) {
					j++;
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case STRING:
						dataSets[i-1][j-1] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i-1][j-1] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i-1][j-1] = cell.getBooleanCellValue();
						break;
					case FORMULA:
						dataSets[i-1][j-1] = cell.getCellFormula();
						break;
					default:
						System.out.println("No matching enum data type found..");
						break;
					}
				}
			}
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus) {
		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));

			//create workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//get sheet name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);	
			int totalRows = sheet.getLastRowNum();
			for(int i =1; i<totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
				String cell =row.getCell(0).getStringCellValue();
				if(cell.contains(testCaseName)) {
					row.createCell(1).setCellValue(testStatus);
					file.close();
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
			
				}
			}
		}catch(Exception e) {
	}
	}
	/**public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		String excelLocation = ResourceHelper.getResourcePath("/src/main/resources/testData/Login.xlsx");
		Object[][] data = excelHelper.getExcelData(excelLocation, "Sheet1");
		System.out.println(data);
	}
	 **/
}
