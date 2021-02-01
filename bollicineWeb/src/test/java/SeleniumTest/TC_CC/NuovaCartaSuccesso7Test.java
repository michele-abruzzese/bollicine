package SeleniumTest.TC_CC;

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
public class NuovaCartaSuccesso7Test {
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
    public void nuovaCartaSuccesso7() {
        driver.get("http://localhost:8080/bollicineSito_war_exploded/View/Carrello/CartCheckoutView.jsp");
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.id("myBtnCt1")).click();
        driver.findElement(By.cssSelector("#myModalCt1 input:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#myModalCt1 input:nth-child(2)")).sendKeys("Rossi");
        driver.findElement(By.cssSelector("#myModalCt1 input:nth-child(3)")).sendKeys("Mario");
        driver.findElement(By.name("numero")).sendKeys("1234567891123");
        driver.findElement(By.name("ccv")).sendKeys("123");
        driver.findElement(By.name("scadenza")).click();
        driver.findElement(By.name("scadenza")).sendKeys("2021-02-02");
        driver.findElement(By.cssSelector(".buttonFormModal:nth-child(7)")).click();
    }
}
