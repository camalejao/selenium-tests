package br.jao.test.misc;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import static br.jao.core.DriverFactory.getDriver;
import static br.jao.core.DriverFactory.killDriver;

public class TesteGoogle {
    
    @Test
    public void teste() {
        
        // Posição e Tamanho do Browser
        getDriver().manage().window().setPosition(new Point(0, 0));
        getDriver().manage().window().setSize(new Dimension(1200, 660));
        getDriver().manage().window().maximize();
        
        getDriver().get("https://google.com");
        
        Assert.assertEquals("Google", getDriver().getTitle());
        
        killDriver();
    }

}
