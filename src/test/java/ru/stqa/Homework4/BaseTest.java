package ru.stqa.Homework4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public EventFiringWebDriver webDriver;
    public WebDriverWait webDriverWait;

    public boolean isElementPresent(By locator) {
        //при явном ожидании вызываем операцию поиска до тех пор, пока она не завершится успешно (будет ждать 5 сек - это время указано при создании об-та webDriverWait)
        try {
            webDriverWait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (TimeoutException ex) {  //функция until выбрасывает уже другое исключение - TimeoutException
            return false;
        }
/*   //при неявном ожидании вызываем операцию поиска - выбрасывает NoSuchElementException
        try {
            webDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            //} catch (NoSuchElementException ex) {
            return false;
        }
 */
    }

    public boolean areElementsPresent(By locator) {
        return webDriver.findElements(locator).size() > 0;
    }

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        webDriver = new EventFiringWebDriver(new ChromeDriver());
        webDriverWait = new WebDriverWait(webDriver, 10);
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    public void login() {
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();
    }

    public void sortArray(List arrayForSort) {
        Collections.sort(new ArrayList());
    }
}