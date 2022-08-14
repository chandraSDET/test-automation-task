package com.task.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DataViewPage {

    WebDriver driver;

    Wait<WebDriver> fluentWait;

    JavascriptExecutor javascriptExecutor;

    @FindBy(how= How.XPATH,using="//button[@class='btn btn-default btn-sm stage-menu-btn']")
    WebElement dropDown;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Intermediate')]")
    WebElement intermediate;

    @FindBy(how= How.XPATH,using="//*[contains(@href,'edit/cla_custom_label_4')]")
    WebElement editDay;

    @FindBy(how= How.XPATH,using="//*[contains(@href,'edit/color')]")
    WebElement editColor;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Save')]")
    WebElement save;

    @FindBy(how= How.XPATH,using="//*[text()='Large View']")
    WebElement largeView;

    @FindBy(how= How.XPATH,using="//*[text()='Blacklist']")
    WebElement blackListSource;

    @FindBy(how= How.XPATH,using="//*[text()='Replacement']")
    WebElement replaceListSource;

    @FindBy(how= How.XPATH,using="//*[text()='Taxonomy Mapping']")
    WebElement replaceTaxonomyMappingSource;

    @FindBy(how= How.ID,using="dataflow-ul-input")
    WebElement sourceDropBox;

    @FindBy(how= How.XPATH,using="//div[contains(text(),'Select List to use as Blacklist')]//*[contains(@class,'chosen-single')]")
    WebElement selectBlackList;

    @FindBy(how= How.XPATH,using="//p[starts-with(text(),'Apply Replacement List')]/parent::div//*[contains(@class,'chosen-single')]")
    WebElement selectReplacementList;

    @FindBy(how= How.XPATH,using="//*[starts-with(text(),'Taxonomy Mapping')]/parent::div/following-sibling::div//*[contains(@class,'chosen-single')]")
    WebElement selectTaxonomyList;

    @FindBy(how= How.XPATH,using="//*[starts-with(text(),'Taxonomy Mapping')]/parent::div/following-sibling::div//*[contains(@class,'chosen-search-input')]")
    WebElement inputTaxonomyList;

    @FindBy(how= How.XPATH,using="//*[starts-with(text(),'Taxonomy Mapping')]/parent::div/following-sibling::div//*[contains(@class,'chosen-results')]/li")
    WebElement suggestedTaxonomyList;

    @FindBy(how= How.XPATH,using="//p[starts-with(text(),'Apply Replacement List')]/parent::*/div/div/*/input")
    WebElement inputReplaceList;

    @FindBy(how= How.XPATH,using="//p[starts-with(text(),'Apply Replacement List')]/parent::*/div/div/ul")
    WebElement suggestedReplaceList;

    @FindBy(how= How.XPATH,using="//div[contains(text(),'Select List to use as Blacklist')]//*[@class='chosen-search-input']")
    WebElement inputList;

    @FindBy(how= How.XPATH,using="//*[@class='chosen-results']/li")
    WebElement suggestedList;

    @FindBy(how= How.XPATH,using="//*[contains(@ng-click,'closeSidebar')]")
    WebElement closeWindow;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Refresh')]")
    WebElement refresh;

    @FindBy(how= How.XPATH,using="//*[@style='height: 15px; display: none;']")
    WebElement img;

    @FindBy(how= How.XPATH,using="//*[text()='Refreshing']")
    WebElement refreshing;

    @FindBy(how= How.XPATH,using="//span[@class='refresh-button-title']")
    WebElement button;

    String saveAfter="//*[text()='Large View']/parent::form/button[1]";

    String colourText="//td[contains(@class,'product-column-color')]/*/div/span";

    public DataViewPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
        javascriptExecutor=(JavascriptExecutor)driver ;
    }

    public void selectDropDown(){
        driver.switchTo().frame(0);
        fluentWait.until(ExpectedConditions.elementToBeClickable(dropDown));
        if(!dropDown.getText().equals("Intermediate")){
            dropDown.click();
            intermediate.click();
        }
        Assert.assertEquals(driver.findElements(By.xpath("//*[text()='No data available']")).size(), 0);
    }

    public void blockList(String lName,String day){
        Assert.assertTrue(editDay.isDisplayed());
        fluentWait.until(ExpectedConditions.elementToBeClickable(editDay));
        editDay.click();
        driver.switchTo().frame("editFrame");
        fluentWait.until(ExpectedConditions.elementToBeClickable(save));
        largeView.click();
        Actions actions = new Actions(driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", blackListSource);
        Action dragAndDrop = actions.clickAndHold(blackListSource)
                .moveToElement(sourceDropBox)
                .release(sourceDropBox)
                .build();
        dragAndDrop.perform();
        fluentWait.until(ExpectedConditions.elementToBeClickable(selectBlackList));
        selectBlackList.click();
        inputList.sendKeys(lName);
        fluentWait.until(ExpectedConditions.elementToBeClickable(suggestedList));
        suggestedList.click();
        List<WebElement> webElementList = driver.findElements(By.xpath(saveAfter));
        for(WebElement element:webElementList){
            if(element.isDisplayed()){
                element.click();
            }
        }
        driver.switchTo().parentFrame();
        fluentWait.until(ExpectedConditions.elementToBeClickable(closeWindow));
        closeWindow.click();
        waitForSeconds(2);
        refresh.click();
        fluentWait.until(function);
        waitForSeconds(2);
        String notExported="//*[text()='day']/ancestor::tr";
        WebElement element=driver.findElement(By.xpath(notExported.replace("day",day)));
        Assert.assertTrue(element.getAttribute("title").contains("This product will not be exported"));
    }

    public void replaceList(String lName,String firstDay,String secondDay){
        fluentWait.until(ExpectedConditions.elementToBeClickable(editDay));
        editDay.click();
        driver.switchTo().frame("editFrame");
        fluentWait.until(ExpectedConditions.elementToBeClickable(save));
        largeView.click();
        Actions actions = new Actions(driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", replaceListSource);
        Action dragAndDrop = actions.clickAndHold(replaceListSource)
                .moveToElement(sourceDropBox)
                .release(sourceDropBox)
                .build();
        dragAndDrop.perform();
        fluentWait.until(ExpectedConditions.elementToBeClickable(selectReplacementList));
        selectReplacementList.click();
        inputReplaceList.sendKeys(lName);
        fluentWait.until(ExpectedConditions.elementToBeClickable(suggestedReplaceList));
        suggestedReplaceList.click();
        List<WebElement> webElementList = driver.findElements(By.xpath(saveAfter));
        for(WebElement element:webElementList){
            if(element.isDisplayed()){
                element.click();
            }
        }
        driver.switchTo().parentFrame();
        fluentWait.until(ExpectedConditions.elementToBeClickable(closeWindow));
        closeWindow.click();
        refresh.click();
        fluentWait.until(function);
        String dayColumn="//*[text()='day']";
        Assert.assertEquals(driver.findElements(By.xpath(dayColumn.replace("day",firstDay.toUpperCase()))).size(),1);
        Assert.assertEquals(driver.findElements(By.xpath(dayColumn.replace("day",secondDay.toUpperCase()))).size(),1);
    }

    public void addTaxonomy(String lName,String blackValue,String blueValue){
        fluentWait.until(ExpectedConditions.elementToBeClickable(editColor));
        editColor.click();
        driver.switchTo().frame("editFrame");
        fluentWait.until(ExpectedConditions.elementToBeClickable(save));
        largeView.click();
        Actions actions = new Actions(driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", replaceTaxonomyMappingSource);
        Action dragAndDrop = actions.clickAndHold(replaceTaxonomyMappingSource)
                .moveToElement(sourceDropBox)
                .release(sourceDropBox)
                .build();
        dragAndDrop.perform();
        fluentWait.until(ExpectedConditions.elementToBeClickable(selectTaxonomyList));
        selectTaxonomyList.click();
        inputTaxonomyList.sendKeys(lName);
        fluentWait.until(ExpectedConditions.elementToBeClickable(suggestedTaxonomyList));
        suggestedTaxonomyList.click();
        List<WebElement> webElementList = driver.findElements(By.xpath(saveAfter));
        for(WebElement element:webElementList){
            if(element.isDisplayed()){
                element.click();
            }
        }
        driver.switchTo().parentFrame();
        fluentWait.until(ExpectedConditions.elementToBeClickable(closeWindow));
        closeWindow.click();
        waitForSeconds(2);
        refresh.click();
        fluentWait.until(function);
        List<WebElement> colourValueElements = driver.findElements(By.xpath(colourText));
        List<String> colourValues= new ArrayList<>();
        for(WebElement colourValue:colourValueElements){
            if(!colourValue.getText().isEmpty()){
                colourValues.add(colourValue.getText());
            }
        }
        System.out.println(colourValues);
        Assert.assertTrue(colourValues.contains(blackValue));
        Assert.assertTrue(colourValues.contains(blueValue));
    }

    Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
    {
        public Boolean apply(WebDriver arg0) {
            String text = button.getText();
            if(text.equals("Refresh"))
            {
                return true;
            }
            return false;
        }
    };

    public void waitForSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
