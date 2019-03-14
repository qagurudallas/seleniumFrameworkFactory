package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public abstract class DriverManager {
	protected WebDriver driver;
	protected EventFiringWebDriver edriver;

	protected abstract void createWebDriver();

	public void quitWebDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}

	public WebDriver getWebDriver() {
		if (null == driver) {
			createWebDriver();

		}
		return driver;

	}

}
