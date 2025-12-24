package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

public class GoogleSearchTest {
    
    WebDriver driver;
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testGoogleSearch() {
        // Navigate to Google
        driver.get("https://www.google.com");
        
        // Verify page title contains "Google"
        Assert.assertTrue(driver.getTitle().contains("Google"), "Page title should contain 'Google'");
        
        // Find and interact with search box
        org.openqa.selenium.WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();
        
        // Wait and verify search results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"), 
                "Search results page should contain search term");
        
        System.out.println("Test passed successfully!");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
