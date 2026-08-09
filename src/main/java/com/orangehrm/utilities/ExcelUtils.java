package com.orangehrm.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] readExcelData(
            String filePath,
            String sheetName) {

        try (
                FileInputStream fileInputStream =
                        new FileInputStream(filePath);

                Workbook workbook =
                        new XSSFWorkbook(fileInputStream)
        ) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                );
            }

            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            Object[][] data =
                    new Object[rowCount][columnCount];

            DataFormatter formatter = new DataFormatter();

            for (int rowIndex = 1;
                 rowIndex <= rowCount;
                 rowIndex++) {

                Row row = sheet.getRow(rowIndex);

                for (int columnIndex = 0;
                     columnIndex < columnCount;
                     columnIndex++) {

                    if (row == null ||
                            row.getCell(columnIndex) == null) {

                        data[rowIndex - 1][columnIndex] = "";

                    } else {

                        data[rowIndex - 1][columnIndex] =
                                formatter.formatCellValue(
                                        row.getCell(columnIndex)
                                );
                    }
                }
            }

            return data;

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath,
                    exception
            );
        }
    }
}