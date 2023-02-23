package com.exelenter.utils;

import com.exelenter.base.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.exelenter.base.BaseClass.driver;

public class CommonMethods extends PageInitializer {
    /**
     * Method will switch focus to next window/tab based on the window title/name
     * @param windowTitle String
     */
    public static void switchToWindow(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowOrTab : windows) {
            String title = driver.switchTo().window(windowOrTab).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Window is found! Page Title: " + driver.getTitle() + " URL: " + driver.getCurrentUrl());
                break;
            }
        }
    }
    public static void sendText(WebElement element, String value) {
        element.sendKeys(value);
    }

    public static void click(WebElement element){
        element.click();
    }

    public static WebDriverWait waitForElement(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
        return wait;
     }

    public static void waitForClickability(WebElement element){
        waitForElement().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickButwaitForClickability(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static void waitForVisibilty(WebElement element){
        waitForElement().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibiltyOfElement(By by){
        waitForElement().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForPresenceOfElements(By by){
        waitForElement().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void clickButWaitForVisibility(WebElement element){
        waitForVisibilty(element);
        element.click();
    }

    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickRadioOrCheckBox(List<WebElement> radioOrCheckBox, String expectedValue){
        for (WebElement element : radioOrCheckBox) {
            String actualValue = element.getAttribute("value");
            if(actualValue.equals(expectedValue)){
                element.click();
                break;
            }
        }
    }

    public static void clickRadioOrCheckBox(WebElement element){
        if(element.isEnabled() && !element.isSelected()){
            element.click();
        }

    }

    public static void clickDropDown(List<WebElement> dropDownList, String expectedValue){
        for (WebElement element : dropDownList) {
            String actualText = element.getText();
            if(actualText.equals(expectedValue)){
                element.click();
                break;
            }
        }
    }

    public static void selectDdValue(WebElement element, String expectedvalue) {
        Select select =new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if(option.getText().equals(expectedvalue)){
                select.selectByVisibleText(expectedvalue);
                break;
            }
        }
    }

    /**
     * Method select dropdown
     * @param dropDownList WebElement
     * @param expectedvalue String
     */
    public static void selectDdValue(List<WebElement> dropDownList, String expectedvalue) {
        for (WebElement element : dropDownList) {
            String actualText = element.getText();
            if (actualText.equals(expectedvalue)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Method will select a dropdown or multi-select by index
     * @param element WebElement
     * @param index int
     */
    public static void selectDdValue(WebElement element, int index){
        Select select = new Select(element);
//        List<WebElement> options = select.getOptions();
        select.selectByIndex(index);
        // burada try catch ile outofbounderror u hallet.
    }

    public static void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            System.out.println("Alert is not present.");
        }
    }

    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public static void sendAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public static String getAlertText() {
        String alertText = null;
        try {
            alertText = driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        return alertText;
    }

    public static void scrollToParagraph(int index){
        String script ="window.scrollTo(0, document.body.scrollHeight)";
        var jsExecuter = (JavascriptExecutor) driver;

        while (getNumberOfParagraph()<index){
            jsExecuter.executeScript(script); //scroll down by one <p> i.e paragraph
        }
        System.out.println("No of paragraphs: "+ getNumberOfParagraph());
    }

    public static int getNumberOfParagraph(){
        List<WebElement> paragraphs = driver.findElements(By.className("jscroll-added"));
        return paragraphs.size();
    }

    public static JavascriptExecutor jsExecutor() {
        return (JavascriptExecutor) driver;
    }

    /**
     * Method performs simple click based on Javascript. Use this if regular Selenium click fails.
     *
     * @param element WebElement that needs to be clicked on.
     */
    public static void jsClick(WebElement element) {
        jsExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * Method will scroll to the given element
     *
     * @param element WebElement to get scrolled to
     */
    public static void scrollToElement(WebElement element) {
        jsExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Method will scroll both vertically (left & right) and horizontally (up & down) based on given pixels.
     * @param horizontalPixel int
     * @param verticalPixel int
     */
    public static void scrollToElement(int horizontalPixel, int verticalPixel) {
        jsExecutor().executeScript("window.scrollBy(" + horizontalPixel + "," + verticalPixel + ")");
    }

    /**
     * Method takes a screenshot when called. Extension defined as .png. You can change to .jpeg from commonmethods when it is needed.
     * @param fileName String as filename
     */
    public static String takeScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Screenshot is not taken");
        }
        return fileName;
    }

    public static String randomStrongPassWord(){
        String passWord = "";
        Random rnd = new Random();
        String lowerLetter = "abcdefghijklmnoprstuwxyz";
        String capitalLetters = "ABCDEFGHIJKLMNOPRSTUWXYZ";
        String specialChar = "!#$%&()*+,-.:;<=>?@[]^_{|}~";
        while (passWord.length() < 12){
            passWord += lowerLetter.charAt(rnd.nextInt(lowerLetter.length()));
            passWord += capitalLetters.charAt(rnd.nextInt(capitalLetters.length()));
            passWord += specialChar.charAt(rnd.nextInt(specialChar.length()));
            passWord += rnd.nextInt(10);
        }
        return passWord;
    }

}
