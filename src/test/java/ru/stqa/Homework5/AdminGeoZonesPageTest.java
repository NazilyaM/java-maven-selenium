package ru.stqa.Homework5;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.Homework4.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class AdminGeoZonesPageTest extends BaseTest {

    @BeforeEach
    void setupBrowser2() {
        webDriver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        login();
    }

    @Test
    void geoZonesPageTest() {
        List<WebElement> table = webDriver.findElements(By.cssSelector("tr.row"));
        String countriesLocator = "tr.row>td:nth-child(3)";
        //String geozonesLocator = ".dataTable>tbody>tr>td:nth-child(3)";
        String geozonesNamesLocator = ".dataTable>tbody>tr>td:nth-child(3)>select>option[selected]";

        for (int i = 0; i < table.size(); i++) {
            List<WebElement> countries = webDriver.findElements(By.cssSelector(countriesLocator));
            String countriesName = countries.get(i).getAttribute("textContent");
            countries.get(i).findElement(By.cssSelector("tr.row>td:nth-child(3) a")).click();

            List<WebElement> geoZones = webDriver.findElements(By.cssSelector(geozonesNamesLocator));
            List geozonesList = new ArrayList(i);
            for (int j = 0; j < geoZones.size(); j++) {
                List<WebElement> geozonesNames = webDriver.findElements(By.cssSelector(geozonesNamesLocator));
                geozonesList.add(geozonesNames.get(j).getAttribute("textContent"));
            }
            System.out.println("У страны " + countriesName + " кол-во геозон " + geozonesList.size() + ": " + geozonesList);
            List sortedGeozonesList = new ArrayList(geozonesList);
            sortArray(sortedGeozonesList);
            System.out.println("Отсортированный список геозон страны " + countriesName + ": " + sortedGeozonesList);
            Assertions.assertEquals(geozonesList, sortedGeozonesList);
            webDriver.navigate().back();
        }
        webDriver.findElement(By.xpath("//a[@title='Logout']")).click();
    }
}

