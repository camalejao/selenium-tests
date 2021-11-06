package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TesteCadastro {
    
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
    public void deveRealizarCadastro() {
        // Nome
        dsl.escreve("elementosForm:nome", "João");
        // Sobrenome
        dsl.escreve("elementosForm:sobrenome", "Falcão");
        // Sexo
        dsl.clicar("elementosForm:sexo:0");
        // Comidas Favoritas
        dsl.clicar("elementosForm:comidaFavorita:0");
        dsl.clicar("elementosForm:comidaFavorita:2");
        // Escolaridade
        dsl.selecionarComboPorTextoVisivel("elementosForm:escolaridade", "2o grau completo");
        // Esportes
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "Corrida");
        dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", "Natacao");
        // Sugestões
        dsl.escreve("elementosForm:sugestoes", "Melhorar Alimentação");
        // Clicar em Cadastrar
        dsl.clicar("elementosForm:cadastrar");

        // Verifica Cadastro (Completo, não-ideal)
        Assert.assertEquals("Cadastrado!" + 
            "\nNome: João" +
            "\nSobrenome: Falcão" +
            "\nSexo: Masculino" +
            "\nComida: Carne Pizza" +
            "\nEscolaridade: 2graucomp" +
            "\nEsportes: Natacao Corrida" +
            "\nSugestoes: Melhorar Alimentação", dsl.obterTexto("resultado"));
        
        // Verificando cada campo individualmente (ainda não-ideal)
        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("João"));
        Assert.assertTrue(dsl.obterTexto("descSobrenome").endsWith("Falcão"));
        Assert.assertTrue(dsl.obterTexto("descSexo").endsWith("Masculino"));
        Assert.assertTrue(dsl.obterTexto("descComida").endsWith("Carne Pizza"));
        Assert.assertTrue(dsl.obterTexto("descEscolaridade").endsWith("2graucomp"));
        Assert.assertTrue(dsl.obterTexto("descEsportes").endsWith("Natacao Corrida"));
        Assert.assertTrue(dsl.obterTexto("descSugestoes").endsWith("Melhorar Alimentação"));
    }

    @Test
    public void deveReescreverNome() {
        dsl.escreve("elementosForm:nome", "João");
        Assert.assertEquals("João", dsl.obterValorElemento("elementosForm:nome"));
        dsl.escreve("elementosForm:nome", "Victor");
        Assert.assertEquals("Victor", dsl.obterValorElemento("elementosForm:nome"));
    }

}
