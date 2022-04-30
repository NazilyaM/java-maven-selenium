package ru.stqa.Homework5;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.Homework4.BaseTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminCountriesPageTest extends BaseTest {

    @BeforeEach
    void setupBrowser2() {
        webDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        login();
    }

    @Test
    void CountriesPageTest() {
        List<WebElement> table = webDriver.findElements(By.cssSelector("tbody>tr.row"));
        String countriesLocator = "form>table>tbody>tr.row>td:nth-child(5)";
        String countriesLocatorForClick = "form>table>tbody>tr.row>td:nth-child(5) a";
        String zonesLocator = "form>table>tbody>tr.row>td:nth-child(6)";
        String geozonesLocator = "//tr[@class='header']//following-sibling::tr//td[3]";


        List countriesNames = new ArrayList();
        for (int i = 0; i < table.size(); i++) {
            List<WebElement> countriesInLoop = webDriver.findElements(By.cssSelector(countriesLocator));
            countriesNames.add(countriesInLoop.get(i).getAttribute("textContent"));

            List<WebElement> zonesInLoop = webDriver.findElements(By.cssSelector(zonesLocator));
            String zoneCountString = zonesInLoop.get(i).getAttribute("textContent");
            int zoneCountInt = Integer.parseInt(zoneCountString);

            if (zoneCountInt > 0) {
                String countriesName = countriesInLoop.get(i).getAttribute("textContent");
                countriesInLoop.get(i).findElement(By.cssSelector(countriesLocatorForClick)).click();

                List<WebElement> geoZones = webDriver.findElements(By.xpath(geozonesLocator));
                int geoZonesSize = geoZones.size() - 1;
                List geozonesList = new ArrayList(i);
                for (int j = 0; j < geoZonesSize; j++) {
                    List<WebElement> geoZonesInLoop = webDriver.findElements(By.xpath(geozonesLocator));
                    geozonesList.add(geoZonesInLoop.get(j).getAttribute("textContent"));
                }
                System.out.println("У страны " + countriesName + " кол-во геозон " + geozonesList.size() + ": "+ geozonesList);
                List sortedGeozonesList = new ArrayList(geozonesList);
                sortArray(sortedGeozonesList);
                //Collections.sort(sortedGeozonesList);
                System.out.println("Отсортированный список геозон страны " + countriesName + ": " + sortedGeozonesList);
                Assertions.assertEquals(geozonesList, sortedGeozonesList);
                webDriver.navigate().back();
            }

        }
        System.out.println("На странице всего " + countriesNames.size() + " стран: " + countriesNames);
        List sortedCountriesNames = new ArrayList(countriesNames);
        sortArray(countriesNames);
        //Collections.sort(sortedCountriesNames);
        System.out.println("Отсортированный список всех стран со страницы: " + sortedCountriesNames);
        Assertions.assertEquals(countriesNames, sortedCountriesNames);
        webDriver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

}

