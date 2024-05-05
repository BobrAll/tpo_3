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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SocialNetworksTest {

    @Test
    public void availableSocialNetworksTest() {
        final List<String> socialNetworks = List.of(
                "https://vimeo.com/acesse",
                "https://www.facebook.com/pages/Acesse-Corporation/475918102475964?fref=ts",
                "http://www.linkedin.com/company/3043458"
        );

        for (WebDriver driver : getDrivers()) {
            driver.get(BASE_URI);

            WebElement socialNetworksList = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@class='soc-media social text-center footer']" +
                                    "/ul[not(@class)]"))
                    );

            List<String> availableSocialNetworks = socialNetworksList.findElements(By.xpath("//li/a"))
                    .stream()
                    .map(e -> e.getAttribute("href"))
                    .toList();

            for (String sn : socialNetworks)
                assertTrue(availableSocialNetworks.contains(sn));

            driver.quit();
        }
    }

}
