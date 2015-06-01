package com.todo.selenium.config;

import java.io.File;
import java.io.IOException;



import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

@RunWith(SeleniumScreenshotJUnit4Runner.class)
public class AbstractSelenium {
	private WebDriver driver;

//	@Before
//	public void setUp() {
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("init.accept_languages", "en");
//		driver = new FirefoxDriver(profile);
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//	}
//	
	@Before
	public void setUp() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// Add the WebDriver proxy capability.
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("localhost:8080");
//		capabilities.setCapability("proxy", proxy);
		
		System.setProperty("webdriver.chrome.driver", "c:\\java\\chromedriver.exe ");
		
		// Add ChromeDriver-specific capabilities through ChromeOptions.
		ChromeOptions options = new ChromeOptions();
		options.setBinary(new File("C:\\Users\\klojewsk\\AppData\\Local\\Chromium\\Application\\chrome.exe"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}
	
	public ToDoPage openToDoPage() {
		return PageFactory.initElements(driver, ToDoPage.class);
	}
	
	public void takeScreenshot() throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\tmp\\"+screenshot.getName());
		FileUtils.copyFile(screenshot, destFile);
		System.out.println(String.format("[[ATTACHMENT|%s]]", destFile.getAbsolutePath()));
	}
	
	@After
	public void thearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
