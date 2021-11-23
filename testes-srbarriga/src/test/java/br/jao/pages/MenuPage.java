package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class MenuPage extends BasePage {
    
    public void clicarLinkAdicionarConta() {
        clicarLink("Contas");
        clicarLink("Adicionar");
    }

    public void clicarLinkListarContas() {
        clicarLink("Contas");
        clicarLink("Listar");
    }

    public void clicarLinkCriarMovimentacao() {
        clicarLink("Criar Movimentação");
    }

    public void clicarLinkResumoMensal() {
        clicarLink("Resumo Mensal");
    }

    public String getMensagemSucesso() {
        return obterTexto(By.xpath("//div[@class='alert alert-success' and @role='alert']"));
    }

    public void clicarLinkHome() {
        clicarLink("Home");
    }

}
