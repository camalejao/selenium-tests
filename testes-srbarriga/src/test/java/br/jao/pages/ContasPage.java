package br.jao.pages;

import org.openqa.selenium.By;

import br.jao.core.BasePage;

public class ContasPage extends BasePage {
    
    public void setNomeConta(String nome) {
        escreve("nome", nome);
    }

    public void clicarBotaoSalvar() {
        clicar(By.xpath("//button[.='Salvar']"));
    }

    public String getMensagemSucesso() {
        return obterTexto(By.xpath("//div[@class='alert alert-success' and @role='alert']"));
    }

    public void clicarEditarConta(String nomeConta) {
        // usando xpath diretamente
        String xpathExp = "//table[@id='tabelaContas']//td[.='" +
            nomeConta + "']/../td//a[1]";
        clicar(By.xpath(xpathExp));

        // alternativa usando métodos da DSL
        // obterCelulaTabela("Conta", nomeConta, "Ações", "tabelaContas")
        //     .findElement(By.xpath(".//span[@class='glyphicon glyphicon-edit']")).click();
    }

    public String getMensagemErro() {
        return obterTexto(By.xpath("//div[@class='alert alert-danger' and @role='alert']"));
    }
}
