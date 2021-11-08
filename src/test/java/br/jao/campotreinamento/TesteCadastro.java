package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.pageobjects.CampoTreinamentoPage;

public class TesteCadastro {
    
    private WebDriver driver;
    private CampoTreinamentoPage page;

    @Before
    public void inicializaDriver() {
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finalizaDriver() {
        driver.quit();
    }

    @Test
    public void deveRealizarCadastro() {
        page.setNome("João");
        page.setSobrenome("Falcão");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setComidaPizza();
        page.setEscolaridade("2o grau completo");
        page.setEsporte("Corrida");
        page.setEsporte("Natacao");
        page.setSugestoes("Melhorar Alimentação");
        page.cadastrar();

        // Verifica Cadastro (Completo, não-ideal)
        Assert.assertEquals("Cadastrado!" + 
            "\nNome: João" +
            "\nSobrenome: Falcão" +
            "\nSexo: Masculino" +
            "\nComida: Carne Pizza" +
            "\nEscolaridade: 2graucomp" +
            "\nEsportes: Natacao Corrida" +
            "\nSugestoes: Melhorar Alimentação", page.obterResultadoCadastro());
        
        // Verificando cada campo individualmente (ainda não-ideal)
        Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.obterNomeCadastro().endsWith("João"));
        Assert.assertTrue(page.obterSobrenomeCadastro().endsWith("Falcão"));
        Assert.assertTrue(page.obterSexoCadastro().endsWith("Masculino"));
        Assert.assertTrue(page.obterComidaCadastro().endsWith("Carne Pizza"));
        Assert.assertTrue(page.obterEscolaridadeCadastro().endsWith("2graucomp"));
        Assert.assertTrue(page.obterEsportesCadastro().endsWith("Natacao Corrida"));
        Assert.assertTrue(page.obterSugestoesCadastro().endsWith("Melhorar Alimentação"));
    }

    @Test
    public void deveReescreverNome() {
        page.setNome("João");
        Assert.assertEquals("João", page.obterNome());
        page.setNome("Victor");
        Assert.assertEquals("Victor", page.obterNome());
    }
}
