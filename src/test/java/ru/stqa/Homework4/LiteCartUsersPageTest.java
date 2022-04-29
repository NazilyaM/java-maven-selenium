package ru.stqa.Homework4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LiteCartUsersPageTest extends BaseTest {

    @BeforeEach
    void setupBrowser2() {
        webDriver.get("http://localhost/litecart/");
    }

    @Test
    void UsersMainPageTest() {
        int stickersCount = 0;
        int n = 0;
        //поиск всех уточек на странице
        List<WebElement> ducks = webDriver.findElements(By.cssSelector("li.product"));
        System.out.println("Всего уточек на странице: " + ducks.size());

        //поиск стикеров в уточках и их подсчет
        for (int i = 0; i < ducks.size(); i++) {
            List<WebElement> stickers = ducks.get(i).findElements(By.cssSelector("div.sticker"));
            n = i + 1;
            for (int j = 0; j < stickers.size(); j++) {
                String stickersName = stickers.get(j).getText();
                System.out.println("У " + n + "-й уточки всего " + stickers.size() + " стикер(ов). Стикер " + stickersName);
            }
            Assertions.assertEquals(1, stickers.size());
            stickersCount = stickersCount + stickers.size();
        }
        System.out.println("Всего стикеров на странице: " + stickersCount);
        Assertions.assertEquals(ducks.size(), stickersCount);
    }
}