package br.jao.campotreinamento;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TesteElementosBasicos {
    
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
    public void testeTextField() {
        dsl.escreve("elementosForm:nome", "Teste de Escrita");
        Assert.assertEquals("Teste de Escrita", dsl.obterValorElemento("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        String txt = "Teste de Escrita\n\nMúltiplas\nLinhas";
        dsl.escreve("elementosForm:sugestoes", txt);
        Assert.assertEquals(txt, dsl.obterValorElemento("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicar("elementosForm:sexo:0");
        Assert.assertTrue(dsl.verificaMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicar("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.verificaMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarComboPorTextoVisivel("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo() {
        List<WebElement> options = dsl.obterOpcoesCombo("elementosForm:escolaridade");
        Assert.assertEquals(8, options.size());
        Assert.assertTrue(dsl.verificaOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
        dsl.selecionarComboPorTextoVisivel("elementosForm:escolaridade", "2o grau completo");
        Assert.assertTrue(dsl.verificaValorCombo("elementosForm:escolaridade", "2o grau completo"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "Natacao");
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "Corrida");
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "O que eh esporte?");

        List<WebElement> selected = dsl.obterValoresComboMultiplo("elementosForm:esportes");
        Assert.assertEquals(3, selected.size());

        Assert.assertTrue(dsl.verificaValorCombo("elementosForm:esportes", "Natacao"));
        
        dsl.deselecionarComboPorTextoVisivel("elementosForm:esportes", "Corrida");
        selected = dsl.obterValoresComboMultiplo("elementosForm:esportes");
        
        Assert.assertEquals(2, selected.size());
        Assert.assertFalse(dsl.verificaValorCombo("elementosForm:esportes", "Corrida"));
    }

    @Test
    public void deveInteragirComBotao() {
        Assert.assertEquals("Clique Me!", dsl.obterValorElemento("buttonSimple"));
        dsl.clicar("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValorElemento("buttonSimple"));
    }

    @Test
    // @Ignore
    public void deveInteragirComLink() {
        dsl.clicar(By.linkText("Voltar"));
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void verificaTextosNaPagina() {
        // WebElement body = driver.findElement(By.tagName("body"));
        // Assert.assertTrue(body.getText().contains("Campo de Treinamento"));

        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
            dsl.obterTexto(By.className("facilAchar")));
    }
}
