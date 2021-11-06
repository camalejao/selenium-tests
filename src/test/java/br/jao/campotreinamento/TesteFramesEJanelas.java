package br.jao.campotreinamento;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
    
    @Test
    public void deveInteragirComFrame() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);

        alert.dismiss();
        driver.switchTo().defaultContent();
        
        WebElement text = driver.findElement(By.id("elementosForm:nome"));
        text.sendKeys(msg);
        
        Assert.assertEquals("Frame OK!", text.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComPopup() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo no PopUp");
        driver.close();
        driver.switchTo().window(originalWindow);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora na janela principal");

        driver.quit();
    }

    @Test
    public void deveInteragirComPopupSemTitulo() {
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        
        String url = "file:///" + System.getProperty("user.dir") + "/src/test/resources/componentes.html";
        driver.get(url);

        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.id("buttonPopUpHard")).click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);

        driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo no PopUp");
        
        driver.switchTo().window(originalWindow);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora na janela principal");

        driver.quit();
    }

}
