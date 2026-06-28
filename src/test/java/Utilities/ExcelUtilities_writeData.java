package Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities_writeData {

    private static String filePath = System.getProperty("user.dir")+"/testdata/Login_Data.xlsx";  // You can keep it in your project folder

    // Write email & password into Excel
    public static void writeData(String email, String password) {
        try {
            File file = new File(filePath);
            Workbook workbook;
            Sheet sheet;

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("Register_Data");

                if (sheet == null) {
                    sheet = workbook.createSheet("Register_Data");
                }
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Register_Data");
            }

            // Find the last row number
            int lastRow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRow + 1);

            // Write email and password
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(email);

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(password);
            
            /*Cell cell3 = row.createCell(2);
            cell3.setCellValue(exp);*/

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();

            System.out.println("✅ Data written successfully: " + email + " | " + password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

