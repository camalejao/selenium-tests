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

        // Verificando cada campo individualmente com xpath
        Assert.assertEquals(page.obterResultadoCadastro(), "Cadastrado!");
        Assert.assertEquals(page.obterNomeCadastro(), "João");
        Assert.assertEquals(page.obterSobrenomeCadastro(), "Falcão");
        Assert.assertEquals(page.obterSexoCadastro(), "Masculino");
        Assert.assertEquals(page.obterComidaCadastro(), "Carne Pizza");
        Assert.assertEquals(page.obterEscolaridadeCadastro(), "2graucomp");
        Assert.assertEquals(page.obterEsportesCadastro(), "Natacao Corrida");
        Assert.assertEquals(page.obterSugestoesCadastro(), "Melhorar Alimentação");
    }

    @Test
    public void deveReescreverNome() {
        page.setNome("João");
        Assert.assertEquals("João", page.obterNome());
        page.setNome("Victor");
        Assert.assertEquals("Victor", page.obterNome());
    }
}
