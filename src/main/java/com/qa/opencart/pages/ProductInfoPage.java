package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUti;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUti = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eleUti.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		int imagesCount = eleUti.waitForElementsVisible(productImages, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("Images count ---> " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInformation() {
		productMap = new HashMap<String, String>();
//		productMap = new TreeMap<String, String>();
//		productMap = new LinkedHashMap<String, String>();

		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUti.getElements(productMetaData);
		System.out.println("product meta data count--->" + metaDataList.size());

		for (WebElement e : metaDataList) {
			String meta = e.getText();
			String metaData[] = meta.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

	// $2,000.00 //0
	// Ex Tax: $2,000.00 //1
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUti.getElements(productpriceData);
		System.out.println("product price count--->" + metaPriceList.size());
		String price = metaPriceList.get(0).getText().trim();
		String ExTaxPrice = metaPriceList.get(1).getText().trim();

		productMap.put("actualprice", price);
		productMap.put("actualtaxprice", ExTaxPrice);

	}

}
