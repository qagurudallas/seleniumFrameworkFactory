package seleframework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Tests {
	DriverManager driverManager;
	WebDriver driver;

	@Test
	public void navigateToHomePage() {
		driverManager = DriverFactoryManager.getDriverManager(DriverType.SAFARI);
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = driverManager.getWebDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
	}

	@Test
	public void login() {
		LoginPage login = new LoginPage(driver);
		
		login.login("asif", "testing");
		
	}
	
}
