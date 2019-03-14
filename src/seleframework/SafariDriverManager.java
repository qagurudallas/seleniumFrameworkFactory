package seleframework;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManager {

	@Override
	protected void createWebDriver() {
		
		SafariOptions options = new SafariOptions();
		options.setCapability("safari.cleanSession", true);
		this.driver = new SafariDriver(options);
		
		
	}

}
