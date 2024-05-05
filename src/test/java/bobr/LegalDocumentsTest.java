package bobr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static bobr.Config.BASE_URI;
import static bobr.Config.TIMEOUT;
import static bobr.DriverManager.getDrivers;
import static org.junit.Assert.assertArrayEquals;

public class LegalDocumentsTest {

    private final List<WebDriver> drivers = getDrivers();

    @AfterEach
    public void closeDrivers() {
        drivers.forEach(WebDriver::close);
    }

    @Test
    public void languagesAvailableTest() {
        List<String> documents = List.of("Document1", "Document2", "Document3");

        for (WebDriver driver : drivers) {
            driver.get(BASE_URI);

            WebElement documentsList = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//ul[@class='nav navbar-nav']" +
                                    "/li[@class='dropdown']" +
                                    "/ul[@class='dropdown-menu']"))
                    );

            List<String> availableDocuments = documentsList.findElements(By.xpath("//a[@class='document']"))
                    .stream()
                    .map(WebElement::getText)
                    .toList();

            Assertions.assertArrayEquals(documents.toArray(), availableDocuments.toArray());
            driver.quit();
        }
    }

}
