package br.jao.pages;

import br.jao.core.BasePage;
import br.jao.core.DriverFactory;

public class LoginPage extends BasePage {
    
    public void acessarTelaInicial() {
        DriverFactory.getDriver().get("https://seubarriga.wcaquino.me");
    }

    public void setEmail(String email) {
        escreve("email", email);
    }

    public void setSenha(String senha) {
        escreve("senha", senha);
    }

    public void clicarBotaoLogin() {
        clicarBotaoPorTexto("Entrar");
    }

    public void realizarLogin(String email, String senha) {
        setEmail(email);
        setSenha(senha);
        clicarBotaoLogin();
    }

    public void reset() {
        clicarLink("reset");
    }

}
