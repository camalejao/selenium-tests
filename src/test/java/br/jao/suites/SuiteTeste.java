package br.jao.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jao.test.campotreinamento.TesteCadastro;
import br.jao.test.campotreinamento.TesteElementosBasicos;
import br.jao.test.campotreinamento.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
    TesteCadastro.class,
    TesteRegrasCadastro.class,
    TesteElementosBasicos.class,
})
public class SuiteTeste {
    
}
