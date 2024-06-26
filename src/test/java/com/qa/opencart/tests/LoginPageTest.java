package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic 100: Design open cart login page")
@Story("US 101: Design login page features for open cart application")
@Feature("Feature 201: Adding login features")

public class LoginPageTest extends BaseTest {

	
	
	@Description("Checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		String actTitle=loginpage.getLoginPageTitle();
		//Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
		
	}
	
	
	@Description("Checking login page URL....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginpageURLTest() {
		
		String actURL=loginpage.getLoginPageURL();
		//Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppError.URL_NOT_FOUND));
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}
	
	@Description("Checking forgot pwd link on login page ....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)

	public void forgotPWDLinkExistTest(){
		
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	
	
	@Description("Checking user is able to login....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
		
	}
}
