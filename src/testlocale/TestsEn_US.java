package seleframework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestsEn_US {
	DriverManager driverManager;
	WebDriver driver;
	String locale = "?locale=en_US";
	LoginPage loginObj;
	
	@BeforeTest
	@Parameters({"URL"})
	public void setup(String url) {
		
	
		//driverManager = DriverFactoryManager.getDriverManager(DriverType.SAFARI);
		//System.setProperty("webdriver.safari.noinstall", "true");
		driverManager = DriverFactoryManager.getDriverManager(DriverType.CHROME);
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		
		driver = driverManager.getWebDriver();
		loginObj = new LoginPage(driver);
		driver.manage().window().maximize();
		driver.get(url+locale);
	
	}

	@Parameters({ "username", "lastname" })
	@Test(priority = 1)
	public void testloginGreetingsVanity(String username, String lastname) {
		loginObj.login(username, lastname, "testing");
		String greetings = driver.findElement(By.className("user-greeting")).getText();
		Assert.assertTrue(greetings.contains("Hello"));
		System.out.println(Thread.currentThread().getId());
	}
	
	@Test(priority=2)
	public void testYourAccountEN_US() {
		driver.findElement(By.cssSelector("[data-behavior='dropdown-trigger']")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("myAccountLink")).sendKeys(Keys.ENTER);
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Account summary"));
		System.out.println(Thread.currentThread().getId());	
	}

	@Test(priority=3, enabled=false)
	public void testYourAccountPageTitle() {
		
		Select country = new Select(driver.findElement(By.id("aa-country-selector")));
		country.selectByVisibleText("United States");
		Select lang = new Select(driver.findElement(By.id("aa-language-selector")));
		lang.selectByVisibleText("Español");
		driver.findElement(By.id("aa-choose-locale")).sendKeys(Keys.ENTER);
		
			
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
