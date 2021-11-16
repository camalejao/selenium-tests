package br.jao.campotreinamento;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.jao.dsl.DSL;

public class TesteSincronismo {
    
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        dsl = new DSL(driver);
    }

    @After
    public void finalizaDriver() {
        driver.quit();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicar("buttonDelay");
        Thread.sleep(5000); // Espera Fixa
        dsl.escreve("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        dsl.clicar("buttonDelay");
        dsl.escreve("novoCampo", "Deu certo?");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void deveUtilizarEsperaExplicita() {
        dsl.clicar("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Deu certo?");
    }

}
