package SeleniumTest.TC_IS;

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
import java.util.concurrent.TimeUnit;

public class NuovoIndirizzoCognomNonValido1Test {
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
    public void nuovoIndirizzoCognomNonValido1() {

        driver.get("http://localhost:8080/bollicineSito_war_exploded/View/Login_Logout/LoginView.jsp");
        //driver.manage().window().setSize(new Dimension(1936, 1056));
        driver.findElement(By.id("emailControlLog")).click();
        driver.findElement(By.id("emailControlLog")).sendKeys("email@ema.it");
        driver.findElement(By.id("pwd1")).sendKeys("rocco");
        driver.findElement(By.cssSelector(".buttonLogin:nth-child(7)")).click();

        WebElement element=driver.findElement(By.className("buttonAddCartCatal0"));
        js.executeScript("arguments[0].click();",element);

        driver.findElement(By.id("qtDaInserire1")).click();
        driver.findElement(By.id("qtDaInserire1")).sendKeys("1");
        WebElement element2=driver.findElement(By.id("buttonAddCartModal1"));
        js.executeScript("arguments[0].click();",element2);

        driver.switchTo().alert().accept();

        driver.findElement(By.cssSelector(".navElement:nth-child(4)")).click();

        driver.findElement(By.id("buttonProcedi")).click();
        driver.findElement(By.id("myBtnIn1")).click();
        driver.findElement(By.name("cognome")).click();
        driver.findElement(By.name("cognome")).sendKeys("a");
        {
            WebElement element3 = driver.findElement(By.id("salvaIndirizzo"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element3).perform();
        }
        {
            WebElement element3 = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element3, 0, 0).perform();
        }
        {
            WebElement element3 = driver.findElement(By.id("salvaIndirizzo"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element3).clickAndHold().perform();
        }
        {
            WebElement element3 = driver.findElement(By.id("formIndirizzo"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element3).release().perform();
        }
        driver.findElement(By.id("formIndirizzo")).click();

        String errorMsg=driver.findElement(By.id("cognome-error")).getText();

        assertEquals("si prega di inserire almeno due caratteri",errorMsg);

    }
}
