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

public class LanguagesTest {

    @Test
    public void availableLanguagesTest() {
        final List<String> languages = List.of("简体中文", "繁體中文", "Pусский", "日本語", "한국어");

        for (WebDriver driver : getDrivers()) {
            driver.get(BASE_URI);

            WebElement languagesList = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//ul[@class='nav navbar-nav navbar-right']" +
                                    "/li[@class='dropdown']" +
                                    "/ul[@class='dropdown-menu']"))
                    );

            List<String> availableLanguages = languagesList.findElements(By.xpath("//a[@class='lang']"))
                    .stream()
                    .map(e -> e.getAttribute("text"))
                    .toList();

            assertArrayEquals(languages.toArray(), availableLanguages.toArray());
            driver.quit();
        }
    }

}
