package br.jao.campotreinamento;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteRegrasDeNegocio {
    
    @Test
    public void nomeDeveSerObrigatorio() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    public void sobrenomeDeveSerObrigatorio() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    public void sexoDeveSerObrigatorio() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    public void testeVegetariano() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        
        // Carne e Vegetariano
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.dismiss();

        // Frango e Vegetariano
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        // alert = new WebDriverWait(driver, Duration.ofMillis(500))
        //     .until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.dismiss();

        driver.quit();
    }

    @Test
    public void testeEsportes() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.dismiss();

        driver.quit();
    }

}
