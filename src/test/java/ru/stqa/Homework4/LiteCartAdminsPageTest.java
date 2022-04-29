package ru.stqa.Homework4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import java.util.List;

public class LiteCartAdminsPageTest extends BaseTest {

    @BeforeEach
    void setupBrowser2() {
        webDriver.get("http://localhost/litecart/admin/");
        login();
    }

    @Test
    void AdminsPageTest() {

        List<WebElement> mainMenuElements = webDriver.findElements(By.cssSelector("ul[id='box-apps-menu'] > li"));
        int size1 = mainMenuElements.size();

        for (int i = 0; i < size1; i++) {
            List<WebElement> mainMenuInLoop = webDriver.findElements(By.cssSelector("ul[id='box-apps-menu'] > li"));
            mainMenuInLoop.get(i).click();
            Assertions.assertTrue(webDriver.findElement(By.cssSelector("h1")).isDisplayed());
            List<WebElement> childMenuElements = webDriver.findElements(By.cssSelector("ul[id='box-apps-menu'] > li li"));
            int size2 = childMenuElements.size();

            for (int j = 0; j < size2; j++) {
                if (j > 0) {
                    List<WebElement> childMenuInLoop = webDriver.findElements(By.cssSelector("ul[id='box-apps-menu'] > li li"));
                    childMenuInLoop.get(j).click();
                    Assertions.assertTrue(webDriver.findElement(By.cssSelector("h1")).isDisplayed());
                }
            }
        }
        webDriver.findElement(By.xpath("//a[@title='Logout']")).click();
    }
}