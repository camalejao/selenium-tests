package br.jao.suites;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jao.core.DriverFactory;
import br.jao.pages.LoginPage;
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
    SaldoTest.class,
    ResumoTest.class
})
public class SuiteGeral {

    private static LoginPage page = new LoginPage();

    @BeforeClass
    public static void resetMassaDeDados() {
        page.acessarTelaInicial();
        page.realizarLogin("joao@falcao", "123456");
        page.reset();

        DriverFactory.killDriver();
    }

}
