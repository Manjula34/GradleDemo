package com.testcases;

import java.io.IOException;


import org.testng.annotations.Test;

public class CallWrapper extends WrapperClass {
	@Test
	public void loginlinkedin() throws IOException, InterruptedException{
		
		int count = getRowCount("C:\\Users\\itsdi\\Downloads\\FBLoginTest.xlsx","Sheet2");
		getDataFromExcel(count);
	}
}
