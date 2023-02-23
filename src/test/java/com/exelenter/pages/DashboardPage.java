package com.exelenter.pages;

import com.exelenter.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BaseClass {
 @FindBy(id = "welcome")   //<== driver find element (By.id("welcome"))
 public WebElement welcome;   //public Webelement welcome =driver.findelement(By.id("welcome"))

 @FindBy(xpath = "//*[@alt='Exelenter Project']")
 public WebElement dashboardLogo;
    public DashboardPage(){
        PageFactory.initElements(driver, this);
    }
}
