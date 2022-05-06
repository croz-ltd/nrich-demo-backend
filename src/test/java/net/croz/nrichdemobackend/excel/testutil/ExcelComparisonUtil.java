package net.croz.nrichdemobackend.excel.testutil;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public final class ExcelComparisonUtil {

    private ExcelComparisonUtil() {
    }

    @SneakyThrows
    public static void compareExcelFiles(byte[] content, byte[] expectedContent) {
        Workbook resultWorkBook = null;
        Workbook expectedWorBook = null;

        try {
            resultWorkBook = WorkbookFactory.create(new ByteArrayInputStream(content));
            expectedWorBook = WorkbookFactory.create(new ByteArrayInputStream(expectedContent));

            assertThat(resultWorkBook.getNumberOfSheets()).isEqualTo(expectedWorBook.getNumberOfSheets());

            for (int i = 0; i < resultWorkBook.getNumberOfSheets(); i++) {
                compareSheet(resultWorkBook.getSheetAt(i), expectedWorBook.getSheetAt(i));
            }
        }
        finally {
            closeWorkbook(resultWorkBook);
            closeWorkbook(expectedWorBook);
        }
    }

    private static void compareSheet(Sheet resultSheet, Sheet expectedSheet) {
        assertThat(resultSheet.getPhysicalNumberOfRows()).isEqualTo(expectedSheet.getPhysicalNumberOfRows());

        for (int i = 0; i < resultSheet.getPhysicalNumberOfRows(); i++) {
            compareRow(resultSheet.getRow(i), expectedSheet.getRow(i));
        }
    }

    private static void compareRow(Row resultRow, Row expectedRow) {
        assertThat(resultRow.getPhysicalNumberOfCells()).isEqualTo(expectedRow.getPhysicalNumberOfCells());

        for (int i = 0; i < resultRow.getPhysicalNumberOfCells(); i++) {
            compareCell(resultRow.getCell(i), expectedRow.getCell(i));
        }
    }

    private static void compareCell(Cell resultCell, Cell expectedCell) {
        switch (resultCell.getCellType()) {
            case BOOLEAN:
                assertThat(resultCell.getBooleanCellValue()).isEqualTo(expectedCell.getBooleanCellValue());
                break;
            case NUMERIC:
                assertThat(resultCell.getNumericCellValue()).isEqualTo(expectedCell.getNumericCellValue());
                break;
            case BLANK:
            case STRING:
            case ERROR:
                assertThat(resultCell.getStringCellValue()).isEqualTo(expectedCell.getStringCellValue());
                break;
        }
    }

    @SneakyThrows
    private static void closeWorkbook(Workbook workbook) {
        if (workbook != null) {
            workbook.close();
        }
    }
}
