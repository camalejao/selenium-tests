package br.jao.tests;

import static br.jao.utils.DateUtils.obterDataFormatada;
import static br.jao.utils.DateUtils.obterDataNoFuturo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        movPage.setDataMovimentacao(obterDataFormatada(LocalDate.now()));
        movPage.setDataPagamento(obterDataFormatada(LocalDate.now()));
        movPage.setDescricao("Depósito Teste");
        movPage.setInteressado("João");
        movPage.setValor("400");
        movPage.setConta("Conta para movimentacoes");
        movPage.setSituacaoPago();
        movPage.clicarSalvar();

        Assert.assertEquals("Movimentação adicionada com sucesso!", movPage.getMensagemSucesso());
    }

    @Test
    public void testaCamposObrigatorios() {
        menuPage.clicarLinkCriarMovimentacao();
        movPage.clicarSalvar();

        List<String> erros = movPage.getMensagensErro();

        Assert.assertTrue(erros.containsAll(Arrays.asList(
            "Data da Movimentação é obrigatório",
            "Data do pagamento é obrigatório",
            "Descrição é obrigatório",
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));

        Assert.assertEquals(6, erros.size());
    }

    @Test
    public void testaMovimentacaoFutura() {
        LocalDate dataFutura = obterDataNoFuturo(3);

        menuPage.clicarLinkCriarMovimentacao();
        movPage.setTipo("Receita");
        movPage.setDataMovimentacao(obterDataFormatada(dataFutura));
        movPage.setDataPagamento(obterDataFormatada(dataFutura));
        movPage.setDescricao("Teste Movimentação Futura");
        movPage.setInteressado("João");
        movPage.setValor("800");
        movPage.setConta("Conta para movimentacoes");
        movPage.setSituacaoPendente();
        movPage.clicarSalvar();

        List<String> erros = movPage.getMensagensErro();

        Assert.assertTrue(erros.contains(
            "Data da Movimentação deve ser menor ou igual à data atual"));
        
        Assert.assertEquals(1, erros.size());
    }

}
