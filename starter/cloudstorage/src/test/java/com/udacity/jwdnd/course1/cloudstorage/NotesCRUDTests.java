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
public class NotesCRUDTests {


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
    public void  noteTesting()  {
        signup();
        login();
        createNoteAndUpdate();
        deleteNote();
    }

    void  deleteNote(){
        WebElement deleteBtn = this.driver.findElement(
                By.xpath("//*[@id='userTable']/tbody/tr/td[1]/a"));
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

        WebElement notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        notesTab.click();
    }
    public void createNoteAndUpdate() {
        this.driver.get(hostName+this.port+"/home");
        WebElement noteTab= this.driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("popup-notes")));

        WebElement noteCreationBtn = driver.findElement(By.id("popup-notes"));

        Assertions.assertNotNull(noteCreationBtn);

        noteCreationBtn.click();

        noteForm("test note title","test note description");
        backToHomeFromResultPage();
        updateNote();
    }

    public void noteForm(String  title,String  description){
        WebElement noteTitleField = this.driver.findElement(By.id("note-title"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(noteTitleField));

        noteTitleField.sendKeys(title);

        WebElement noteDescriptionField = this.driver.findElement(By.id("note-description"));

        noteDescriptionField.sendKeys(description);

        WebElement noteForm = this.driver.findElement(By.id("note-form"));
        noteForm.submit();
    }

    public void updateNote(){
        WebElement editBtn = this.driver.findElement(
                By.xpath("//*[@id='userTable']/tbody/tr/td[1]/button"));
        this.webDriverWait.until(ExpectedConditions.visibilityOf(editBtn));
        editBtn.click();
        noteForm("test note title update","test note description update");

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
