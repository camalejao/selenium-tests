package br.jao.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
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
                default: driver = new RemoteWebDriver(gridURL, new ChromeOptions()); break;
            }
        }

        if (Properties.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
            URL nuvemURL = null;
            
            try {
                nuvemURL = new URL(System.getenv("SAUCELABS_URL"));
            } catch (MalformedURLException e) {
                System.err.println("Falha na criação de URL para conectar à nuvem");
                e.printStackTrace();
            }
            
            switch (Properties.BROWSER) {
                case FIREFOX:
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.setPlatformName("Windows 10");
                    ffOptions.setBrowserVersion("latest");
                    Map<String, Object> sauceOptions = new HashMap<>();
                    ffOptions.setCapability("sauce:options", sauceOptions);
                    driver = new RemoteWebDriver(nuvemURL, ffOptions);
                    break;
                
                case CHROME:
                    ChromeOptions browserOptions = new ChromeOptions();
                    browserOptions.setPlatformName("Windows 10");
                    browserOptions.setBrowserVersion("latest");
                    Map<String, Object> sOptions = new HashMap<>();
                    browserOptions.setCapability("sauce:options", sOptions);
                    driver = new RemoteWebDriver(nuvemURL, browserOptions);
                    break;
                
                case IE:
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.setPlatformName("Windows 10");
                    ieOptions.setBrowserVersion("latest");
                    Map<String, Object> slOptions = new HashMap<>();
                    ieOptions.setCapability("sauce:options", slOptions);
                    driver = new RemoteWebDriver(nuvemURL, ieOptions);
                    break;
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
