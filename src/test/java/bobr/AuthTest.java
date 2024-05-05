package bobr;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static bobr.Config.BASE_URI;
import static bobr.DriverManager.getDrivers;
import static org.junit.Assert.assertNotEquals;

public class AuthTest {

    final String path = "/search/en/";
    private final List<WebDriver> drivers = getDrivers();

    @AfterEach
    public void closeDrivers() {
        drivers.forEach(WebDriver::close);
    }

    @Test
    public void loginTest() {
        for (WebDriver driver : drivers) {
            driver.get(BASE_URI + path);

            WebElement loginElem = findNavBar(driver)
                    .findElement(By.xpath("//b[contains(text(), 'Log in')]"));

            loginElem.click();
            assertNotEquals(BASE_URI + path, driver.getCurrentUrl());
        }
    }

    @Test
    public void registerTest() {
        for (WebDriver driver : drivers) {
            driver.get(BASE_URI + path);

            WebElement registerElem = findNavBar(driver)
                    .findElement(By.xpath("//b[contains(text(), 'Log in')]"));

            registerElem.click();
            assertNotEquals(BASE_URI + path, driver.getCurrentUrl());
        }
    }

    private WebElement findNavBar(WebDriver driver) {
        return driver.findElement(By.xpath("//ul[@class='nav navbar-nav pull-right']/li/a"));
    }

}
