package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	//xpath of search buttong on iprice home page
	public By search_Element = By.xpath("//*[@id=\"term-desktop\"]");
	
	//function to select the brand name available on page
	public boolean selectLaptopBrand(String brand,WebDriver driver)
	{
		for(int i=1;i<=12;i++)
		{
			WebElement ele = driver.findElement(By.xpath("//*[@id=\"body\"]/div/div[1]/div[4]/amp-carousel/div[1]/a["+i+"]"));
			String actual = ele.getText();
		    if(actual.equalsIgnoreCase(brand))
		    {
		    	ele.click();
		    	return true;
		    }
		}
		
		return false;
	}
	
	//function to verify if list of product getting is same as searched
	public boolean verifyResultsBrand(String brand,WebDriver driver)
	{
		List<WebElement> searchList =driver.findElements(By.xpath("//*[@id=\"product-list\"]/*"));
		
		for(WebElement temp:searchList)
		{
			String actual = temp.getText();
			System.out.println(actual);
		    if(!(actual.contains(brand) || actual.contains(brand.toLowerCase()) || actual.contains(brand.toUpperCase())))
		    {
		    	return false;
		    }
		}
		
		return true;
	}
	
	
	//function to verify if desc order price selection functionality is working fine or not
	public boolean verifyPriceSorting(WebDriver driver)
	{
		List<WebElement> searchList =driver.findElements(By.xpath("//*[@id=\"product-list\"]/*"));
		int product =1;
		double prevprice =9999999999999999999999.99;
		double productPrice=0;
		for(WebElement temp:searchList)
		{
			productPrice = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"pc"+product+"\"]/a/div/div[1]/div/div[3]/div")).getText().trim().split(" ")[1].replaceAll(",", ""));
	        System.out.println(productPrice);
		    if(!(productPrice<=prevprice))
		    {
		    	return false;
		    }
		    prevprice=productPrice;
		    product++;
		}
		return true;
	}
	
}
