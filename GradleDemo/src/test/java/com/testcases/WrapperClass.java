package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WrapperClass {
	public static WebDriver driver;
	public static FileInputStream fs;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\itsdi\\Downloads\\selenium\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://linkedin.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	public  int getRowCount(String Xlpath,String Sheet) throws IOException {
		
		 fs = new FileInputStream(new File(Xlpath));
			wb = new XSSFWorkbook(fs);
			ws = wb.getSheet(Sheet);
			int count = ws.getLastRowNum();
			System.out.println(count);
			wb.close();
			fs.close();
			return count;
		
	}
	
	public void getDataFromExcel(int count) throws IOException, InterruptedException {
		
		System.out.println(count);		
		for(int i=1;i<=count;i++) 
		{			
			row=ws.getRow(i);
			cell = row.getCell(0);
			String un=cell.getStringCellValue();
			XSSFCell cell1 = row.getCell(1);
			String pwd = cell1.getStringCellValue();
			Setup();
			System.out.println("username " + un + "  "  +"password" + pwd  );
			EnterById("username",un);
			EnterById("password",pwd);
			clickbyXpath("//button[@class='btn__primary--large from__button--floating']");
			TakeScreenShot();
			tearDown();
		}
	}
	
	public void EnterById(String locator,String value) {
		driver.findElement(By.id(locator)).sendKeys(value);
	}
	public void clickbyXpath(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}
	public void TakeScreenShot() throws IOException  {
		TakesScreenshot ts = (TakesScreenshot)driver;
		String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+ "screenshot.png";
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(screenshotpath));
		}
	
	public void tearDown() throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.quit();
		wb.close();
	}
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fs=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fs);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fs.close();
		return cellcount;
	}
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fs=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fs);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fs.close();
		return data;
	}
	
	

}
