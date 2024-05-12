package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap = new HashMap<String, String>();
	
	private By productHeader=By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil =new ElementUtil(driver);
	}

	
	public String getProductHeader() {
		String header=eleUtil.doGetElementText(productHeader);
		return header;
	}
	
	
	public int getProductImagesCount()
	{
		int totalImages=eleUtil.waitForElementsVisible(images, 10).size();
		System.out.println(" Images count is:  "+ getProductHeader() + " :" +totalImages);
		return totalImages;
	}
	
	private void getProductMetaData() {
		
			List<WebElement> productList=eleUtil.getElements(productMetaData);
		
			for(WebElement e: productList)
			{
				String text=e.getText();
				String metaKey =text.split(":")[0].trim();
				String metaValue =text.split(":")[1].trim();
				productMap.put(metaKey, metaValue);
				
			}
		
	}
	
	private void getProductPriceData() {
		System.out.println("getting price data");
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", price);
		productMap.put("extaxprice", exTaxPrice);

	}

	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", getProductHeader());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("product Details: \n" + productMap);
		return productMap;
	}
	
}
	
	
	

