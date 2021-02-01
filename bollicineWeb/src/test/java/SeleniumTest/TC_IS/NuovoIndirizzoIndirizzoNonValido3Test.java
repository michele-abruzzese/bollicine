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
public class NuovoIndirizzoIndirizzoNonValido3Test {
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
    public void nuovoIndirizzoIndirizzoNonValido3() {
        driver.get("http://localhost:8080/bollicineSito_war_exploded/View/Carrello/CartCheckoutView.jsp");
        driver.manage().window().setSize(new Dimension(1936, 1056));
        driver.findElement(By.id("myBtnIn1")).click();
        driver.findElement(By.name("cognome")).click();
        driver.findElement(By.name("cognome")).sendKeys("Rossi");
        driver.findElement(By.name("nome")).sendKeys("Mario");
        driver.findElement(By.name("indirizzo")).sendKeys("v");
        driver.findElement(By.cssSelector("#myModalIn1 > .modal-content")).click();
        driver.findElement(By.cssSelector(".buttonFormModal:nth-child(9)")).click();
    }
}
