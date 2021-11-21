package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.ContasPage;
import br.jao.pages.MenuPage;

public class ContaTest extends BaseTest {

    private MenuPage menuPage = new MenuPage();
    private ContasPage contasPage = new ContasPage();

    public void adicionarConta(String nome) {
        menuPage.clicarLinkAdicionarConta();
        contasPage.setNomeConta(nome);
        contasPage.clicarBotaoSalvar();
    }

    @Test
    public void deveAdicionarConta() {
        adicionarConta("nova conta");
        Assert.assertEquals("Conta adicionada com sucesso!", contasPage.getMensagemSucesso());
    }

    @Test
    public void deveEditarConta() {
        menuPage.clicarLinkListarContas();
        contasPage.clicarEditarConta("nova conta");
        contasPage.setNomeConta("conta editada");
        contasPage.clicarBotaoSalvar();
        Assert.assertEquals("Conta alterada com sucesso!", contasPage.getMensagemSucesso());
    }

    @Test
    public void testeAdicionarContaMesmoNome() {
        adicionarConta("conta editada");
        Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.getMensagemErro());
    }

}
