package br.jao.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.jao.dsl.DSL;

public class CampoTreinamentoPage {
    
    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String nome) {
        dsl.escreve("elementosForm:nome", nome);
    }

    public String obterNome() {
        return dsl.obterValorElemento("elementosForm:nome");
    }
    
    public void setSobrenome(String sobrenome) {
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clicar("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicar("elementosForm:sexo:1");
    }

    public void setComidaCarne() {
        dsl.clicar("elementosForm:comidaFavorita:0");
    }

    public void setComidaFrango() {
        dsl.clicar("elementosForm:comidaFavorita:1");
    }

    public void setComidaPizza() {
        dsl.clicar("elementosForm:comidaFavorita:2");
    }

    public void setComidaVegetariano() {
        dsl.clicar("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valorVisivel) {
        dsl.selecionarComboPorTextoVisivel("elementosForm:escolaridade", valorVisivel);
    }

    public void setEsporte(String... valores) {
        for (String valor : valores)
            dsl.selecionarComboPorTextoVisivel("elementosForm:esportes", valor);
    }

    public void setSugestoes(String sugestoes) {
        dsl.escreve("elementosForm:sugestoes", sugestoes);
    }

    public void cadastrar() {
        dsl.clicar("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
    }

    public String obterSexoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
    }

    public String obterComidaCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
    }

    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
    }

    public String obterEsportesCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
    }

    public String obterSugestoesCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSugestoes']/span"));
    }

}
