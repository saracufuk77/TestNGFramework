package com.exelenter.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.exelenter.base.BaseClass.driver;
import static com.exelenter.utils.CommonMethods.click;

public class PIMPage {
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement PIM;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployee;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeList;

    public PIMPage(){
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddEmployee(){
        click(PIM);
        click(addEmployee);
    }
}
