package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProducts = By.cssSelector("div.product-layout");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	

	public String getSearchPageTitle(String productName) {
		return eleUtil.waitForTitleContains(productName, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public int getSearchProductsCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("product search count : " + productCount);
		return productCount;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name : " + mainProductName);
		eleUtil.doClick(By.linkText(mainProductName));
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
}
