package br.jao.pages;

import org.openqa.selenium.By;

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
        clicar(By.xpath("//button[.='Entrar']"));
    }

    public void realizarLogin(String email, String senha) {
        setEmail(email);
        setSenha(senha);
        clicarBotaoLogin();
    }

}
