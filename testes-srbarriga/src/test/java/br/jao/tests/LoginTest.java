package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.HomePage;

public class LoginTest extends BaseTest {

    private HomePage page = new HomePage();

    @Test
    public void deveRealizarLogin() {
        Assert.assertEquals("Bem vindo, João!", page.getMensagemBemVindo());
    }
}
