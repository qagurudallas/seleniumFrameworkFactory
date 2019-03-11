package seleframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	By UsernameTextBox =  By.id("username");
	By PasswordTextBox = By.id("Password");
	By LoginButton = By.id("loginBtn");
	
	public void login(String username, String password) {
		
	}
	
	public void EnterUsername(String username) {
		driver.findElement(UsernameTextBox).sendKeys(username);
		
	}
	public void EnterPassword(String password) {
		driver.findElement(PasswordTextBox).sendKeys(password);
		
	}
	public void clickLogin() {
		driver.findElement(LoginButton).click();
	}
}
