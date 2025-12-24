package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import static base.BaseClass.getDriver;
import static base.BaseClass.getWait;
import static base.TestListener.getExtentReport;

public class PageClass1 {

	private WebDriver driver;
	private WebDriverWait wait;

	public PageClass1() {
		this.driver = getDriver();
		this.wait = getWait();
		PageFactory.initElements(driver, this);
	}

	// Elements
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameInput;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//p[text()='Forgot your password? ']")
	private WebElement forgotPasswordLink;
	@FindBy(xpath = "//img[contains(@src, 'orangehrm-logo.png')]")
	private WebElement dashboardHeader;

	// ========== Page Actions ==========

	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameInput));
		usernameInput.clear();
		getExtentReport().log(Status.INFO, "Cleared username field");
		usernameInput.sendKeys(username);
		getExtentReport().log(Status.INFO, "Entered username: " + username);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordInput));
		passwordInput.clear();
		getExtentReport().log(Status.INFO, "Cleared password field");
		passwordInput.sendKeys(password);
		getExtentReport().log(Status.INFO, "Entered password: " + password);
	}


	public void clickLoginButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	    loginButton.click();
	    getExtentReport().log(Status.INFO, "Clicked login button");

	    try {
	        wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
	        boolean isVisible = dashboardHeader.isDisplayed();

	        if (isVisible) {
	            getExtentReport().pass("✅ Dashboard header is visible.");
	            Assert.assertTrue(isVisible, "Dashboard header should be visible after login.");
	        } else {
	            getExtentReport().fail("❌ Dashboard header is NOT visible.");
	            Assert.fail("Dashboard header is not visible after login.");
	        }

	    } catch (Exception e) {
	        getExtentReport().fail("❌ Exception occurred while verifying Dashboard: " + e.getMessage());
	        Assert.fail("Exception occurred: " + e.getMessage());
	    }
	}


	public boolean isForgotPasswordLinkDisplayed() {
		boolean displayed = forgotPasswordLink.isDisplayed();
		getExtentReport().log(Status.INFO, "Forgot password link displayed: " + displayed);
		return displayed;
	}

	// Composite action
	public void login(String username, String password) {
		getExtentReport().log(Status.INFO, "Starting login with: " + username);
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		getExtentReport().log(Status.PASS, "Login flow executed");
	}
}
