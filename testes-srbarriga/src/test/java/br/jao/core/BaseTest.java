package br.jao.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.jao.pages.LoginPage;

import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    private LoginPage page = new LoginPage();
    
    @Rule
    public TestName testName = new TestName();

    @Before
    public void inicializaTeste() {
        page.acessarTelaInicial();
        page.realizarLogin("joao@falcao", "123456");
    }

    @After
    public void finalizaDriver() throws IOException {

        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator +
            "screenshots/" + File.separator + testName.getMethodName() + ".jpg"));

        if (Properties.QUIT_BROWSER) {
            killDriver();
        }
    }

}
