package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InfoUserTest {
    @Test
    public void testAdicionaInfoUser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jezreel Santana\\Drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.get("http://www.juliodelima.com.br/taskit");


        //Clicar no link que possui o texto "Sing in"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificar o formulario login
        WebElement Formsigninbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo Login que esta dentro do formulario, o texto julio0001
        Formsigninbox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo password o texto 123456
        Formsigninbox.findElement(By.name("password")).sendKeys("123456");

        //clicar no link "Sing in"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" esta o texto "Hi,julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoMe = me.getText();

        assertEquals("Hi, Julio", textoMe);

        navegador.quit();
    }
}
