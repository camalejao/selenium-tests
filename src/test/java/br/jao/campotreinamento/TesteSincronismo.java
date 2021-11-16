package br.jao.campotreinamento;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.jao.core.DSL;
import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

public class TesteSincronismo {
    
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        getDriver().get(url);
        dsl = new DSL();
    }

    @After
    public void finalizaDriver() {
        killDriver();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicar("buttonDelay");
        Thread.sleep(5000); // Espera Fixa
        dsl.escreve("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        dsl.clicar("buttonDelay");
        dsl.escreve("novoCampo", "Deu certo?");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void deveUtilizarEsperaExplicita() {
        dsl.clicar("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Deu certo?");
    }

}
