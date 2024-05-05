package bobr;

import org.junit.Test;
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
import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void searchTest() {
        final String path = "/search/en";
        final String searchQuery = "something";

        for (WebDriver driver : getDrivers()) {
            driver.get(BASE_URI + path);

            WebElement searchLine = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@type='text']"))
                    );

            searchLine.sendKeys("something");

            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            String searchUrl = driver.getCurrentUrl();

            assertTrue(searchUrl.contains("q=" + searchQuery));
            driver.quit();
        }
    }

}
