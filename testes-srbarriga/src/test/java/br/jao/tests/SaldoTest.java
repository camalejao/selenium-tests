package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.HomePage;
import br.jao.pages.MenuPage;

public class SaldoTest extends BaseTest {
    
    private HomePage page = new HomePage();
    private MenuPage menuPage = new MenuPage();

    @Test
    public void deveConferirSaldoConta() {
        menuPage.clicarLinkHome();
        Assert.assertEquals("534.00", page.obterSaldoConta("Conta para saldo"));
    }

}
