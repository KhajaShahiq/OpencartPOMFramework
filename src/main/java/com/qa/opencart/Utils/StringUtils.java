package com.qa.opencart.Utils;

public class StringUtils {

	
	
	public static String getRandomEmailId() {
		String emailId = "decbatch" + System.currentTimeMillis() + "@opencart.com";
		return emailId;
	}

}
