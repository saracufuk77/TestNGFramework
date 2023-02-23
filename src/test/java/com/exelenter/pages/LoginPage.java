package com.exelenter.pages;

import com.exelenter.utils.ConfigsReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.exelenter.base.BaseClass.driver;
import static com.exelenter.utils.CommonMethods.click;
import static com.exelenter.utils.CommonMethods.sendText;

// Note: This is just a template for LoginPage where we store everything related to Login page here, in this class.
//  In Page Object Model (Design Pattern), we organize our code by pages. Each web page will have their own class.
public class LoginPage {

    // LoginPage using PageFactory
    //@FindBy() == driver.findElement()

    @FindBy(id = "txtUsername")                 //  locating by ID
    public WebElement username;

    @FindBy(name = "txtPassword")               // locating by Name
    public WebElement password;

    @FindBy(xpath = "//input[@id='btnLogin']") // Locating by xPath
    public WebElement loginBtn;

    @FindBy(css = "#divLogo img")
    public WebElement homepageLogo;

    @FindBy(id="spanMessage")
    public WebElement LoginErrorMessage;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public void loginToWebsite(String user, String psswd){
        sendText(username, ConfigsReader.getProperties(user));
        sendText(password, ConfigsReader.getProperties(psswd));
        click(loginBtn);
    }

}
