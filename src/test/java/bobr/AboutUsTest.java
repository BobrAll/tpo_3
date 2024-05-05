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
import static org.junit.Assert.assertTrue;

public class AboutUsTest {

    @Test
    public void aboutUsTest() {
        final String path = "/en/about";
        final List<String> headers = List.of(
                "The Acesse Story",
                "Reaching Small Businesses Around the World"
        );

        for (WebDriver driver : getDrivers()) {
            driver.get(BASE_URI + path);

            WebElement informationBlocks = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@class='copy']" +
                                    "//div[@class='row']" +
                                    "/div"))
                    );

            List<String> availableHeaders = informationBlocks.findElements(By.xpath("//h2"))
                    .stream()
                    .map(WebElement::getText)
                    .toList();

            for (String header : headers)
                assertTrue(availableHeaders.contains(header));

            driver.quit();
        }
    }

}
