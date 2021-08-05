package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {

    @LocalServerPort
    private String port;

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
        this.webDriverWait = new WebDriverWait(driver,1500);
    }

    @AfterEach
    public void afterEach(){
        if (this.driver !=null){
            driver.quit();
        }
    }


    @Test
    public void  credentialTesting()  {
        signup();
        login();
        createCredentialAndUpdate();
        deleteCredential();
    }

    void  deleteCredential(){
        WebElement deleteBtn = this.driver.findElement(
                By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/a"));
        this.webDriverWait.until(ExpectedConditions.visibilityOf(deleteBtn));
        deleteBtn.click();
        Alert alert =  this.webDriverWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        backToHomeFromResultPage();

    }
    private void backToHomeFromResultPage()   {

        Assertions.assertEquals("Result", driver.getTitle());

        WebElement toHomePageBtn = this.driver.findElement(By.id("to-home-page"));

        toHomePageBtn.click();

        this.webDriverWait.until(ExpectedConditions.titleContains("Home"));

        Assertions.assertEquals("Home", driver.getTitle());

        WebElement notesTab = this.driver.findElement(By.id("nav-credentials-tab"));

        notesTab.click();
    }
    public void createCredentialAndUpdate() {
        this.driver.get(hostName+this.port+"/home");
        WebElement credentialTab= this.driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        WebElement  credentialCreationBtn = driver.findElement(By.id("popup-credential"));
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialCreationBtn));

        Assertions.assertNotNull(credentialCreationBtn);

        credentialCreationBtn.click();

        credentialForm("https://www.google.com.sa","google","2021");
        backToHomeFromResultPage();
        updateCredential();
    }

    public void credentialForm(String  url,String  username,String password){
        WebElement credentialUrlField = this.driver.findElement(By.id("credential-url"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(credentialUrlField));

        credentialUrlField.sendKeys(url);

        WebElement noteUsernameField = this.driver.findElement(By.id("credential-username"));

        noteUsernameField.sendKeys(username);

        WebElement credentialPasswordField = this.driver.findElement(By.id("credential-password"));

        credentialPasswordField.sendKeys(password);
        WebElement noteForm = this.driver.findElement(By.id("credential-form"));
        noteForm.submit();
    }

    public void updateCredential(){
        WebElement editBtn = this.driver.findElement(
                By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button"));
        this.webDriverWait.until(ExpectedConditions.visibilityOf(editBtn));
        editBtn.click();
        credentialForm("https://www.goo.com.sa","goo","2021");

        backToHomeFromResultPage();


    }
    public void signup(){
        this.driver.get(hostName+this.port+"/signup");
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
    public void login(){
        this.driver.get(hostName+this.port+"/login");

        WebElement username = this.driver.findElement(By.id("inputUsername"));
        username.sendKeys("uTest");

        WebElement password = this.driver.findElement(By.id("inputPassword"));
        password.sendKeys("test@123");

        WebElement submitBtn = driver.findElement(By.tagName("button"));
        submitBtn.click();
    }
}
