package driverfactory;

public class DriverFactoryManager {

	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		
		
		
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case SAFARI:
			driverManager = new SafariDriverManager();
			break;
		}

		return driverManager;

	}

}
