package pagesPackage;

import org.openqa.selenium.By;

import basePackage.Base;

public class LoginPage extends Base{
	By emailTextBoxLocator = By.name("loginfmt");
	By nextButtonLocator = By.xpath("//input[@value='Next']");
	By passwordTextboxLocator = By.name("passwd");
	By signInButtonLocator = By.xpath("//input[@value='Sign in']");
	By yesButtonLocator = By.xpath("//input[@value='Yes']");
	
	public void userLogin() {
		
		logger = report.createTest("Logging into Be.Cognizant");

		try {
			waitUntilWebElementIsVisible(10, emailTextBoxLocator);
			findElement(emailTextBoxLocator).sendKeys(prop.getProperty("CognizantEmailID"));

			waitUntilWebElementIsVisible(10, nextButtonLocator);
			findElement(nextButtonLocator).click();

			waitUntilWebElementIsVisible(10, passwordTextboxLocator);
			findElement(passwordTextboxLocator).sendKeys(prop.getProperty("CognizantEmailIDPassword"));
			findElement(signInButtonLocator).click();
			
			waitUntilWebElementIsVisible(30, yesButtonLocator);
			findElement(yesButtonLocator).click();
			
			Thread.sleep(10000);
			
			if (driver.getTitle().equals("Be.Cognizant - Home"))
				captureScreenshot("UserLoggedIn");
			reportPass("User Login to Be.Cognizant was successful");
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenShotOnFailure("UserLoggedInFailed");
			reportFail("User Login was unsuccessful");
		}
	}

}
