package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class ResumoPage extends BasePage {
    
    public void excluirPrimeiraMovimentacao() {
        clicar(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
    }

}
