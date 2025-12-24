package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.PageClass1;
import utility.ExcelFileManager;

@Listeners(base.TestListener.class)
public class TestClass4 extends BaseClass {

    @Test
    public void testCase1() {
    	 PageClass1 loginPage = new PageClass1();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        System.out.println("✅ Username: " + username + " | Password: " + password);
        
        PageClass1 loginPage = new PageClass1();
        loginPage.login(username, password);
       
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        String filePath = "/testdata/LoginData.xlsx";  // Ensure this file exists in your project
        String sheetName = "Sheet1";

        // Replace hardcoded values with dynamic logic if needed
        int rowCount = 2;
        int colCount = 2;

        Object[][] data = new Object[rowCount - 1][colCount];  // Skip header (row 0)

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = ExcelFileManager.getCellData(filePath, sheetName, i, j);
            }
        }

        return data;
    }
}
