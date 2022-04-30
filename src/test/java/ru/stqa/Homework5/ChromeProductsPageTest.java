package ru.stqa.Homework5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.Homework4.BaseTest;

public class ChromeProductsPageTest extends BaseTest {
    @BeforeEach
    void setupBrowser2() {
        webDriver.get("http://localhost/litecart/");
    }

    @Test
    void chromeProductsPageTest() {
        WebElement duckInCampaignsBlock = webDriver.findElement(By.cssSelector("#box-campaigns .product"));

        //получить на главной странице название товара
        String ducksNameOnMainPage = webDriver.findElement(By.cssSelector("#box-campaigns .product  a")).getAttribute("title");

        //Найти цены (обычную и скидочную) на главной странице
        WebElement regularPriceOnMainPage = duckInCampaignsBlock.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPriceOnMainPage = duckInCampaignsBlock.findElement(By.cssSelector(".campaign-price"));

        //Получить цены на главной странице
        String getRegularPriceOnMainPage = regularPriceOnMainPage.getAttribute("textContent");
        String getCampaignPriceOnMainPage = campaignPriceOnMainPage.getAttribute("textContent");

        //Проверить, что цвет обычной цены серый на главной странице (#777)
        String elementColorRegularPriceOnMainPage = regularPriceOnMainPage.getCssValue("color");
        Assertions.assertEquals("rgba(119, 119, 119, 1)", elementColorRegularPriceOnMainPage);

        //Проверить, что обычная цена зачеркнута на главной странице
        String textDecorationRegularPriceOnMainPage = regularPriceOnMainPage.getCssValue("text-decoration-line");
        Assertions.assertEquals("line-through", textDecorationRegularPriceOnMainPage);

        //Проверить, что цвет скидочной цены на главной странице  - красный
        String elementColorCampaignPriceeOnMainPage = campaignPriceOnMainPage.getCssValue("color");
        Assertions.assertEquals("rgba(204, 0, 0, 1)", elementColorCampaignPriceeOnMainPage);

        //Проверить, что скидочная цена на главной странице  - полужирная
        String fontWeightCampaignPriceOnMainPage = campaignPriceOnMainPage.getCssValue("font-weight");
        Assertions.assertEquals("700", fontWeightCampaignPriceOnMainPage);

        //Проверить, что скидочная цена(18px) крупнее, чем обычная(0.8em)  на главной странице
        String fontSizeRegularPriceOnMainPage = regularPriceOnMainPage.getCssValue("font-size");
        String fontSizeCampaignPriceOnMainPage = campaignPriceOnMainPage.getCssValue("font-size");
        int resultOnMainPage = fontSizeRegularPriceOnMainPage.compareTo(fontSizeCampaignPriceOnMainPage);
        Assertions.assertTrue(resultOnMainPage < 0);


        //перейти на страницу товара
        duckInCampaignsBlock.click();

        //получить название товара на странице товара
        String ducksNameOnProductsPage = webDriver.findElement(By.cssSelector("h1")).getAttribute("textContent");

        //Найти цены (обычную и скидочную) на странице товара
        WebElement regularPriceOnProductsPage = webDriver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement campaignPriceOnProductsPage = webDriver.findElement(By.cssSelector("#box-product .campaign-price"));

        //Получить цены на странице товара
        String getRegularPriceOnProductsPage = regularPriceOnProductsPage.getAttribute("textContent");
        String getCampaignPriceOnProductsPage = campaignPriceOnProductsPage.getAttribute("textContent");

        //Проверить, что цвет обычной цены на странице товара серый (другой серый - #666666)
        String elementColorRegularPriceOnProductsPage = regularPriceOnProductsPage.getCssValue("color");
        Assertions.assertEquals("rgba(102, 102, 102, 1)", elementColorRegularPriceOnProductsPage);

        //Проверить, что обычная цена а странице товара зачеркнута
        String textDecorationRegularPriceOnProductsPage = regularPriceOnProductsPage.getCssValue("text-decoration-line");
        Assertions.assertEquals(textDecorationRegularPriceOnProductsPage, "line-through");

        //Проверить, что цвет скидочной цены на странице товара - красный
        String elementColorCampaignPriceOnProductsPage = campaignPriceOnProductsPage.getCssValue("color");
        Assertions.assertEquals("rgba(204, 0, 0, 1)", elementColorCampaignPriceOnProductsPage);

        //Проверить, что скидочная цена на странице товара - полужирная
        String fontWeightCampaignPriceOnProductsPage = campaignPriceOnProductsPage.getCssValue("font-weight");
        Assertions.assertEquals("700", fontWeightCampaignPriceOnProductsPage);

        //Проверить, что скидочная цена(22px) крупнее, чем обычная(16px)  на странице товара
        String fontSizeRegularPriceOnProductsPage = regularPriceOnProductsPage.getCssValue("font-size");
        String fontSizeCampaignPriceOnProductsPage = campaignPriceOnProductsPage.getCssValue("font-size");
        int resultOnProductsPage = fontSizeRegularPriceOnProductsPage.compareTo(fontSizeCampaignPriceOnProductsPage);
        Assertions.assertTrue(resultOnProductsPage < 0);


        //Проверить, что на главной странице и на странице товара совпадает текст названия товара
        Assertions.assertEquals(ducksNameOnMainPage, ducksNameOnProductsPage);

        //Проверить, что на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assertions.assertEquals(getRegularPriceOnMainPage, getRegularPriceOnProductsPage);
        Assertions.assertEquals(getCampaignPriceOnMainPage, getCampaignPriceOnProductsPage);

        //Проверить, что цвет обычной цены на главной странице и на странице товара НЕ совпадают
        Assertions.assertNotEquals(elementColorRegularPriceOnMainPage, elementColorRegularPriceOnProductsPage);

        //Проверить, что обычная цена зачеркнута и на главной странице, и на странице товара
        Assertions.assertEquals(textDecorationRegularPriceOnMainPage, textDecorationRegularPriceOnProductsPage);

        //Проверить, что цвет скидочной цены на главной странице и на странице товара совпадают
        Assertions.assertEquals(elementColorCampaignPriceeOnMainPage, elementColorCampaignPriceOnProductsPage);

        //Проверить, что скидочная цена полужирная и на главной странице, и на странице товара
        Assertions.assertEquals(fontWeightCampaignPriceOnMainPage, fontWeightCampaignPriceOnProductsPage);

    }

}