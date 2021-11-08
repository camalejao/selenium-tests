package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.jao.dsl.DSL;

public class TesteFramesEJanelas {
    
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
    public void deveInteragirComFrame() {
        dsl.entrarFrame("frame1");
        dsl.clicar("frameButton");

        String msg = dsl.obterTextoAceitandoAlert();
        Assert.assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        
        dsl.escreve("elementosForm:nome", msg);
        Assert.assertEquals("Frame OK!", dsl.obterValorElemento("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComPopup() {
        String originalWindow = driver.getWindowHandle();

        dsl.clicar("buttonPopUpEasy");
        dsl.trocarJanela("Popup");

        dsl.escreve(By.tagName("textarea"), "Escrevendo no PopUp");
        driver.close();

        dsl.trocarJanela(originalWindow);
        dsl.escreve(By.tagName("textarea"), "E agora na janela principal");
    }

    @Test
    public void deveInteragirComPopupSemTitulo() {
        String originalWindow = driver.getWindowHandle();

        dsl.clicar("buttonPopUpHard");
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);

        dsl.escreve(By.tagName("textarea"), "Escrevendo no PopUp");
        
        dsl.trocarJanela(originalWindow);
        dsl.escreve(By.tagName("textarea"), "E agora na janela principal");
    }

    @Test
    public void deveInteragirComFrameEscondido() {
        dsl.executarJS("window.scrollBy(0, arguments[0])", driver.findElement(By.id("frame2")).getLocation().getY());
        dsl.entrarFrame("frame2");
        dsl.clicar("frameButton");
        String msg = dsl.obterTextoAceitandoAlert();
        Assert.assertEquals("Frame OK!", msg);
    }

}
