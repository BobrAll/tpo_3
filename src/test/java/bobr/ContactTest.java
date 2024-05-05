package bobr;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static bobr.Config.BASE_URI;
import static bobr.Config.TIMEOUT;
import static bobr.DriverManager.getDrivers;
import static org.junit.Assert.assertTrue;

public class ContactTest {

    @Test
    public void contactTest() {
        final String path = "/en/contact";
        final String phone = "PH: +1";
        final String fax = "FX: +1";

        for (WebDriver driver : getDrivers()) {
            driver.get(BASE_URI + path);

            WebElement contactElement = new WebDriverWait(driver, Duration.ofMillis(TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@class='copy']" +
                                    "/div[@class='row']" +
                                    "/div" +
                                    "/p"))
                    );

            String contacts = contactElement.getText();

            assertTrue(contacts.contains(fax));
            assertTrue(contacts.contains(phone));

            driver.quit();
        }
    }

}
