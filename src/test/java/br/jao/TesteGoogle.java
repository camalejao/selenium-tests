package br.jao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
    
    @Test
    public void teste() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        // Posição e Tamanho do Browser
        // driver.manage().window().setPosition(new Point(0, 0));
        // driver.manage().window().setSize(new Dimension(1200, 660));
        // driver.manage().window().maximize();
        
        driver.get("https://google.com");
        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }

}
