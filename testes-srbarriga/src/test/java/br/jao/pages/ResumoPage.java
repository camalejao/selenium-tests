package br.jao.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.jao.core.BasePage;
import br.jao.core.DriverFactory;

public class ResumoPage extends BasePage {
    
    public void excluirPrimeiraMovimentacao() {
        clicar(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
    }

    public void selecionarAno(String ano) {
        selecionarComboPorTextoVisivel("ano", ano);
    }

    public int obterQuantidadeMovimentacoes() {
        List<WebElement> movimentacoes = DriverFactory.getDriver().findElements(By.xpath(
            "//table[@id='tabelaExtrato']/tbody/tr"
        ));
        return movimentacoes.size();
    }

    public void clicarBuscar() {
        clicar(By.xpath("//input[@value='Buscar']"));
    }

}
