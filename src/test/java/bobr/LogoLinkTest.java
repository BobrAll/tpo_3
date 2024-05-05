package bobr;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static org.junit.Assert.assertEquals;

public class LogoLinkTest {

    List<WebDriver> drivers = getDrivers();

    @AfterEach
    public void closeDrivers() {
        drivers.forEach(WebDriver::close);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/en/about", "/en/contact", "/search/en/"})
    public void logoLinkTest(String path) {
        for (WebDriver driver : drivers) {

            goToMainFromOtherPath(path, driver);
            assertEquals(BASE_URI + "/", driver.getCurrentUrl());
        }
    }

    public void goToMainFromOtherPath(String path, WebDriver driver) {
        driver.get(BASE_URI + path);

        WebElement logoLink = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@class='logo-box']" + "/a"))
                );

        logoLink.click();
    }

}
