package bobr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class DriverManager {

    public static List<WebDriver> getDrivers() {
        return List.of(new ChromeDriver(), new FirefoxDriver());
    }

}
