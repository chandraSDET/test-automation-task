package com.task.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AddListPage {

    WebDriver driver;
    Wait<WebDriver> fluentWait;

    JavascriptExecutor javascriptExecutor;

    @FindBy(how= How.XPATH,using="//*[text()='Add list']")
    WebElement addList;

    @FindBy(how= How.XPATH,using="//*[text()='Choose a list type']")
    WebElement listTypeText;

    @FindBy(how= How.XPATH,using="//*[text()='Standard Inclusion/Exclusion']")
    WebElement standardIncOrExc;

    @FindBy(how= How.XPATH,using="//*[text()='Standard Replacement']")
    WebElement standardReplacement;

    @FindBy(how= How.XPATH,using="//*[text()='Continue']")
    WebElement cont;

    @FindBy(how= How.XPATH,using="//*[text()='Configure the Standard Inclusion/Exclusion list']")
    WebElement configStandardIncOrExc;

    @FindBy(how= How.XPATH,using="//input[@type='text']")
    private WebElement listName;

    @FindBy(how= How.XPATH,using="//*[text()='Add']")
    WebElement add;

    @FindBy(how= How.XPATH,using="//*[@placeholder='Enter a term']")
    WebElement term;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Export')]")
    WebElement export;

    @FindBy(how= How.XPATH,using="//pup-table-row[@class='table-row ng-star-inserted'][1]")
    WebElement addedTerm;

    @FindBy(how= How.XPATH,using="//*[@class='paragraph ng-star-inserted']")
    WebElement standardReplacementHeader;

    @FindBy(how= How.XPATH,using="//*[@placeholder='Search for a value']")
    WebElement searchTerm;

    String sRepTerm="//input[@placeholder='Enter a term']";

    String sRepAddedTerms="//*[@class='table-row ng-star-inserted']";

    String dayTaxonomyTerm="//*[text()='Day']//parent::div/parent::pup-table-cell/parent::pup-table-row/pup-table-cell[2]//*/input";

    String addTaxonomy="//pup-floating-label[text()='Taxonomy']//following::pup-dropdown-search//*/input";

    String addAttribute="//pup-floating-label[text()='Attribute']//following::pup-dropdown-search//*/input";

    @FindBy(how= How.XPATH,using="//*[text()='Partner Taxonomy Mapping']")
    WebElement partnerTaxonomyMapping;

    @FindBy(how= How.XPATH,using="//*[@class='dropdown-menu-item-content']")
    WebElement suggestion;

    @FindBy(how= How.XPATH,using="//*[@class='scroll-container']/pup-dropdown-menu-item")
    WebElement colourSuggestion;

    @FindBy(how= How.XPATH,using="//*[contains(@class,'highlighted')][1]")
    WebElement termSuggestion;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Mapped')]")
    WebElement mappedItems;

     String anotherTermSuggestion="//*[text()='None']//parent::span/parent::pup-dropdown-menu-item/parent::div/pup-dropdown-menu-item[2]/span";


    public AddListPage(WebDriver driver){
        this.driver=driver;
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
        javascriptExecutor=(JavascriptExecutor)driver ;
    }

    public void clickAddList(){
        driver.switchTo().frame(0);
        fluentWait.until(ExpectedConditions.elementToBeClickable(addList));
        addList.click();
        fluentWait.until(ExpectedConditions.elementToBeClickable(listTypeText));
    }

    public void configureStandardInclusionOrExclusion(String lName,String day){
        standardIncOrExc.click();
        javascriptExecutor.executeScript("arguments[0].click();", cont);
        fluentWait.until(ExpectedConditions.visibilityOf(configStandardIncOrExc));
        listName.sendKeys(lName);
        javascriptExecutor.executeScript("arguments[0].click();", add);
        fluentWait.until(ExpectedConditions.visibilityOf(term));
        term.sendKeys(day);
        add.click();
        fluentWait.until(ExpectedConditions.elementToBeClickable(export));
        fluentWait.until(ExpectedConditions.elementToBeClickable(addedTerm));
        Assert.assertTrue(addedTerm.isDisplayed()) ;
    }

    public void configureStandardReplacement(String lName, String firstDay,String secondDay) {
        standardReplacement.click();
        javascriptExecutor.executeScript("arguments[0].click();", cont);
        fluentWait.until(ExpectedConditions.visibilityOf(listName));
        listName.sendKeys(lName);
        javascriptExecutor.executeScript("arguments[0].click();", add);
        fluentWait.until(ExpectedConditions.visibilityOf(standardReplacement));
        fluentWait.until(ExpectedConditions.elementToBeClickable(export));
        addReplaceTerm(firstDay);
        addReplaceTerm(secondDay);
        searchTerm.click();
        standardReplacementHeader.click();
        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
        {
            public Boolean apply(WebDriver arg0) {
                int size = driver.findElements(By.xpath(sRepAddedTerms)).size();
                if(size==2)
                {
                    return true;
                }
                return false;
            }
        };
        fluentWait.until(function);
        Assert.assertEquals(driver.findElements(By.xpath(sRepAddedTerms)).size(), 2);
    }

    public void configurePartnerTaxonomyMapping(String lName) {
        javascriptExecutor.executeScript("arguments[0].click();", partnerTaxonomyMapping);
        javascriptExecutor.executeScript("arguments[0].click();", cont);
        fluentWait.until(ExpectedConditions.visibilityOf(listName));
        listName.sendKeys(lName);
        driver.findElement(By.xpath(addTaxonomy)).sendKeys("Ali Express EN");
        suggestion.click();
        driver.findElement(By.xpath(addAttribute)).sendKeys("colour");
        waitForSeconds(7);
        fluentWait.until(ExpectedConditions.elementToBeClickable(colourSuggestion));
        suggestion.click();
        javascriptExecutor.executeScript("arguments[0].click();", add);
        fluentWait.until(ExpectedConditions.elementToBeClickable(partnerTaxonomyMapping));
        driver.findElement(By.xpath(dayTaxonomyTerm.replace("Day","Black"))).sendKeys("Air Conditioning");
        waitForSeconds(3);
        termSuggestion.click();
        waitForSeconds(3);
        driver.findElement(By.xpath(dayTaxonomyTerm.replace("Day","Blue"))).sendKeys("Television");
        waitForSeconds(3);
        driver.findElement(By.xpath(anotherTermSuggestion)).click();
        driver.navigate().refresh();
        driver.switchTo().frame(0);
        Assert.assertTrue(mappedItems.getText().contains("2"));
    }

    public void addReplaceTerm(String day){
        int count=0;
        List<WebElement> lst=driver.findElements(By.xpath(sRepTerm));
        for(WebElement webElement:lst) {
            if(count<1){
                webElement.sendKeys(day);
                count++;
            }
            else{
                webElement.sendKeys(day.toUpperCase());
            }
        }
        add.click();
    }

    public void waitForSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
