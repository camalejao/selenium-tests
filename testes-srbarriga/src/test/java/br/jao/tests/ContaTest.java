package br.jao.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.jao.core.BaseTest;
import br.jao.core.Properties;
import br.jao.pages.ContasPage;
import br.jao.pages.MenuPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        contasPage.setNomeConta(Properties.NOME_CONTA_ALT);
        contasPage.clicarBotaoSalvar();
        Assert.assertEquals("Conta alterada com sucesso!", contasPage.getMensagemSucesso());
    }

    @Test
    public void testeAdicionarContaMesmoNome() {
        adicionarConta(Properties.NOME_CONTA_ALT);
        Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.getMensagemErro());
    }

    // @Test
    // public void testaExcluirContaComMovimentacao() {
    //     menuPage.clicarLinkListarContas();
    //     contasPage.clicarExcluirConta(Properties.NOME_CONTA_ALT);
    //     Assert.assertEquals("Conta em uso na movimentações", contasPage.getMensagemErro());
    // }

}
