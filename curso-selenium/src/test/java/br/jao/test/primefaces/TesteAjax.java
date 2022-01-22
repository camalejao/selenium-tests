package br.jao.test.primefaces;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.jao.core.DSL;
import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

public class TesteAjax {

    private DSL dsl;

    @Before
    public void inicializaDriver() {
        String url = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml";
        getDriver().get(url);
        dsl = new DSL();
    }

    @After
    public void finalizaDriver() {
        killDriver();
    }

    @Test
    public void testeAjax() {
        dsl.escreve("j_idt311:name", "Teste");
        dsl.clicar("j_idt311:j_idt315");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt311:display"), "Teste"));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt311:display"));
    }

    @Test
    public void testeAlternativo() {
        dsl.escreve("j_idt311:name", "Teste");
        dsl.clicar("j_idt311:j_idt315");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.textToBe(By.id("j_idt311:display"), "Teste"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'pi-spinner')]")));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt311:display"));
    }
}
