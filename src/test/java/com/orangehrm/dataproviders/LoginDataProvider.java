package com.orangehrm.dataproviders;

import com.orangehrm.utilities.ExcelUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String filePath = "src/test/resources/testdata/LoginData.xlsx";
        String sheetName = "LoginData";

        return ExcelUtils.readExcelData(filePath, sheetName);
    }
}