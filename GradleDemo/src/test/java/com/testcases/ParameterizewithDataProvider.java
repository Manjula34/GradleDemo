package com.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterizewithDataProvider {
	WebDriver driver;
	
	@Test(dataProvider="facebooklogin")
	public void LoginToFacebook(String username,String password) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\itsdi\\Downloads\\selenium\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
		//driver.findElement(By.id("u_0_b")).click();
		Assert.assertEquals("https://www.facebook.com/", driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());
		System.out.println("user able to login successfully");
	}
	
	@AfterMethod
	public void CloseApplication() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
	}
	
  @DataProvider(name="facebooklogin")
  public  Object[][] passData(){
	  Object[][] data = new Object[3][2];
	  
	  data[0][0] = "John";
	  data[0][1] = "abs123";
	  
	  data[1][0] = "manju";
	  data[1][1] = "demo12";
	  
	  data[2][0] = "tomy";
	  data[2][1] = "demo1";
	 
	 
	  return data;
	  }
  }
