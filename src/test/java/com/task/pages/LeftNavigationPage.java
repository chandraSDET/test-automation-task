package com.task.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class LeftNavigationPage {

    WebDriver driver;

    Wait<WebDriver> fluentWait;

    JavascriptExecutor javascriptExecutor;

    public LeftNavigationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
        javascriptExecutor=(JavascriptExecutor)driver ;
    }

    String featureName="//span[text()='feature-name']";
    @FindBy(how= How.XPATH,using="//nav[@class='sidebar']/child::a")
    WebElement siteLogo;

    public void navigateToSiteLogo(){
        fluentWait.until(ExpectedConditions.elementToBeClickable(siteLogo));
        Actions actions = new Actions(driver);
        actions.moveToElement(siteLogo);
    }

    public void clickList(String feature){
        navigateToSiteLogo();
        driver.findElement(By.xpath(featureName.replace("feature-name",feature))).click();
        fluentWait.until(ExpectedConditions.urlContains("lists"));
    }

    public void  clickDataView(String feature){
        driver.switchTo().defaultContent();
        navigateToSiteLogo();
        driver.findElement(By.xpath(featureName.replace("feature-name",feature))).click();
        fluentWait.until(ExpectedConditions.urlContains("data-view"));
    }

    public void clickDataFlow(String feature){
        navigateToSiteLogo();
        driver.findElement(By.xpath(featureName.replace("feature-name",feature))).click();
        fluentWait.until(ExpectedConditions.urlContains("dataflow"));
    }



}
