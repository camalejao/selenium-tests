package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TesteAlert {
    
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
    public void deveInteragirComAlertSimples() {
        dsl.clicar("alert");

        String alertText = dsl.obterTextoAceitandoAlert();
        Assert.assertEquals("Alert Simples", alertText);

        dsl.escreve("elementosForm:nome", alertText);
        Assert.assertEquals(alertText, dsl.obterValorElemento("elementosForm:nome"));
    }

    @Test
    public void deveConfirmarAlerta() {
        dsl.clicar("confirm");
        Assert.assertEquals("Confirm Simples", dsl.obterTextoAceitandoAlert());
        Assert.assertEquals("Confirmado", dsl.obterTextoAceitandoAlert());
    }

    @Test
    public void deveRejeitarAlerta() {
        dsl.clicar("confirm");
        Assert.assertEquals("Confirm Simples", dsl.obterTextoRejeitandoAlert());
        Assert.assertEquals("Negado", dsl.obterTextoRejeitandoAlert());
    }

    @Test
    public void deveInteragirComPrompt() {
        dsl.clicar("prompt");
        Assert.assertEquals("Digite um numero", dsl.obterTextoAlert());
        dsl.escreverAlert("42");
        Assert.assertEquals("Era 42?", dsl.obterTextoAceitandoAlert());
        Assert.assertEquals(":D", dsl.obterTextoAceitandoAlert());
    }
}