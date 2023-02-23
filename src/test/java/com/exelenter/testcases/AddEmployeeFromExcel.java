package com.exelenter.testcases;

import com.exelenter.base.BaseClass;
import com.exelenter.utils.Constants;
import com.exelenter.utils.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.exelenter.base.PageInitializer.loginPage;

public class AddEmployeeFromExcel extends BaseClass {
    @Test(dataProvider = "getItFromExcel")
    public void addUser(String firstName,String lastName,String userName,String passwrd){
        loginPage.loginToWebsite("username","password");
        wait(1);
        pimPage.navigateToAddEmployee();
        wait(1);
        sendText(addEmployeePage.firstName,firstName);
        sendText(addEmployeePage.lastName,lastName);
        String userId = addEmployeePage.employeeId.getAttribute("value");
        System.out.println("userId = " + userId);
        clickButwaitForClickability(addEmployeePage.createLoginDetailsCheckBox);
        sendText(addEmployeePage.username, userName);
        System.out.println("userName = " + userName);
        sendText(addEmployeePage.password,passwrd);
        System.out.println("passwrd = " + passwrd);
        sendText(addEmployeePage.confirmPassword,passwrd);
        wait(1);
        click(addEmployeePage.saveButton);

        try {
            if(personalDetailsPage.personalDetailsHeader.isDisplayed()){
                Assert.assertEquals(personalDetailsPage.employeeId.getAttribute("value"),userId,"Id did NOT match");
                takeScreenshot(firstName+" "+lastName);
                System.out.println("User added successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Employee is not added. Username or Id already exists.");
        }
    }
    @DataProvider(name = "getItFromExcel")
    public Object[][] getItFromExcel(){
        //String filePath = ExcelUtility.projectPath+"/testData/HW_user_login_tests.xlsx";
        return ExcelUtility.readFromExcel(Constants.TESTDATA_FILEPATH,"users");
    }
}
