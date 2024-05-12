package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.AppConstants;

public class AccountsPage{
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	// 1. Private By Locators
	
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	
	
	//2. public class constructor
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil =new ElementUtil(driver);
	}
	
	
	// 3. Public Page Actions/Method
	
	public String getAccountPageTitle() {
		
		String AccountPagetitle =eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Account page title : " + AccountPagetitle);
		return AccountPagetitle;
	}
	
	public String getAccountPageURL() {
		String AccountPageUrl =eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
		System.out.println("AccountPage url : " + AccountPageUrl);
		return AccountPageUrl;
	}
	
	public boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, 5).isDisplayed();
	}
	
	public boolean isMyAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, 5).isDisplayed();
	}
	
	
	public List<String> getAccountsPageHeadersList() {
		
		List<WebElement> getHeadersEleList= eleUtil.getElements(headers);
		List<String> headersList=new ArrayList<String>();
		for(WebElement e: getHeadersEleList )
		{
			String Headers=e.getText();
			headersList.add(Headers);
		}
		return headersList;

	}
	
	
	public SearchResultsPage doSearch(String searchKey)
	{
		System.out.println("searching for : " +searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	
	
	
	
	
	
	
	
}
