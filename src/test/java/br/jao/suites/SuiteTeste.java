package br.jao.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jao.campotreinamento.TesteCadastro;
import br.jao.campotreinamento.TesteElementosBasicos;
import br.jao.campotreinamento.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
    TesteCadastro.class,
    TesteRegrasCadastro.class,
    TesteElementosBasicos.class,
})
public class SuiteTeste {
    
}
