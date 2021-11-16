package br.jao.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    
    public static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
