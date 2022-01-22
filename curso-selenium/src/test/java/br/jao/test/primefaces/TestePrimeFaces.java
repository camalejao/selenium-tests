package br.jao.test.primefaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.jao.core.DSL;
import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

public class TestePrimeFaces {
    
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        dsl = new DSL();
    }

    @After
    public void finalizaDriver() {
        killDriver();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");

        dsl.clicar(By.xpath("//*[@id='j_idt312:console:1']/../..//span"));
        Assert.assertTrue(dsl.verificaMarcado("j_idt312:console:1"));

        dsl.clicar(By.xpath("//label[.='Option3']/..//span"));
        Assert.assertTrue(dsl.verificaMarcado("j_idt312:console:2"));
    }

    @Test
    public void deveSelecionarComboPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        
        String idDiv = dsl.getElementId(By.xpath("//select[@aria-label='Basic']/../.."));

        dsl.selecionarComboPrime(idDiv, "Option3");

        Assert.assertEquals("Option3", dsl.obterValorComboPrime(idDiv));
    }

}
