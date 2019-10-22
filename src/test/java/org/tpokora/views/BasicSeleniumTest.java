package org.tpokora.views;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tpokora.Application;
import org.tpokora.TestApplication;
import org.tpokora.config.properties.TestProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class })
@ContextConfiguration(classes = TestProperties.class)
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class BasicSeleniumTest {

    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";

    @Autowired
    protected TestProperties testProperties;

    protected WebDriver driver;

    @Before
    public void setup() {
        String pathToChromeDriver = "lib/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        this.driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        this.driver.close();
        this.driver.quit();
    }

    @Test
    public void testLoginToHomePage() {
        loginDefault();
        WebElement homeElement = this.driver.findElement(By.tagName("home-view"));
        homeElement.findElement(By.tagName("h3"));

        Assert.assertEquals(String.format("%s does not exist", WELCOME_TO_EMMY_APP), WELCOME_TO_EMMY_APP, homeElement.getText());
    }

    protected void login(String username, String password) {
        driver.navigate().to(ViewsStrings.LOGIN_URL);
        WebElement loginElement = driver.findElement(By.id("vaadinLoginUsername"));
        loginElement.findElement(By.xpath("input[@name = 'username']"));
        Assert.assertNotNull("Login input does not exist", loginElement);
        WebElement passwordElement = driver.findElement(By.id("vaadinLoginPassword"));
        passwordElement.findElement(By.xpath("input[@name = 'password']"));
        Assert.assertNotNull("Password input does not exist", passwordElement);

        loginElement.sendKeys(username);
        passwordElement.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.tagName("vaadin-button"));
        loginBtn.click();
        Assert.assertNotNull("Login button does not exist", loginBtn);

        Assert.assertEquals(String.format("After login not redirected to %s instead %s",
                ViewsStrings.HOME_URL, getCurrentUrl()),
                ViewsStrings.HOME_URL, getCurrentUrl());
    }

    protected void loginDefault() {
        login(this.testProperties.getUsername(), this.testProperties.getPassword());
    }

    protected String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
}
