package com.task.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    Wait<WebDriver> fluentWait;

    @FindBy(how= How.XPATH,using="//*[@type='text']")
    private WebElement uname;

    @FindBy(how= How.XPATH,using="//*[text()='Next']")
    private WebElement next;

    @FindBy(how= How.XPATH,using="//input[@type='password']")
    private WebElement password;

    @FindBy(how= How.XPATH,using="//*[text()='Log In']" )
    private WebElement logIn;

    @FindBy(how=How.XPATH,using = "//input[placeholder='Search']")
    private WebElement search;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 30);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public void loginToApplication(String userName,String pw){
        driver.switchTo().frame(0);
        fluentWait.until(ExpectedConditions.elementToBeClickable(uname));
        uname.sendKeys(userName);
        next.click();
        fluentWait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(pw);
        logIn.click();
        fluentWait.until(ExpectedConditions.urlContains("account"));
    }
}
