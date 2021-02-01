package SeleniumTest.TC_IMP;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class InserimentoModificaProdottoDisponibilitNoNValida6Test {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Michele\\IdeaProjects\\bollicine\\bollicineWeb\\src\\main\\webapp\\WEB-INF\\utility\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void inserimentoModificaProdottoDisponibilitNoNValida6() {
        driver.get("http://localhost:8080/bollicineSito_war_exploded/VisualizzaProdotti");
        driver.manage().window().setSize(new Dimension(1936, 1056));
        driver.findElement(By.cssSelector("#formInsert > form")).click();
        driver.findElement(By.name("nome")).click();
        driver.findElement(By.name("nome")).sendKeys("Colli Di Luni Vermentino DOC");
        driver.findElement(By.name("descrizione")).click();
        driver.findElement(By.name("descrizione")).sendKeys("è un bianco fresco e armonico");
        driver.findElement(By.name("tipo")).click();
        driver.findElement(By.name("tipo")).sendKeys("Vermentino");
        driver.findElement(By.name("annata")).click();
        driver.findElement(By.name("annata")).sendKeys("2020");
        driver.findElement(By.name("prezzo")).click();
        driver.findElement(By.name("prezzo")).sendKeys("10.5");
        driver.findElement(By.cssSelector(".buttonProtected:nth-child(25)")).click();
    }
}
