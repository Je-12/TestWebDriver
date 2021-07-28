package Test;

import Pages.LoginPage;
import Suporte.Web;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacaoUserPageTest.csv")

public class InformacaoUserPageTest {
    private WebDriver navegador;

    @Before
    public void setup(){
        navegador = Web.createBrowserStack();
    }
    @Test
    public void testAdicionainfoUser (
            @Param(name = "login")String login,
            @Param(name = "senha")String senha,
            @Param(name = "tipo")String tipo,
            @Param(name = "contato")String contato,
            @Param(name = "mensagem")String mensagemEsperada)
        {
        String textoToast = new LoginPage(navegador)
                .clickSingIn()
                .FazerLogin(login, senha)
                .clicarME()
                .clicarMoreDataAboutYou()
                .clicarBtnAddMoreDataAbout()
                .adicionarContato(tipo, contato)
                .capturarTextoToast();

        assertEquals(mensagemEsperada, textoToast);
    }

    @After
    public void tearDown(){
        //navegador.quit();
    }
}
