package SeleniumTest.TC_LU;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
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
public class LoginUtenteEmailNonVaida3Test {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/webapp/WEB-INF/utility/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void loginUtenteEmailNonVaida3() {
        driver.get("http://localhost:8080/bollicineSito_war_exploded/View/Login_Logout/LoginView.jsp");
        driver.manage().window().setSize(new Dimension(1936, 1056));
        driver.findElement(By.id("emailControlLog")).click();
        driver.findElement(By.id("emailControlLog")).sendKeys("email@ema.ue");
        driver.findElement(By.id("pwd1")).click();
        assertEquals("Email non valida!",driver.switchTo().alert().getText());
    }
}
