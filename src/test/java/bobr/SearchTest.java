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

public class SearchTest {

    private final List<WebDriver> drivers = getDrivers();

    @AfterEach
    public void closeDrivers() {
        drivers.forEach(WebDriver::close);
    }

    @Test
    public void searchTest() {
        final String path = "/search/en";
        final String searchQuery = "something";

        for (WebDriver driver : drivers) {
            driver.get(BASE_URI + path);

            WebElement searchLine = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@type='text']"))
                    );

            searchLine.sendKeys(searchQuery);

            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            String searchUrl = driver.getCurrentUrl();
            Assertions.assertTrue(searchUrl.contains("/results"));
        }
    }

}
