package seleframework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	
	private final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private By UsernameTextBox = By.id("loginForm.loginId");
	private By LastNameTextBox = By.id("loginForm.lastName");
	private By PasswordTextBox = By.id("loginForm.password");
	private By LoginButton = By.name("_button_go");
	
	
	public void login(String username, String lastname, String password) {
		this.enterUsername(username);
		this.enterLastname(lastname);
		this.enterPassword(password);
		this.clickLogin();
		// return new LoginPage(driver);
	}

	
	public void enterUsername(String username) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(UsernameTextBox).sendKeys(username);
	}

	public void enterLastname(String lastname) {
		driver.findElement(LastNameTextBox).sendKeys(lastname);
	}

	public void enterPassword(String password) {
		driver.findElement(PasswordTextBox).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(LoginButton).click();
	}
	
	
	
	public WebElement getWhenVisible(By locator, int timeout) {
		
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		element = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return element;
		
		
	}
	
}
