package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class ContasPage extends BasePage {
    
    public void setNomeConta(String nome) {
        escreve("nome", nome);
    }

    public void clicarBotaoSalvar() {
        clicar(By.xpath("//button[.='Salvar']"));
    }

    public String getMensagemSucesso() {
        return obterTexto(By.xpath("//div[@class='alert alert-success' and @role='alert']"));
    }

    public void clicarEditarConta(String nomeConta) {
        String xpathExp = "//table[@id='tabelaContas']//td[.='" +
            nomeConta + "']/../td//a[1]";
        clicar(By.xpath(xpathExp));
    }
}
