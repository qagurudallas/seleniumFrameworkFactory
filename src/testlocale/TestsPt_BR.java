package testlocale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverfactory.DriverFactoryManager;
import driverfactory.DriverManager;
import driverfactory.DriverType;
import pageobjectmodel.LoginPage;

public class TestsPt_BR {
	DriverManager driverManager;
	WebDriver driver;
	String locale = "?locale=pt_BR";
	LoginPage loginObj;

	@BeforeTest
	@Parameters({ "URL" })
	public void setup(String url) {

		// driverManager = DriverFactoryManager.getDriverManager(DriverType.SAFARI);
		// System.setProperty("webdriver.safari.noinstall", "true");
		driverManager = DriverFactoryManager.getDriverManager(DriverType.CHROME);
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = driverManager.getWebDriver();
		loginObj = new LoginPage(driver);
		driver.manage().window().maximize();
		driver.get(url + locale);

	}

	@Parameters({ "eliteusername", "elitelastname" })
	@Test(priority = 1)
	public void testloginGreetingsVanity(String username, String lastname) {
		loginObj.login(username, lastname, "testing");
		String greetings = driver.findElement(By.className("user-greeting")).getText();
		Assert.assertTrue(greetings.contains("Olá"));
		System.out.println(Thread.currentThread().getId());

	}

	@Test(priority = 2)
	public void testYourAccountPt_BR() {
		driver.findElement(By.cssSelector("[data-behavior='dropdown-trigger']")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("myAccountLink")).sendKeys(Keys.ENTER);
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Sua conta"));
		System.out.println(Thread.currentThread().getId());

	}

	
	@Test(priority=3)
	public void testPartnerLink() {
		driver.findElement(By.id("member-info-menu-advantage")).sendKeys(Keys.ENTER);
		String partnerLink = driver.findElement(By.id("menu-contas_de_parceiros")).getText();
		Assert.assertTrue(partnerLink.contains("Contas de parceiros"));
		
	}

	@Test(priority = 4)
	public void HyattPartnerPageTitle() {
		driver.findElement(By.id("member-info-menu-advantage")).sendKeys(Keys.ENTER);
		WebElement partnerLink = driver.findElement(By.id("menu-contas_de_parceiros"));
		partnerLink.sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("American Airlines – Contas de parceiros – WOH"));

	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
