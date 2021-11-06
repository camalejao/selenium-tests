package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TesteRegrasDeNegocio {

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
    public void nomeDeveSerObrigatorio() {
        dsl.clicar("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.obterTextoAlert());
    }

    @Test
    public void sobrenomeDeveSerObrigatorio() {
        dsl.escreve("elementosForm:nome", "Nome");
        dsl.clicar("elementosForm:cadastrar");

        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.obterTextoAlert());
    }

    @Test
    public void sexoDeveSerObrigatorio() {
        dsl.escreve("elementosForm:nome", "Nome");
        dsl.escreve("elementosForm:sobrenome", "Sobreome");
        dsl.clicar("elementosForm:cadastrar");

        Assert.assertEquals("Sexo eh obrigatorio", dsl.obterTextoAlert());
    }

    @Test
    public void testeVegetariano() {
        dsl.escreve("elementosForm:nome", "Nome");
        dsl.escreve("elementosForm:sobrenome", "Sobreome");
        dsl.clicar("elementosForm:sexo:1");
        
        // Carne e Vegetariano
        dsl.clicar("elementosForm:comidaFavorita:0");
        dsl.clicar("elementosForm:comidaFavorita:3");
        
        dsl.clicar("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterTextoRejeitandoAlert());

        // Frango e Vegetariano
        dsl.clicar("elementosForm:comidaFavorita:1"); // marca Frango
        dsl.clicar("elementosForm:comidaFavorita:0"); // desmarca Carne

        dsl.clicar("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterTextoAlert());
    }

    @Test
    public void testeEsportes() {
        dsl.escreve("elementosForm:nome", "Nome");
        dsl.escreve("elementosForm:sobrenome", "Sobreome");
        dsl.clicar("elementosForm:sexo:1");
        dsl.clicar("elementosForm:comidaFavorita:3");

        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "Corrida");
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "O que eh esporte?");
        
        dsl.clicar("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterTextoAlert());
    }

}
