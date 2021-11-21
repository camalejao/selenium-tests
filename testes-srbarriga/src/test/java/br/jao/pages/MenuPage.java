package br.jao.pages;

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

}
