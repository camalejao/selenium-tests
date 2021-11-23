package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.core.Properties;
import br.jao.pages.ContasPage;
import br.jao.pages.MenuPage;

public class ContaComMovimentacaoTest extends BaseTest {
    
    private MenuPage menuPage = new MenuPage();
    private ContasPage contasPage = new ContasPage();

    @Test
    public void testaExcluirContaComMovimentacao() {
        menuPage.clicarLinkListarContas();
        contasPage.clicarExcluirConta(Properties.NOME_CONTA_ALT);
        Assert.assertEquals("Conta em uso na movimentações", contasPage.getMensagemErro());
    }
}
