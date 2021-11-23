package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.core.Properties;
import br.jao.pages.HomePage;
import br.jao.pages.MenuPage;

public class SaldoTest extends BaseTest {
    
    private HomePage page = new HomePage();
    private MenuPage menuPage = new MenuPage();

    @Test
    public void deveConferirSaldoConta() {
        menuPage.clicarLinkHome();
        Assert.assertEquals("400.00", page.obterSaldoConta(Properties.NOME_CONTA_ALT));
    }

}
