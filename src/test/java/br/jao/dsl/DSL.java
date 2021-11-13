package br.jao.dsl;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {
    
    WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }
    
    public void escreve(String idCampo, String texto) {
        escreve(By.id(idCampo), texto);
    }

    public void escreve(By by, String texto) {
        limpa(by);
        driver.findElement(by).sendKeys(texto);
    }

    public void escreveSemLimpar(String idCampo, String texto) {
        escreveSemLimpar(By.id(idCampo), texto);
    }

    public void escreveSemLimpar(By by, String texto) {
        driver.findElement(by).sendKeys(texto);
    }

    public void limpa(By by) {
        driver.findElement(by).clear();
    }

    public void limpa(String idCampo) {
        limpa(By.id(idCampo));
    }

    public String obterValorElemento(String idElemento) {
        return driver.findElement(By.id(idElemento)).getAttribute("value");
    }

    public void clicar(String idElemento) {
        driver.findElement(By.id(idElemento)).click();
    }

    public boolean verificaMarcado(String idElemento) {
        return driver.findElement(By.id(idElemento)).isSelected();
    }

    public void selecionarComboPorTextoVisivel(String idSelect, String texto) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        combo.selectByVisibleText(texto);
    }

    public void deselecionarComboPorTextoVisivel(String idSelect, String texto) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        combo.deselectByVisibleText(texto);
    }

    public void selecionarComboPorIndex(String idSelect, int index) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        combo.selectByIndex(index);
    }

    public void selecionarComboPorValor(String idSelect, String valor) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        combo.selectByValue(valor);
    }

    public List<WebElement> obterOpcoesCombo(String idSelect) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        return combo.getOptions();
    }

    public boolean verificaOpcaoCombo(String idSelect, String opcao) {
        boolean result = false;

        for (WebElement option : obterOpcoesCombo(idSelect)) {
            if (option.getText().equals(opcao)) result = true;
        }

        return result;
    }

    public boolean verificaValorCombo(String idSelect, String opcao) {
        boolean result = false;
        
        for (WebElement option : obterValoresComboMultiplo(idSelect)) {
            if (option.getText().equals(opcao)) result = true;
        }

        return result;
    }

    public String obterValorCombo(String idSelect) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        return combo.getFirstSelectedOption().getText();
    }

    public List<WebElement> obterValoresComboMultiplo(String idSelect) {
        Select combo = new Select(driver.findElement(By.id(idSelect)));
        return combo.getAllSelectedOptions();
    }

    public void clicar(By by) {
        driver.findElement(by).click();
    }

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }

    public String obterTexto(String idElemento) {
        return obterTexto(By.id(idElemento));
    }

    public Alert esperarAlerta(int milis) {
        return new WebDriverWait(driver, Duration.ofMillis(milis))
            .until(ExpectedConditions.alertIsPresent());
    }

    public String obterTextoAlert() {
        return esperarAlerta(500).getText();
    }

    public String obterTextoAceitandoAlert() {
        Alert alert = esperarAlerta(500);
        String txt = alert.getText();
        alert.accept();
        return txt;
    }

    public String obterTextoRejeitandoAlert() {
        Alert alert = esperarAlerta(500);
        String txt = alert.getText();
        alert.dismiss();
        return txt;
    }

    public void escreverAlert(String texto) {
        Alert alert = esperarAlerta(500);
        alert.sendKeys(texto);
        alert.accept();
    }

    public void entrarFrame(String idFrame) {
        driver.switchTo().frame(idFrame);
    }

    public void sairFrame() {
        driver.switchTo().defaultContent();
    }

    public void trocarJanela(String idJanela) {
        driver.switchTo().window(idJanela);
    }

    public Object executarJS(String script, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, param);
    }

    public void clicarBotaoTabela(String tituloColuna, String textoLinha, String tituloColunaBotao, String idTabela) {
        WebElement tabela = driver.findElement(By.xpath("//*[@id='" + idTabela + "']"));
        
        int indexColuna = obterIndiceColuna(tituloColuna, tabela);
        int indexLinha = obterIndiceLinha(textoLinha, tabela, indexColuna);
        int indexColunaBotao = obterIndiceColuna(tituloColunaBotao, tabela);

        WebElement celula = tabela.findElement(By.xpath("./tbody/tr[" + indexLinha + "]/td[" + indexColunaBotao + "]"));
        celula.findElement(By.xpath(".//input")).click();
    }

    private int obterIndiceColuna(String tituloColuna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int indexColuna = -1;
        
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(tituloColuna)) {
                indexColuna = i + 1; // xpath é indexado a partir de 1
                break;
            }
        }

        return indexColuna;
    }

    private int obterIndiceLinha(String textoLinha, WebElement tabela, int indexColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + indexColuna + "]"));
        int indexLinha = -1;
        
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(textoLinha)) {
                indexLinha = i + 1; // xpath é indexado a partir de 1
                break;
            }
        }

        return indexLinha;
    }

    public String getElementId(By by) {
        return driver.findElement(by).getAttribute("id");
    }

    public void selecionarComboPrime(String radical, String textoOpcao) {
        clicar(By.xpath("//*[@id='" + radical + "']//label"));
        clicar(By.xpath("//*[@id='" + radical + "_items']//li[.='" + textoOpcao + "']"));
    }

    public String obterValorComboPrime(String radical) {
        return obterTexto(By.xpath("//*[@id='" + radical + "']//label"));
    }
}
