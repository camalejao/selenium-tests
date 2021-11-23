package br.jao.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.jao.core.BaseTest;
import br.jao.pages.MenuPage;
import br.jao.pages.ResumoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        Assert.assertEquals(0, resPage.obterQuantidadeMovimentacoes());
    }

}
