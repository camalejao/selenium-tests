package br.jao.campotreinamento;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.jao.dsl.DSL;

public class TesteAjax {
    
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml";
        driver.get(url);
        dsl = new DSL(driver);
    }

    @After
    public void finalizaDriver() {
        driver.quit();
    }


    @Test
    public void testeAjax() {
        dsl.escreve("j_idt304:name", "Teste");
        dsl.clicar("j_idt304:j_idt308");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt304:display"), "Teste"));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt304:display"));
    }

    @Test
    public void testeAlternativo() {
        dsl.escreve("j_idt304:name", "Teste");
        dsl.clicar("j_idt304:j_idt308");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.textToBe(By.id("j_idt304:display"), "Teste"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'pi-spinner')]")));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt304:display"));
    }
}
