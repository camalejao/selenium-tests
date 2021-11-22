package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.HomePage;

public class SaldoTest extends BaseTest {
    
    private HomePage page = new HomePage();

    @Test
    public void deveConferirSaldoConta() {
        Assert.assertEquals("400.00", page.obterSaldoConta("conta editada"));
    }

}
