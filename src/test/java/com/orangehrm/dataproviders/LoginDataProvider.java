package com.orangehrm.dataproviders;

import com.orangehrm.constants.FrameworkConstants;
import com.orangehrm.utilities.ExcelUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        return ExcelUtils.readExcelData(
                FrameworkConstants.LOGIN_EXCEL_PATH,
                FrameworkConstants.LOGIN_SHEET_NAME
        );
    }
}