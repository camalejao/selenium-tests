package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class HomePage extends BasePage {
    
    public String getMensagemBemVindo() {
        return obterTexto(By.xpath("//div[contains(text(), 'Bem vindo')]"));
    }
}
