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
        adicionarConta("Nova Conta");
        Assert.assertEquals("Conta adicionada com sucesso!", contasPage.getMensagemSucesso());
    }

    @Test
    public void deveEditarConta() {
        menuPage.clicarLinkListarContas();
        contasPage.clicarEditarConta("Conta para alterar");
        contasPage.setNomeConta("Conta Alterada");
        contasPage.clicarBotaoSalvar();
        Assert.assertEquals("Conta alterada com sucesso!", contasPage.getMensagemSucesso());
    }

    @Test
    public void testeAdicionarContaMesmoNome() {
        adicionarConta("Conta mesmo nome");
        Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.getMensagemErro());
    }

    @Test
    public void testaExcluirContaComMovimentacao() {
        menuPage.clicarLinkListarContas();
        contasPage.clicarExcluirConta("Conta com movimentacao");
        Assert.assertEquals("Conta em uso na movimentações", contasPage.getMensagemErro());
    }

}
