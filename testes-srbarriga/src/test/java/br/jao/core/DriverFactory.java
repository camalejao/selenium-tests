package br.jao.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>() {
        @Override
        protected synchronized WebDriver initialValue() {
            return initDriver();
        }
    };

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    public static WebDriver initDriver() {
        WebDriver driver = null;
        switch (Properties.browser) {
            case FIREFOX: driver = new FirefoxDriver(); break;
            case CHROME: driver = new ChromeDriver(); break;
            default: driver = new ChromeDriver(); break;
        }
        return driver;
    }

    public static void killDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (threadDriver != null) {
            threadDriver.remove();
        }
    }

}
