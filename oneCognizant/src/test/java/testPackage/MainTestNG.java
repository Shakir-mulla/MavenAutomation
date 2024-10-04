package testPackage;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import basePackage.Base;
import pagesPackage.HomePage;
import pagesPackage.LoginPage;

public class MainTestNG extends Base{
	
	HomePage homePageObject = new HomePage();
	LoginPage loginPageObject = new LoginPage();
	
	@BeforeSuite
	public void launchBrowser() {
		logger = report.createTest("Be.Cognizant Test Cases");
		logger.log(Status.INFO, "Summary Of Test Cases");
		logger.log(Status.INFO, "Test Case 01: Launch browser");
		invokeBrowser();
		reportPass("Browser was invoked successfully");
	}
	
	@BeforeTest
	public void launchURL() {
		logger.log(Status.INFO, "Summary Of Test Cases");
		logger.log(Status.INFO, "Test Case 02: Launch URL - Be.cognizant");
		launchURL("URL");
		reportPass("URL was launched successfully");
	}
	
	@Test(priority = 1)
	public void getTitle() throws Exception {
		loginPageObject.userLogin();
		logger.log(Status.INFO, "Summary Of Test Cases");
		logger.log(Status.INFO, "Test Case 03: Get the title of page");
		reportPass("Page title was retrieved successfully");
	}
	
	@Test(priority = 2)
	public void navigateToAboutCognizant() {
		logger.log(Status.INFO, "Summary Of Test Cases");
		logger.log(Status.INFO, "Test Case 04: Navigate to About Cognizant");
		homePageObject.navigateToCompany();
		reportPass("Navigated successfully");
	}
	
	@Test(priority = 3)
	public void fillForm() throws Exception {
		logger.log(Status.INFO, "Summary Of Test Cases");
		logger.log(Status.INFO, "Test Case 05: Fill question form in About Cognizant page");
		homePageObject.fillCognizantForm();
		reportPass("Form filled successfully");
		
	}
	
	@AfterTest
	public void closeReport() {
		endReport();
	}

	@AfterSuite
	public void quitBrowser() throws InterruptedException {
		Thread.sleep(8000);
		closeBrowser();
	}

	
}