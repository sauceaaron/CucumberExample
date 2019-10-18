package com.saucedemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	String SELENIUM_PLATFORM = "Windows 10";
	String SELENIUM_BROWSER = "Chrome";
	String SELENIUM_VERSION = "latest";

	RemoteWebDriver driver;

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL("https://ondemand.saucelabs.com/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("username", SAUCE_USERNAME);
		capabilities.setCapability("accessKey", SAUCE_ACCESS_KEY);
		capabilities.setCapability("platform", SELENIUM_PLATFORM);
		capabilities.setCapability("browserName", SELENIUM_BROWSER);
		capabilities.setCapability("version", SELENIUM_VERSION);

		driver = new RemoteWebDriver(url, capabilities);
	}

	@Test
	public void openSite()
	{
		Site sauceDemo = new Site(driver);

		driver.get(sauceDemo.url);
		String title = driver.getTitle();

		System.out.println(title);
		assertThat(title).isEqualTo("Swag Labs");
	}

	@After
	public void cleanup()
	{
		driver.quit();
	}
}
