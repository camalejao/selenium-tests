package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class MovimentacoesPage extends BasePage {
    
    public void setDataMovimentacao(String data) {
        escreve("data_transacao", data);
    }

    public void setDataPagamento(String data) {
        escreve("data_pagamento", data);
    }

    public void setDescricao(String texto) {
        escreve("descricao", texto);
    }

    public void setInteressado(String texto) {
        escreve("interessado", texto);
    }

    public void setValor(String valor) {
        escreve("valor", valor);
    }

    public void setSituacaoPago() {
        clicar("status_pago");
    }

    public void setSituacaoPendente() {
        clicar("status_pendente");
    }

    public void setConta(String nomeConta) {
        selecionarComboPorTextoVisivel("conta", nomeConta);
    }

    public void setTipo(String tipo) {
        selecionarComboPorTextoVisivel("tipo", tipo);
    }

    public void clicarSalvar() {
        clicarBotaoPorTexto("Salvar");
    }

    public String getMensagemSucesso() {
        return obterTexto(By.xpath("//div[@class='alert alert-success' and @role='alert']"));
    }
}
