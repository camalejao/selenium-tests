package br.jao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCTCadastro {
    
    @Test
    public void deveRealizarCadastro() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        // Nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("João");

        // Sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Falcão");

        // Sexo
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        
        // Comidas Favoritas
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        
        // Escolaridade
        Select selectEscolaridade = new Select(driver.findElement(By.id("elementosForm:escolaridade")));
        selectEscolaridade.selectByVisibleText("2o grau completo");

        // Esportes
        Select selectEsportes = new Select(driver.findElement(By.id("elementosForm:esportes")));
        selectEsportes.selectByVisibleText("Corrida");
        selectEsportes.selectByVisibleText("Natacao");
        
        // Sugestões
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Melhorar Alimentação");
        
        // Clicar em Cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        // Verifica Cadastro (Completo, não-ideal)
        String txt = driver.findElement(By.id("resultado")).getText();
        Assert.assertEquals("Cadastrado!" + 
            "\nNome: João" +
            "\nSobrenome: Falcão" +
            "\nSexo: Masculino" +
            "\nComida: Carne Pizza" +
            "\nEscolaridade: 2graucomp" +
            "\nEsportes: Natacao Corrida" +
            "\nSugestoes: Melhorar Alimentação", txt);
        
        // Verificando cada campo individualmente (ainda não-ideal)
        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("João"));
        Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Falcão"));
        Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
        Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Carne Pizza"));
        Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("2graucomp"));
        Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Natacao Corrida"));
        Assert.assertTrue(driver.findElement(By.id("descSugestoes")).getText().endsWith("Melhorar Alimentação"));

        driver.quit();
    }

}
