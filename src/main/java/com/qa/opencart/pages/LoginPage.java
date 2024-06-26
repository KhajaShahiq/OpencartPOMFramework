package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.AppConstants;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	// 1. Private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPWdLink = By.linkText("Forgotten Password");
	
	
	private By registerLink = By.linkText("Register");

	

	// 2. Public Page Class Const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =new ElementUtil(driver);
	}
	
	// 3. Public Page Actions/Method
	

	
		public String getLoginPageTitle() {
			
			String title =eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
			System.out.println("login page title : " + title);
			return title;
		}
		
		public String getLoginPageURL() {
			String url =eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
			
;			System.out.println("login page url : " + url);
			return url;
		}

		public boolean isForgotPwdLinkExist() {
			return eleUtil.isElementDisplayed(forgotPWdLink);
		}
		
		
		public AccountsPage doLogin(String username, String pwd)
		{
			
			
			System.out.println("User details" +username + ":"  +pwd);
			eleUtil.waitForElementVisible(emailId, 5).sendKeys(username);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginButton);
			return new AccountsPage(driver);
		
		}
		
		
		public RegistrationPage navigateToRegistePage() {
			
			eleUtil.waitForElementVisible(registerLink, 10).click();;
			return new RegistrationPage(driver);
		}
		
		
		
		
		
}
