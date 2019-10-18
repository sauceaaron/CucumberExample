package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Site
{
	public Properties config;
	public String url;

	public WebDriver driver;

	public Site(WebDriver driver)
	{
		config = loadProperties("config.properties");
		url = config.getProperty("url");

		this.driver = driver;
	}

	public void openLandingPage()
	{
		driver.get(url);
	}

	public Properties loadProperties(String propertiesFile)
	{
		Properties properties = new Properties();

		try
		{
			InputStream input = this.getClass().getClassLoader().getResourceAsStream(propertiesFile);
			properties.load(input);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return properties;
	}
}
