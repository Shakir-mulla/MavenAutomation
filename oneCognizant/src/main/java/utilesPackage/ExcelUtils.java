package utilesPackage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String filePath;
	public static FileInputStream workFile;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String[] searchInput;

	// Reading the search input data from the Excel Sheet
	public String[] readExcelData() throws Exception {

		filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\inputdata\\InputData.xlsx";
		File f = new File(filePath);
		workFile = new FileInputStream(f);
		workbook = new XSSFWorkbook(workFile);

		sheet = workbook.getSheet("Data");
		row = sheet.getRow(0);
		String value1 = row.getCell(0).toString();
		String value2 = row.getCell(1).toString();
		String value3 = row.getCell(2).toString();
		String value4 = row.getCell(3).toString();
		String value5 = row.getCell(4).toString();
		
		searchInput=new String[] {value1,value2,value3,value4,value5}; 

		return searchInput;
		
	}
}
