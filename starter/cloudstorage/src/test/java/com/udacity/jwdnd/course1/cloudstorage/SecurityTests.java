package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTests {

    @LocalServerPort
    private int port;


    private String hostName="http://localhost:";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();

    }


    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        this.webDriverWait = new WebDriverWait(driver ,1000);
    }

    @AfterEach
    public void afterEach(){
        if (this.driver !=null){
            driver.quit();
        }
    }

    @Test
    public void homeNotAccessibleWithOutLogin(){
        this.driver.get(hostName+this.port+"/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void signupAndLogin(){
        signup();
        loginAndLogout();
    }
    public void signup(){
        this.driver.get(hostName+this.port+"/signup");
        Assertions.assertEquals("Sign Up",driver.getTitle());

        WebElement fNameElement = this.driver.findElement(By.id("inputFirstName"));
        fNameElement.sendKeys("fTest");
        WebElement lNameElement = this.driver.findElement(By.id("inputLastName"));
        lNameElement.sendKeys("LTest");
        WebElement userNameElement = this.driver.findElement(By.id("inputUsername"));
        userNameElement.sendKeys("uTest");
        WebElement passwordElement = this.driver.findElement(By.id("inputPassword"));
        passwordElement.sendKeys("test@123");

        WebElement submitBtn = driver.findElement(By.tagName("button"));
        submitBtn.click();

    }

    public void loginAndLogout(){
        this.driver.get(hostName+this.port+"/login");

        Assertions.assertEquals("Login",driver.getTitle());

        WebElement username = this.driver.findElement(By.id("inputUsername"));
        username.sendKeys("uTest");

        WebElement password = this.driver.findElement(By.id("inputPassword"));
        password.sendKeys("test@123");

        WebElement submitBtn = driver.findElement(By.tagName("button"));
        submitBtn.click();
        Assertions.assertEquals("Home",this.driver.getTitle());

        WebElement logoutElement = driver.findElement(By.id("logoutBtn"));
        Assertions.assertEquals("Logout",logoutElement.getText());
        logoutElement.click();
        Assertions.assertEquals("Login",driver.getTitle());

    }

    public void checkHomePage(){
        this.driver.get(hostName+this.port+"/home");

        Assertions.assertEquals("Home",this.driver.getTitle());
    }

    @Test
    public void loginWithInvalid(){
        this.driver.get(hostName+this.port+"/login");
        Assertions.assertEquals("Login",driver.getTitle());

        WebElement username = this.driver.findElement(By.id("inputUsername"));
        username.sendKeys("invalidUser");

        WebElement password = this.driver.findElement(By.id("inputPassword"));
        password.sendKeys("123");

        WebElement submitBtn = driver.findElement(By.tagName("button"));
        submitBtn.click();
        Assertions.assertEquals("Invalid username or password",this.driver.findElement(By.id("msgError")).getText());
    }

}
