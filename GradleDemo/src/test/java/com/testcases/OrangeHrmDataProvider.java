package com.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrangeHrmDataProvider extends WrapperClass {
WebDriver driver;
	
	@Test(dataProvider="OrangeHrm")
	public void OrangeHrm(String username,String password) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\itsdi\\Downloads\\selenium\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		Assert.assertEquals("https://opensource-demo.orangehrmlive.com/index.php/dashboard", driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());
		System.out.println("user able to login successfully");
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
	}
	
  @DataProvider(name="OrangeHrm")
  public  Object[][] passData() throws IOException{
	  
	  int rowcount = getRowCount("C:\\Users\\itsdi\\Downloads\\FBLoginTest.xlsx","Sheet3");
	  int CellCnt = getCellCount("C:\\Users\\itsdi\\Downloads\\FBLoginTest.xlsx","Sheet3",1);
	  Object[][] data = new Object[rowcount][CellCnt];
	  for(int i=1;i<=rowcount;i++) 
		{	
		  row=ws.getRow(i);
		  for(int j=0;j<CellCnt;j++) {
			  data[i-1][j] =getCellData("C:\\Users\\itsdi\\Downloads\\FBLoginTest.xlsx", "Sheet3", i, j);
			  
		 }
			
		}
 
	 
	  return data;
	  }
  

}
