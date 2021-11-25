package br.jao.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.jao.core.Properties.TipoExecucao;

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

        if (Properties.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
            switch (Properties.BROWSER) {
                case FIREFOX: driver = new FirefoxDriver(); break;
                case CHROME: driver = new ChromeDriver(); break;
                default: driver = new ChromeDriver(); break;
            }
        }

        if (Properties.TIPO_EXECUCAO == TipoExecucao.GRID) {
            URL gridURL = null;
            try {
                gridURL = new URL("http://192.168.0.47:4444/");
            } catch (MalformedURLException e) {
                System.err.println("Falha na criação de URL para conectar ao GRID");
                e.printStackTrace();
            }
            switch (Properties.BROWSER) {
                case FIREFOX: driver = new RemoteWebDriver(gridURL, new FirefoxOptions()); break;
                case CHROME: driver = new RemoteWebDriver(gridURL, new ChromeOptions()); break;
                default: driver = new ChromeDriver(); break;
            }
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
