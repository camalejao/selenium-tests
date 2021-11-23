package br.jao.tests;

import org.junit.Assert;
import org.junit.Test;

import br.jao.core.BaseTest;
import br.jao.pages.MenuPage;
import br.jao.pages.ResumoPage;

public class ResumoTest extends BaseTest {
    
    private MenuPage menuPage = new MenuPage();
    private ResumoPage resPage = new ResumoPage();

    @Test
    public void deveRemoverMovimentacao() {
        menuPage.clicarLinkResumoMensal();
        resPage.excluirPrimeiraMovimentacao();
        Assert.assertEquals("Movimentação removida com sucesso!",
            menuPage.getMensagemSucesso());
    }

    @Test
    public void verificaResumoMensalVazio() {
        menuPage.clicarLinkResumoMensal();
        Assert.assertEquals("Seu Barriga - Extrato", resPage.obterTitulo());

        resPage.selecionarAno("2016");
        resPage.clicarBuscar();
        Assert.assertEquals(0, resPage.obterQuantidadeMovimentacoes());
    }

}
