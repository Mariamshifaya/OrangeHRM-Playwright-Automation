package tests;

import org.testng.annotations.Test;
import utils.ExcelUtils;

public class ExcelTest {

    @Test
    public void readExcel() {

        Object[][] data = ExcelUtils.getExcelData(
                "src/test/resources/LoginData.xlsx",
                "Login");

        for (Object[] row : data) {
            System.out.println(row[0] + " | " + row[1]);
        }
    }
}