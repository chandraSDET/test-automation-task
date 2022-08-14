package com.task.test;

import com.task.BaseTest;
import com.task.driver.DriverManager;
import com.task.pages.*;


import org.testng.annotations.*;


public class TaskTests extends BaseTest {

    @Test(description = "Task-1")
    public void testStandardIncOrExc()
    {
        String lName=getUniqueName();
        String day=getRandomDay();
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProjectPage projectPage = new ProjectPage(DriverManager.getDriver());
        LeftNavigationPage leftNavigationPage = new LeftNavigationPage(DriverManager.getDriver());
        AddListPage addListPage = new AddListPage(DriverManager.getDriver());
        DataViewPage dataViewPage = new DataViewPage(DriverManager.getDriver());
        loginPage.navigateToUrl(configFileReader.getUrl());
        loginPage.loginToApplication(configFileReader.getEmail(),configFileReader.getPassword());
        projectPage.clickSite(configFileReader.getSiteName());
        leftNavigationPage.clickList("Lists");
        addListPage.clickAddList();
        addListPage.configureStandardInclusionOrExclusion(lName,day);
        leftNavigationPage.clickDataView("Data View");
        dataViewPage.selectDropDown();
        dataViewPage.blockList(lName,day);
    }

    @Test(description = "Task-2")
    public void testStandardReplacement()
    {
        String lName=getUniqueName();
        String firstDay=getRandomDay();
        String secondDay=getRandomDay();
        secondDay=checkRandom(firstDay,secondDay);
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProjectPage projectPage = new ProjectPage(DriverManager.getDriver());
        LeftNavigationPage leftNavigationPage = new LeftNavigationPage(DriverManager.getDriver());
        AddListPage addListPage = new AddListPage(DriverManager.getDriver());
        DataViewPage dataViewPage = new DataViewPage(DriverManager.getDriver());
        loginPage.navigateToUrl(configFileReader.getUrl());
        loginPage.loginToApplication(configFileReader.getEmail(),configFileReader.getPassword());
        projectPage.clickSite(configFileReader.getSiteName());
        leftNavigationPage.clickList("Lists");
        addListPage.clickAddList();
        addListPage.configureStandardReplacement(lName,firstDay,secondDay);
        leftNavigationPage.clickDataView("Data View");
        dataViewPage.selectDropDown();
        dataViewPage.replaceList(lName,firstDay,secondDay);
    }

    @Test(description = "Task-3")
    public void partnerTaxonomyMapping()
    {
        String lName=getUniqueName();
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProjectPage projectPage = new ProjectPage(DriverManager.getDriver());
        LeftNavigationPage leftNavigationPage = new LeftNavigationPage(DriverManager.getDriver());
        AddListPage addListPage = new AddListPage(DriverManager.getDriver());
        DataViewPage dataViewPage = new DataViewPage(DriverManager.getDriver());
        loginPage.navigateToUrl(configFileReader.getUrl());
        loginPage.loginToApplication(configFileReader.getEmail(),configFileReader.getPassword());
        projectPage.clickSite(configFileReader.getSiteName());
        leftNavigationPage.clickList("Lists");
        addListPage.clickAddList();
        addListPage.configurePartnerTaxonomyMapping(lName);
        leftNavigationPage.clickDataView("Data View");
        dataViewPage.selectDropDown();
        dataViewPage.addTaxonomy(lName,configFileReader.getBlockColour(),configFileReader.getBlueColour());
    }

    @Test(description = "Task-4")
    public void mappingColumnsAtDataFlow()
    {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProjectPage projectPage = new ProjectPage(DriverManager.getDriver());
        LeftNavigationPage leftNavigationPage = new LeftNavigationPage(DriverManager.getDriver());
        DataFlowPage dataFlowPage = new DataFlowPage(DriverManager.getDriver());
        loginPage.navigateToUrl(configFileReader.getUrl());
        loginPage.loginToApplication(configFileReader.getEmail(),configFileReader.getPassword());
        projectPage.clickSite(configFileReader.getSiteName());
        leftNavigationPage.clickDataFlow("Dataflow");
        dataFlowPage.selectDropDown();
        dataFlowPage.createMapping();
    }

}
