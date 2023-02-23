package com.exelenter.testcases;

import com.exelenter.base.BaseClass;
import com.exelenter.utils.Constants;
import com.exelenter.utils.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginTestNegative extends BaseClass {
    @Test(dataProvider = "loginData")
    public void userLogin(String username, String password,String expectedErrorMessage ){
        sendText(loginPage.username, username);
        System.out.println("username = " + username);
        sendText(loginPage.password, password);
        System.out.println("password = " + password);
        click(loginPage.loginBtn);
        Assert.assertEquals(loginPage.LoginErrorMessage.getText(), expectedErrorMessage, "Error message is incorrect");
        System.out.println(expectedErrorMessage);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        //String filePath = ExcelUtility.projectPath+"/testData/HW_user_login_tests.xlsx";
        return ExcelUtility.readFromExcel(Constants.TESTDATA_FILEPATH,"testler");
    }

    @DataProvider
    Object[][] getData() {
        return new Object[][]{
                {"Admin", "invalidPass", "Invalid credentials"},           // valid user     invalid password
                {"admi123", "Exelent2022Sdet!", "Invalid credentials"},    // invalid user   valid password
                {"admi123", "invalidPass", "Invalid credentials"},         // invalid user   invalid password
                {"Admin", "", "Password cannot be empty"},                 // valid user     empty password
                {"Admi123", "", "Password cannot be empty"},               // invalid        empty
                {"", "Exelent2022Sdet!", "Username cannot be empty"},      // empty          valid
                {"", "invalidPass", "Username cannot be empty"},           // empty          invalid
                {"", "", "Username cannot be empty"}                       // empty          empty
        };
    }
}
