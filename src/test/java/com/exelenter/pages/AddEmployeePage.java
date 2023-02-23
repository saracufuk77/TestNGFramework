package com.exelenter.pages;

import com.exelenter.base.BaseClass;
import com.exelenter.utils.ConfigsReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddEmployeePage extends BaseClass {
    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public  WebElement lastName;

    @FindBy(id = "employeeId")
    public WebElement employeeId;

    @FindBy(id ="photofile" )
    public WebElement uploadPhoto;

    @FindBy(id="chkLogin")
    public WebElement createLoginDetailsCheckBox;

    @FindBy(id="user_name")
    public WebElement username;

    @FindBy(id="user_password")
    public WebElement password;

    @FindBy(id="re_password")
    public WebElement confirmPassword;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public AddEmployeePage(){
        PageFactory.initElements(driver,this);
    }

    public void addEmployee(String empFirstName, String empLastName, String filePath){
        sendText(firstName, ConfigsReader.getProperties(empFirstName));
        sendText(lastName,ConfigsReader.getProperties(empLastName));
        sendText(uploadPhoto,ConfigsReader.getProperties(filePath));  //retrieving photo location
        click(saveButton);
    }
}
