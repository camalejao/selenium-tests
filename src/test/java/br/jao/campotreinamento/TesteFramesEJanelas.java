package br.jao.campotreinamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.jao.core.DSL;
import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

public class TesteFramesEJanelas {
    
    private DSL dsl;

    @Before
    public void inicializaDriver() {
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        getDriver().get(url);
        dsl = new DSL();
    }

    @After
    public void finalizaDriver() {
        killDriver();
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
        String originalWindow = getDriver().getWindowHandle();

        dsl.clicar("buttonPopUpEasy");
        dsl.trocarJanela("Popup");

        dsl.escreve(By.tagName("textarea"), "Escrevendo no PopUp");
        getDriver().close();

        dsl.trocarJanela(originalWindow);
        dsl.escreve(By.tagName("textarea"), "E agora na janela principal");
    }

    @Test
    public void deveInteragirComPopupSemTitulo() {
        String originalWindow = getDriver().getWindowHandle();

        dsl.clicar("buttonPopUpHard");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);

        dsl.escreve(By.tagName("textarea"), "Escrevendo no PopUp");
        
        dsl.trocarJanela(originalWindow);
        dsl.escreve(By.tagName("textarea"), "E agora na janela principal");
    }

    @Test
    public void deveInteragirComFrameEscondido() {
        dsl.executarJS("window.scrollBy(0, arguments[0])", getDriver().findElement(By.id("frame2")).getLocation().getY());
        dsl.entrarFrame("frame2");
        dsl.clicar("frameButton");
        String msg = dsl.obterTextoAceitandoAlert();
        Assert.assertEquals("Frame OK!", msg);
    }

}
