package com.task.pages;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

public class DataFlowPage {

    WebDriver driver;

    Wait<WebDriver> fluentWait;

    @FindBy(how= How.XPATH,using="//button[@class='btn btn-primary btn-sm']")
    WebElement dropDown;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Hogedrukreinigers - cool blue')]")
    WebElement exportName;

    @FindBy(how= How.XPATH,using="//*[@id='wall1']/child::*")
    WebElement existingMappingXpath;

    String existingMappingXPaths="//*[@id='wall1']/child::*";
    String sourceColumn="//td[contains(@class,'source-columns')]//*[@name='%sourceValue']";
    String intermediateColumn="//td[contains(@class,'intermediate-columns')]//*[@name='%targetValue']";



    JavascriptExecutor javascriptExecutor;

    public DataFlowPage(WebDriver driver){
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
        if(!dropDown.getText().equals("Hogedrukreinigers - cool blue")){
            dropDown.click();
            exportName.click();
        }
    }

    public void createMapping() {
        List<String> existingMappingValues = new ArrayList<>();
        Map<String,String> mapping= new LinkedHashMap<>();
        mapping.put("brand","brand");
        mapping.put("colour","color");
        mapping.put("day","cla_custom_label_4");
        mapping.put("GTIN","gtin");
        mapping.put("GTIN_old","gtin_old");
        mapping.put("ID","id");
        mapping.put("size","size");
        waitForSeconds(10);
        fluentWait.until(ExpectedConditions.visibilityOf(existingMappingXpath));
        List<WebElement> existingMappings = driver.findElements(By.xpath(existingMappingXPaths));
        for(WebElement existingMapping:existingMappings){
            existingMappingValues.add(existingMapping.getAttribute("column"));
        }
        Collection<String> commonSource=CollectionUtils.subtract(mapping.values(),existingMappingValues);
        Assert.assertFalse(commonSource.isEmpty(),"All Mappings are there");
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
                for(String s:commonSource) {
                    if(entry.getValue().equals(s)) {
                        WebElement source=driver.findElement(By.xpath(sourceColumn.replace("%sourceValue",entry.getKey())));
                        WebElement target=driver.findElement(By.xpath(intermediateColumn.replace("%targetValue",s)));
                        source.click();
                        waitForSeconds(2);
                        target.click();
                        waitForSeconds(5);
                    }
                }
        }
    }

    public void waitForSeconds(long sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
