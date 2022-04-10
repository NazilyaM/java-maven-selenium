package ru.stqa.Homework4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteCartUsersPageTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUpBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        driver.get("http://localhost/litecart/");
    }

    @Test
    void UsersMainPageTest() {
        int stickersCount = 0;
        //поиск всех уточек на странице
        List<WebElement> ducks = driver.findElements(By.xpath("//ul[@class='listing-wrapper products']//li"));
        System.out.println("Всего уточек на странице: " + ducks.size());

        //поиск стикеров в уточках и их подсчет
        for (int i = 0; i < ducks.size(); i++) {
            String ducksName = ducks.get(i).getText();
            List<WebElement> stickers = ducks.get(i).findElements(By.xpath(".//div[starts-with(@class,'sticker')]"));
            for (int j = 0; j < stickers.size(); j++) {
                String stickersName = stickers.get(j).getText();
                System.out.println("У "+ i+"-й" +" уточки " + "всего " + stickers.size() + " стикер(ов). Стикер " + stickersName);
            }
            Assertions.assertEquals(1, stickers.size());
            stickersCount = stickersCount + stickers.size();
        }
        System.out.println("Всего стикеров на странице: " + stickersCount);
        Assertions.assertEquals(ducks.size(), stickersCount);
    }

    @AfterEach
    void stop() {
        driver.quit();
    }
}