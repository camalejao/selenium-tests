package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TestePrimeFaces {
    
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        dsl = new DSL(driver);
    }

    @After
    public void finalizaDriver() {
        driver.quit();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");

        dsl.clicar(By.xpath("//*[@id='j_idt305:console:1']/../..//span"));
        Assert.assertTrue(dsl.verificaMarcado("j_idt305:console:1"));

        dsl.clicar(By.xpath("//label[.='Option3']/..//span"));
        Assert.assertTrue(dsl.verificaMarcado("j_idt305:console:2"));
    }

    @Test
    public void deveSelecionarComboPrime() {
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        
        String idDiv = dsl.getElementId(By.xpath("//select[@aria-label='Basic']/../.."));

        dsl.selecionarComboPrime(idDiv, "Option3");

        Assert.assertEquals("Option3", dsl.obterValorComboPrime(idDiv));
    }

}
