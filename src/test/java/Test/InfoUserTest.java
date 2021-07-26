package Test;

import Suporte.Generator;
import Suporte.Screenshot;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacaoUserTest.csv")

public class InfoUserTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jezreel Santana\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
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

    //clicar no link com o texto "Sing in"
        navegador.findElement(By.linkText("SIGN IN")).click();

    //Clicar em um link de text que possui a class "me"
       navegador.findElement(By.className("me")).click();

    //clicar no link com o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionaInfoUser(@Param(name="tipo")String tipo,
                                     @Param(name="contato")String contato,
                                     @Param(name="mensagem")String mensagemEsperada){

        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

    // Identificar o popup onde esta o formulario
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

    // Combo de name "type" escolher a opção "Phone
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

    // No campo de name "contact" digitar "+5511999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

    // Clicar no link de text "SAVE" que está no popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

    // Na mensagem de id = "toast-container" validar que o texto é "Your contact has been added"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));

        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

    }

    @Test
    public void RemoverContdeUser(){
    //Clicar no elemento pelo seu xpath //*[@id="moredata"]/div[1]/ul/li[1]/a/i
        navegador.findElement(By.xpath("//*[@id=\"moredata\"]/div[1]/ul/li[1]/a/i")).click();

    //Confirmar a janela JavaSCRIP
        navegador.switchTo().alert().accept();

    //Validar que a mensagem foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();

        assertEquals("Rest in peace, dear phone!", mensagem);

        Screenshot.tirar(navegador,
                "C:\\Users\\Jezreel Santana\\Desktop\\Dev\\Projetos\\TestWebDriverJava\\src\\test\\java\\test_reports\\taskit\\"
                        + Generator.dataHoraArquivo() + test.getMethodName() + ".png");

    //Aguarda apenas 10 Segundos para janela desaparecer
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

    //Clicar no link com text "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }
     @After
     public void tearDown(){
        //navegador.quit();
     }
}
