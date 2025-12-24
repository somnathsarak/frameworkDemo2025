package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelFileManager {

    /**
     * Reads data from a specific cell in the Excel sheet.
     *
     * @param filePath   Relative path to the Excel file
     * @param sheetName  Name of the sheet
     * @param rowNum     Row number (0-based)
     * @param colNum     Column number (0-based)
     * @return           Cell value as string
     */
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        String absolutePath = System.getProperty("user.dir") + filePath;

        try (FileInputStream fis = new FileInputStream(absolutePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";

            Row row = sheet.getRow(rowNum);
            if (row == null) return "";

            Cell cell = row.getCell(colNum);
            if (cell == null) return "";

            return new DataFormatter().formatCellValue(cell);

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read from Excel file: " + absolutePath, e);
        }
    }

    /**
     * Writes data to a specific cell and saves the file.
     *
     * @param filePath   Relative path to the Excel file
     * @param sheetName  Name of the sheet
     * @param rowNum     Row number (0-based)
     * @param colNum     Column number (0-based)
     * @param value      Data to write
     */
    public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String value) {
        String absolutePath = System.getProperty("user.dir") + filePath;

        try (FileInputStream fis = new FileInputStream(absolutePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            Cell cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);

            cell.setCellValue(value);

            // Write back to the same file
            try (FileOutputStream fos = new FileOutputStream(absolutePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to write to Excel file: " + absolutePath, e);
        }
    }
}
