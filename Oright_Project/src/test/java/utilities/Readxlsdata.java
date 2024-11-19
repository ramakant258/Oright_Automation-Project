package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Readxlsdata {
	public static void main(String args[]) throws EncryptedDocumentException, IOException {
		Readxlsdata red = new Readxlsdata();
		red.getData("login");
}
	public String[][] getData(String login)throws EncryptedDocumentException, IOException
	{
	File f= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.xlsx.xlsx");
	FileInputStream fis = new FileInputStream(f);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sheetname = wb.getSheet(login);
	int totalRows = sheetname.getLastRowNum();
	System.out.println(totalRows);;
	Row rowCells = sheetname.getRow(0);
	int totalCols = rowCells.getLastCellNum();
	System.out.println(totalCols);
	 DataFormatter format = new DataFormatter();
	 String testData[][] =new String[totalRows][totalCols];
	 for(int i=1;i<=totalRows; i++) {
		 for(int j=1;j<totalCols; j++) {
	 testData[i-1][j] =format.formatCellValue(sheetname.getRow(i).getCell(j));
	 System.out.println(testData[i-1][j]);}
	}

	return testData;
}
}