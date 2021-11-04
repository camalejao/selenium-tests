package br.jao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {
    
    @Test
    public void testeTextField() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement textField = driver.findElement(By.id("elementosForm:nome"));
        textField.sendKeys("Teste de Escrita");
        Assert.assertEquals("Teste de Escrita", textField.getAttribute("value"));
                
        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement textArea = driver.findElement(By.id("elementosForm:sugestoes"));
        textArea.sendKeys("Teste de Escrita\n\nMúltiplas\nLinhas");
        Assert.assertEquals("Teste de Escrita\n\nMúltiplas\nLinhas", textArea.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement radio = driver.findElement(By.id("elementosForm:sexo:0"));
        radio.click();

        Assert.assertTrue(radio.isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement check = driver.findElement(By.id("elementosForm:comidaFavorita:2"));
        check.click();

        Assert.assertTrue(check.isSelected());

        driver.quit();
    }
}
