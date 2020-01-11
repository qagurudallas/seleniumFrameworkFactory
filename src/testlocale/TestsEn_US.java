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
import org.testng.asserts.SoftAssert;

import driverfactory.DriverFactoryManager;
import driverfactory.DriverManager;
import driverfactory.DriverType;
import pageobjectmodel.LoginPage;

public class TestsEn_US {
	DriverManager driverManager;
	WebDriver driver;
	String locale = "?locale=en_US";
	LoginPage loginObj;
	SoftAssert sassert;
	int x;

	@BeforeTest
	@Parameters({ "URL" })
	public void setup(String url) {

		
		driverManager = DriverFactoryManager.getDriverManager(DriverType.CHROME);
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = driverManager.getWebDriver();
		loginObj = new LoginPage(driver);
		sassert = new SoftAssert();
		driver.manage().window().maximize();
		driver.get(url + locale);

	}

	@Parameters({ "eliteusername", "elitelastname" })
	@Test(priority = 1)
	public void testloginGreetingsVanity(String username, String lastname) {
		loginObj.login(username, lastname, "testing");
		String greetings = driver.findElement(By.className("user-greeting")).getText();
		Assert.assertTrue(greetings.contains("Hello"));
		System.out.println(Thread.currentThread().getId());
	}

	@Test(priority = 2)
	public void testYourAccountEN_US() {
		driver.findElement(By.cssSelector("[data-behavior='dropdown-trigger']")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("myAccountLink")).sendKeys(Keys.ENTER);
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Account summary"));
		System.out.println(Thread.currentThread().getId());
	}

	@Test(enabled = false)
	public void testYourAccountPageTitle() {

		Select country = new Select(driver.findElement(By.id("aa-country-selector")));
		country.selectByVisibleText("United States");
		Select lang = new Select(driver.findElement(By.id("aa-language-selector")));
		lang.selectByVisibleText("Español");
		driver.findElement(By.id("aa-choose-locale")).sendKeys(Keys.ENTER);

	}

	@Test(priority = 3)
	public void testPartnerLink() {
		driver.findElement(By.id("member-info-menu-advantage")).sendKeys(Keys.ENTER);
		String partnerLink = driver.findElement(By.id("menu-partner_accounts")).getText();
		Assert.assertTrue(partnerLink.contains("Partner accounts"));

	}

	@Test(priority = 4)
	public void HyattPartnerPageTitle() {
		driver.findElement(By.id("member-info-menu-advantage")).sendKeys(Keys.ENTER);
		WebElement partnerLink = driver.findElement(By.id("menu-partner_accounts"));
		partnerLink.sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("American Airlines - Partner Accounts - WOH"));

	}
	
	@Test(priority=5)
	public void testEnterWOHdetails() {
		
		String greetings = driver.findElement(By.className("card__title")).getText();
		WebElement account = driver.findElement(By.id("hyatt_account_number"));
		String linkaccountBtn = driver.findElement(By.cssSelector("[data-test=\"link-acc\")]")).getText();
		System.out.println(linkaccountBtn);
		sassert.assertTrue(greetings.contains("Enter your World of Hyatt number"));
		sassert.assertTrue(account.isEnabled());
		sassert.assertTrue(linkaccountBtn.contains("Link your account"));
		sassert.assertAll();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
