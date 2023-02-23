package com.exelenter.testcases;

import com.exelenter.base.BaseClass;
import org.testng.annotations.Test;


public class AddEmployeeTest extends BaseClass {
       @Test(groups = {"smoke","regression"})
    public void addEmployeeTest(){
        loginPage.loginToWebsite("username","password");
        pimPage.navigateToAddEmployee();

        String employeeId = addEmployeePage.employeeId.getAttribute("value");
        System.out.println("employeeId = " + employeeId);

        addEmployeePage.addEmployee("empFirstName","empLastName","filePath");    //this method will add a new employee
    }

}
