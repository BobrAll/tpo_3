package bobr;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static bobr.Config.BASE_URI;
import static bobr.DriverManager.getDrivers;

public class SiteLoadTest {

    @Test
    public void mainPageLoadTest() {
        getDrivers().stream()
                .peek(d -> d.get(BASE_URI))
                .forEach(WebDriver::quit);
    }

}
