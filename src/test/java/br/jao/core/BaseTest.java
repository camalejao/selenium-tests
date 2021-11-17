package br.jao.core;

import org.junit.After;

import static br.jao.core.DriverFactory.killDriver;

public class BaseTest {
    
    @After
    public void finalizaDriver() {
        killDriver();
    }

}
