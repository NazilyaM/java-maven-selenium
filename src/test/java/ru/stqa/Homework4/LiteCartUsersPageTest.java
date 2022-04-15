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
        List<WebElement> ducks = webDriver.findElements(By.cssSelector("li[class='product column shadow hover-light']"));
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


/*
Хорошо, но над локаторами нужно ещё немного поработать.

1) Для поиска товаров используется локатор с избыточной информацией:

"//ul[@class='listing-wrapper products']//li"

Достаточно вычленить основной, семантически значимый класс, которым помечаются соответствующие товарам элементы,
и использовать именно его для поиска, а всё несущественное и малозначимое исключить из условия поиска (классы,
которые используются для оформления)

2) Для поиска стикеров используется локатор, проверяющий частичное совпадение названия класса:

".//div[starts-with(@class,'sticker')]"

Однако полагаться на порядок классов довольно рискованно. Если у элемента указан атрибут class="sticker new"
это с точки зрения вёрстки то же самое, что и class="new sticker", а локатор перестанет работать. Кроме того,
верстальщики могут добавить какие-то новые классы, например, class="large sticker new" и это тоже приведёт
к тому, что локатор перестанет работать, хотя ничего критичного на странице не поменялось. Безопаснее использовать
функцию contains, а не starts-with.

3) А ещё лучше вместо XPath использовать CSS, локаторы будут более компактные и простые, потому что поиск здесь
выполняется по классам, а CSS поддерживает краткую нотацию для таких локаторов.
 */

/*
    @Test
    void UsersMainPageTestOld() {
        int stickersCount = 0;
        //поиск всех уточек на странице
        List<WebElement> ducks = driver.findElements(By.xpath("//ul[@class='listing-wrapper products']//li"));
        System.out.println("Всего уточек на странице: " + ducks.size());

        //поиск стикеров в уточках и их подсчет
        for (int i = 0; i < ducks.size(); i++) {
            //String ducksName = ducks.get(i).getAttribute("alt");
            List<WebElement> stickers = ducks.get(i).findElements(By.xpath(".//div[starts-with(@class,'sticker')]"));
            for (int j = 0; j < stickers.size(); j++) {
                String stickersName = stickers.get(j).getText();
                System.out.println("У "+ i+"-й уточки всего " + stickers.size() + " стикер(ов). Стикер " + stickersName);
            }
            Assertions.assertEquals(1, stickers.size());
            stickersCount = stickersCount + stickers.size();
        }
        System.out.println("Всего стикеров на странице: " + stickersCount);
        Assertions.assertEquals(ducks.size(), stickersCount);
    }
 */