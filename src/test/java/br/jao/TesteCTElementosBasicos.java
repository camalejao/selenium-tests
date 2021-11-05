package br.jao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCTElementosBasicos {
    
    @Test
    public void testeTextField() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement textField = driver.findElement(By.id("elementosForm:nome"));
        textField.sendKeys("Teste de Escrita");
        Assert.assertEquals("Teste de Escrita", textField.getAttribute("value"));
                
        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement textArea = driver.findElement(By.id("elementosForm:sugestoes"));
        textArea.sendKeys("Teste de Escrita\n\nMúltiplas\nLinhas");
        Assert.assertEquals("Teste de Escrita\n\nMúltiplas\nLinhas", textArea.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement radio = driver.findElement(By.id("elementosForm:sexo:0"));
        radio.click();

        Assert.assertTrue(radio.isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement check = driver.findElement(By.id("elementosForm:comidaFavorita:2"));
        check.click();

        Assert.assertTrue(check.isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCombo() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

        // combo.selectByIndex(2);
        // combo.selectByValue("superior");
        combo.selectByVisibleText("2o grau completo");

        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

        driver.quit();
    }

    @Test
    public void deveVerificarValoresCombo() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());
        
        boolean encontrado = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrado = true;
                break;
            }
        }
        Assert.assertTrue(encontrado);

        driver.quit();
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> selected = combo.getAllSelectedOptions();

        Assert.assertEquals(3, selected.size());

        List<String> valores = new ArrayList<>();
        for (WebElement option : selected) {
            valores.add(option.getText());
        }

        Assert.assertTrue(valores.contains("Natacao"));

        combo.deselectByVisibleText("Corrida");
        selected = combo.getAllSelectedOptions();
        Assert.assertEquals(2, selected.size());

        driver.quit();
    }

    @Test
    public void deveInteragirComBotao() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Clique Me!", botao.getAttribute("value"));
        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

        driver.quit();
    }

    @Test
    // @Ignore
    public void deveInteragirComLink() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        WebElement link = driver.findElement(By.linkText("Voltar"));
        link.click();

        WebElement div = driver.findElement(By.id("resultado"));
        Assert.assertEquals("Voltou!", div.getText());

        driver.quit();
    }

    @Test
    public void verificaTextosNaPagina() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        // WebElement body = driver.findElement(By.tagName("body"));
        // Assert.assertTrue(body.getText().contains("Campo de Treinamento"));

        Assert.assertEquals("Campo de Treinamento",
            driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
            driver.findElement(By.className("facilAchar")).getText());
        
        driver.quit();
    }
}
