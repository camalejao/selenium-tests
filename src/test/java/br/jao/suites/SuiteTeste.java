package br.jao.suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jao.core.DriverFactory;
import br.jao.test.campotreinamento.TesteCadastro;
import br.jao.test.campotreinamento.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
    TesteCadastro.class,
    TesteRegrasCadastro.class,
})
public class SuiteTeste {
    
    @AfterClass
    public static void finaliza() {
        DriverFactory.killDriver();
    }

}
