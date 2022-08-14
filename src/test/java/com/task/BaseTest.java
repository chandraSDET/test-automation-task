package com.task;

import com.task.config.ConfigFileReader;
import com.task.config.ConfigManager;
import com.task.driver.DriverFactory;
import com.task.driver.DriverManager;
import com.task.reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BaseTest extends ExtentReportManager {
    public ConfigFileReader configFileReader;
    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        driver = new DriverFactory().createInstance(browser);
        DriverManager.setDriver(driver);
        configFileReader=ConfigManager.getInstance().getConfigReader();
    }

    @AfterMethod
    public void closeDriver(){
        DriverManager.quit();
    }

    public String getUniqueName(){
        LocalDateTime currentDate
                = LocalDateTime .parse(LocalDateTime.now().toString());
        int day = currentDate.getSecond();
        Month month = currentDate.getMonth();
        return day+""+month.toString().substring(0,3);
    }

    public String getRandomDay() {
        List<String> daysList = Arrays.asList("Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Testday",
                "SnowDay",
                "FunDay");
        Random rand = new Random();
        return daysList.get(rand.nextInt(daysList.size()));
    }

    public String checkRandom(String firstDay,String secondDay) {
        if (firstDay.equals(secondDay)) {
            secondDay = getRandomDay();
        }
        return secondDay;
    }
}
