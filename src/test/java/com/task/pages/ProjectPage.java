package com.task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectPage {

    WebDriver driver;
    WebDriverWait wait;

    Wait<WebDriver> fluentWait;

    String projectSiteName="//span[text()='site-name']";

    @FindBy(how= How.XPATH,using="//a[@class='project-name']/parent::td/parent::tr/td[1]")
    WebElement projectDrillDown;


    public ProjectPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver,this);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
    }

    public void clickSite(String siteName){
        projectDrillDown.click();
        driver.findElement(By.xpath(projectSiteName.replace("site-name",siteName))).click();
        fluentWait.until(ExpectedConditions.urlContains("dashboard"));
    }





}
