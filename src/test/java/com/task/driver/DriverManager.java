package com.task.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    public static void quit() {
        webDriverThreadLocal.get().quit();
    }
}
