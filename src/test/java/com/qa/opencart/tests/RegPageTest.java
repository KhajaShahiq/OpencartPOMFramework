package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.CSVUtil;
import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.Utils.StringUtils;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class RegPageTest extends BaseTest{

	
	@BeforeClass
	public void regSetup() {
		
		regPage=loginpage.navigateToRegistePage();
	}
	
	
	@DataProvider
	public Object[][] getUserRegTestData()
	{
		return new Object[][] {
			{"Shajreen","Shaik","7987298632","Password","yes"},
			{"Shazia","Shaik","887298632","Password","yes"},
			{"Sana","Shaik","9987298632","Password","yes"},
			
			
		};
		
	}
	
	@DataProvider
	public Object[][] getUserRegTestDataFromExcel()
	{
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);

	}

	@DataProvider
	public Object[][] getUserRegTestDataFromCSV()
	{
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}

	
	@Test(dataProvider = "getUserRegTestDataFromCSV")
	public void userRegTest(String firstName, String lastName,String telephone, String pwd, String subscribtBtn) 
	{
		
		Assert.assertTrue(regPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, pwd, subscribtBtn));
	}
	
}
