package com.saucedemo.com.saucedemo.steps;

import com.saucedemo.Site;
import com.saucedemo.com.saucedemo.utils.SauceUtils;
import com.saucedemo.pages.LandingPage;
import com.saucelabs.saucerest.SauceREST;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions
{
	RemoteWebDriver driver;
	WebDriverWait wait;
	String sessionId;

	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	String SELENIUM_PLATFORM = "Windows 10";
	String SELENIUM_BROWSER = "Chrome";
	String SELENIUM_VERSION = "latest";

	SauceREST api;
	SauceUtils sauceUtils;

	Site swagLabs;

	@Before
	public void setup(Scenario scenario) throws MalformedURLException
	{
		URL url = new URL("https://ondemand.saucelabs.com/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("username", SAUCE_USERNAME);
		capabilities.setCapability("accessKey", SAUCE_ACCESS_KEY);
		capabilities.setCapability("platform", SELENIUM_PLATFORM);
		capabilities.setCapability("browserName", SELENIUM_BROWSER);
		capabilities.setCapability("version", SELENIUM_VERSION);

		driver = new RemoteWebDriver(url, capabilities);
		wait = new WebDriverWait(driver, 30);
		sessionId = driver.getSessionId().toString();

		api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
		sauceUtils = new SauceUtils(api);

		swagLabs = new Site(driver);
	}

	@After
	public void tearDown(Scenario scenario)
	{
		driver.quit();
		sauceUtils.updateResults(! scenario.isFailed(), sessionId);
	}

	@When("I open the landing page")
	public void i_open_the_landing_page()
	{
		swagLabs.openLandingPage();
	}

	@Then("I see the Swag Labs logo")
	public void i_see_the_Swag_Labs_logo()
	{
		String title = driver.getTitle();

		System.out.println(title);
		assertThat(title).isEqualTo("Swag Labs");
	}

}
