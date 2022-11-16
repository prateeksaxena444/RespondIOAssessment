package com.base;

import org.testng.annotations.Test;

import com.pages.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class UITestRunner {
	WebDriver driver =null;
	String url = "https://iprice.my";
	HomePage home = new HomePage();
  
@BeforeClass
  public void setupDriver() {
	  WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get(url);
		Assert.assertEquals(driver.getTitle(), "Save Money with iPrice – Malaysia’s #1 Price Comparison Site", "IPrice Malaysia website not loading");
  }
  
  @Test(priority=1)
  public void users_are_able_to_filter_for_an_item_by_brand() {
	  driver.navigate().to(url+"/computing/laptops");
	  Assert.assertTrue(home.selectLaptopBrand("Dell", driver));
	  Assert.assertTrue(home.verifyResultsBrand("Dell", driver));
  }
  
  @Test(priority=2)
  public void users_are_able_to_sort_results_under_dresses_by_price()
  {
	  driver.navigate().to(url+"/clothing/dresses/?sort=price.net_desc");
	  Assert.assertTrue(home.verifyPriceSorting(driver));
  }
  
  @Test(priority=3)
  public void users_are_able_to_search_for_an_item()
  {
	  driver.navigate().to(url);
	  driver.findElement(home.search_Element).clear();
		driver.findElement(home.search_Element).sendKeys("iPhone 14");
		driver.findElement(home.search_Element).sendKeys(Keys.ENTER);
	  home.verifyResultsBrand("iPhone 14",driver);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
