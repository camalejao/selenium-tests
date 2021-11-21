package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class MenuPage extends BasePage {
    
    public void clicarLinkAdicionarConta() {
        clicar(By.linkText("Contas"));
        clicar(By.linkText("Adicionar"));
    }

}
