package ru.stqa.Homework2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiteCartLoginTest{
    WebDriverWait webDriverWait;
    static WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
    }
    @Test
    void LoginLiteCartTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        webDriverWait.until(ExpectedConditions.titleIs("My Store"));
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("login"))));
    }

    @AfterAll
    static void stop() {
        driver.quit();
    }
}
