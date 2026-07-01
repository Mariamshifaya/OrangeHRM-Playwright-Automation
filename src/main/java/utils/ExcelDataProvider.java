package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        String filePath = "src/test/resources/LoginData.xlsx";

        return ExcelUtils.getExcelData(filePath, "Login");
    }
}