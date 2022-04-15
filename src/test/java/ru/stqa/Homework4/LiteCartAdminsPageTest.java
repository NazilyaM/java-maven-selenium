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


    //Тот же самый тест, только Xpath-локаторы. Подскажите, пожалуйста, почему не работает?
    @Test
    void AdminsPageTest2() {

        List<WebElement> mainMenuElements = webDriver.findElements(By.xpath("//ul[@id='box-apps-menu']//li"));
        int size1 = mainMenuElements.size();

        for (int i = 0; i < size1; i++) {
            List<WebElement> mainMenuElementsLoop = webDriver.findElements(By.xpath("//ul[@id='box-apps-menu']//li"));
            mainMenuElementsLoop.get(i).click();
            List<WebElement> childElements = webDriver.findElements(By.xpath("//ul[@id='box-apps-menu']//li//child::li"));
            int size2 = childElements.size();

            for (int j = 0; j < size2; j++) {
                if (j > 0) {
                    List<WebElement> childElementsLoop = webDriver.findElements(By.xpath("//ul[@id='box-apps-menu']//li//child::li"));
                    childElementsLoop.get(j).click();
                }
            }
        }
        webDriver.findElement(By.xpath("//a[@title='Logout']")).click();
    }
}

/*

Посмотреть резюме

1) Для выполнения повторяющихся действий человечество придумало циклы.

2) Явное перечисление пунктов меню вступает в противоречие с условием задания.
потому что от нас не требуется проверять, что в меню есть определённые пункты, то есть:

а) если какие-то пункты пропадут -- это нормально с точки зрения этого теста, и он не должен падать

б) если какие-то пункты добавятся -- тест должен их проверять, но при явном перечислении он
будет их пропускать, то есть перестанет выполнять поставленную перед ним задачу

3) Проверять текст заголовка не обязательно, достаточно проверить наличие заголовка.
 */


/*


        driver.findElement(By.xpath("//span[.='Appearence']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Template | My Store"));
        Assertions.assertEquals("Template", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Template']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
        driver.findElement(By.xpath("//a[.='Logotype']")).click();
        Assertions.assertEquals("Logotype", driver.findElement(By.xpath("//h1")).getText());


        driver.findElement(By.xpath("//span[.='Catalog']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Catalog | My Store"));
        Assertions.assertEquals("Catalog", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Catalog']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
        driver.findElement(By.xpath("//a[.='Product Groups']")).click();
        Assertions.assertEquals("Product Groups", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Option Groups']")).click();
        Assertions.assertEquals("Option Groups", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Manufacturers']")).click();
        Assertions.assertEquals("Manufacturers", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Suppliers']")).click();
        Assertions.assertEquals("Suppliers", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Delivery Statuses']")).click();
        Assertions.assertEquals("Delivery Statuses", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Sold Out Statuses']")).click();
        Assertions.assertEquals("Sold Out Statuses", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Quantity Units']")).click();
        Assertions.assertEquals("Quantity Units", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='CSV Import/Export']")).click();
        Assertions.assertEquals("CSV Import/Export", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Countries']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Countries | My Store"));
        Assertions.assertEquals("Countries", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Currencies']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Currencies | My Store"));
        Assertions.assertEquals("Currencies", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Customers']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Customers | My Store"));
        Assertions.assertEquals("Customers", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Customers']")).click();
        Assertions.assertEquals("Customers", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='CSV Import/Export']")).click();
        Assertions.assertEquals("CSV Import/Export", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Newsletter']")).click();
        Assertions.assertEquals("Newsletter", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Geo Zones']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Geo Zones | My Store"));
        Assertions.assertEquals("Geo Zones", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Languages']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Languages | My Store"));
        Assertions.assertEquals("Languages", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Languages']")).click();
        Assertions.assertEquals("Languages", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Storage Encoding']")).click();
        Assertions.assertEquals("Storage Encoding", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Modules']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Job Modules | My Store"));
        Assertions.assertEquals("Job Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Background Jobs']")).click();
        Assertions.assertEquals("Job Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Customer']")).click();
        Assertions.assertEquals("Customer Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Shipping']")).click();
        Assertions.assertEquals("Shipping Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Payment']")).click();
        Assertions.assertEquals("Payment Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Order Total']")).click();
        Assertions.assertEquals("Order Total Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Order Success']")).click();
        Assertions.assertEquals("Order Success Modules", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Order Action']")).click();
        Assertions.assertEquals("Order Action Modules", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Orders']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Orders | My Store"));
        Assertions.assertEquals("Orders", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Orders']")).click();
        Assertions.assertEquals("Orders", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Order Statuses']")).click();
        Assertions.assertEquals("Order Statuses", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Pages']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Pages | My Store"));
        Assertions.assertEquals("Pages", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Reports']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Monthly Sales | My Store"));
        Assertions.assertEquals("Monthly Sales", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Monthly Sales']")).click();
        Assertions.assertEquals("Monthly Sales", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Most Sold Products']")).click();
        Assertions.assertEquals("Most Sold Products", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Most Shopping Customers']")).click();
        Assertions.assertEquals("Most Shopping Customers", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Settings']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Settings | My Store"));
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Store Info']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Defaults']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='General']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Listings']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Images']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Checkout']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Advanced']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Security']")).click();
        Assertions.assertEquals("Settings", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Slides']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Slides | My Store"));
        Assertions.assertEquals("Slides", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Tax']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Tax Classes | My Store"));
        Assertions.assertEquals("Tax Classes", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Tax Classes']")).click();
        Assertions.assertEquals("Tax Classes", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Tax Rates']")).click();
        Assertions.assertEquals("Tax Rates", driver.findElement(By.xpath("//h1")).getText());


        driver.findElement(By.xpath("//span[.='Translations']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Search Translations | My Store"));
        Assertions.assertEquals("Search Translations", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Search Translations']")).click();
        Assertions.assertEquals("Search Translations", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='Scan Files']")).click();
        Assertions.assertEquals("Scan Files For Translations", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='CSV Import/Export']")).click();
        Assertions.assertEquals("CSV Import/Export", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='Users']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("Users | My Store"));
        Assertions.assertEquals("Users", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//span[.='vQmods']")).click();
        webDriverWait.until(ExpectedConditions.titleIs("vQmods | My Store"));
        Assertions.assertEquals("vQmods", driver.findElement(By.xpath("//h1")).getText());
        driver.findElement(By.xpath("//a[.='vQmods']")).click();
        Assertions.assertEquals("vQmods", driver.findElement(By.xpath("//h1")).getText());

        driver.findElement(By.xpath("//a[@title='Logout']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("login"))));
        Assertions.assertEquals("Login", driver.findElement(By.name("login")).getText());
 */