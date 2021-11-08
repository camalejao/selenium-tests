package br.jao.campotreinamento;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;
import br.jao.pageobjects.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

    private WebDriver driver;
    private CampoTreinamentoPage page;
    private DSL dsl;

    @Parameter
    public String nome;
    @Parameter(value = 1)
    public String sobrenome;
    @Parameter(value = 2)
    public String sexo;
    @Parameter(value = 3)
    public List<String> comidas;
    @Parameter(value = 4)
    public String[] esportes;
    @Parameter(value = 5)
    public String msg;

    @Before
    public void inicializaDriver() {
        driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finalizaDriver() {
        driver.quit();
    }
    
    @Parameters
    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][] {
            {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
            {"Nome", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
            {"Nome", "Sobrenome", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
            {"Nome", "Sobrenome", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
            {"Nome", "Sobrenome", "Feminino", Arrays.asList("Frango", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
            {"Nome", "Sobrenome", "Feminino", Arrays.asList("Vegetariano"), new String[]{"Corrida", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }

    @Test
    public void deveValidarRegras() {
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        
        if (sexo.equals("Masculino")) page.setSexoMasculino();
        else if (sexo.equals("Feminino")) page.setSexoFeminino();

        if (comidas.contains("Carne")) page.setComidaCarne();
        if (comidas.contains("Frango")) page.setComidaFrango();
        if (comidas.contains("Pizza")) page.setComidaPizza();
        if (comidas.contains("Vegetariano")) page.setComidaVegetariano();

        page.setEsporte(esportes);

        page.cadastrar();

        Assert.assertEquals(msg, dsl.obterTextoAlert());
    }

}
