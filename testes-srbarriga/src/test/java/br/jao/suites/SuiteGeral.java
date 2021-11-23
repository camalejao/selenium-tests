package br.jao.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jao.core.DriverFactory;
import br.jao.pages.LoginPage;
import br.jao.tests.ContaComMovimentacaoTest;
import br.jao.tests.ContaTest;
import br.jao.tests.LoginTest;
import br.jao.tests.MovimentacaoTest;
import br.jao.tests.ResumoTest;
import br.jao.tests.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
    LoginTest.class,
    ContaTest.class,
    MovimentacaoTest.class,
    ContaComMovimentacaoTest.class,
    SaldoTest.class,
    ResumoTest.class
})
public class SuiteGeral {
    
    private static LoginPage page = new LoginPage();
    
    @BeforeClass
    public static void inicializaTestes() {
        page.acessarTelaInicial();
        page.realizarLogin("joao@falcao", "123456");
    }

    @AfterClass
    public static void finalizaTestes() {
        DriverFactory.killDriver();
    }
}
