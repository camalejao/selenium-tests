package br.jao;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteCTAlert {
    
    @Test
    public void deveInteragirComAlertSimples() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        
        WebElement alertButton = driver.findElement(By.id("alert"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        Assert.assertEquals("Alert Simples", alertText);
        alert.accept();

        WebElement textField = driver.findElement(By.id("elementosForm:nome"));
        textField.sendKeys(alertText);
        Assert.assertEquals(alertText, textField.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveConfirmarAlerta() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        
        WebElement confirmButton = driver.findElement(By.id("confirm"));
        confirmButton.click();

        Alert confirmAlert = driver.switchTo().alert();

        String confirmText = confirmAlert.getText();
        Assert.assertEquals("Confirm Simples", confirmText);
        confirmAlert.accept();

        Alert alert = new WebDriverWait(driver, Duration.ofMillis(500))
            .until(ExpectedConditions.alertIsPresent());

        confirmText = alert.getText();
        Assert.assertEquals("Confirmado", confirmText);

        driver.quit();
    }

    @Test
    public void deveRejeitarAlerta() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        
        WebElement confirmButton = driver.findElement(By.id("confirm"));
        confirmButton.click();

        Alert confirmAlert = driver.switchTo().alert();

        String confirmText = confirmAlert.getText();
        Assert.assertEquals("Confirm Simples", confirmText);
        confirmAlert.dismiss();

        // espera explicita adicionada pois o selenium executava antes do alerta aparecer
        Alert alert = new WebDriverWait(driver, Duration.ofMillis(500))
            .until(ExpectedConditions.alertIsPresent());
        
        String alertText = alert.getText();
        Assert.assertEquals("Negado", alertText);

        driver.quit();
    }

    @Test
    public void deveInteragirComPrompt() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        
        WebElement promptButton = driver.findElement(By.id("prompt"));
        promptButton.click();
        Alert prompt = driver.switchTo().alert();

        Assert.assertEquals("Digite um numero", prompt.getText());

        prompt.sendKeys("42");
        prompt.accept();
        prompt = new WebDriverWait(driver, Duration.ofMillis(500))
            .until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals("Era 42?", prompt.getText());

        prompt.accept();
        prompt = new WebDriverWait(driver, Duration.ofMillis(500))
            .until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(":D", prompt.getText());

        driver.quit();
    }
}