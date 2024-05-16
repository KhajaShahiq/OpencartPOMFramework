package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTest extends BaseTest{
	
	
	
	@BeforeClass
	public void searchResultsSetup()
	{
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
				{"macbook", 3},
				{"Imac", 1},
				{"Samsung", 2}
		};
	}
	
	
	
	@Test(dataProvider = "getProductCountData")
	public void searchResultsCountTest(String searchKey, int proudctCount) {
		searchResultsPage=accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), proudctCount);
	}
	
//	@Test
//
//	public void searchResultsTest() {
//	
//		Assert.assertEquals(searchResultsPage.getSearchProductCount(), 2);
//	}
//	

}
