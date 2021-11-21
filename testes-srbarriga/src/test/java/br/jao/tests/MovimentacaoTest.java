package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.MenuPage;
import br.jao.pages.MovimentacoesPage;

public class MovimentacaoTest extends BaseTest {
    
    private MenuPage menuPage = new MenuPage();
    private MovimentacoesPage movPage = new MovimentacoesPage();

    @Test
    public void deveCriarMovimentacao() {
        menuPage.clicarLinkCriarMovimentacao();
        movPage.setTipo("Receita");
        movPage.setDataMovimentacao("20/11/2021");
        movPage.setDataPagamento("20/11/2021");
        movPage.setDescricao("Depósito Teste");
        movPage.setInteressado("João");
        movPage.setValor("400");
        movPage.setConta("conta editada");
        movPage.setSituacaoPago();
        movPage.clicarSalvar();

        Assert.assertEquals("Movimentação adicionada com sucesso!", movPage.getMensagemSucesso());
    }

}
