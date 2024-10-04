package pagesPackage;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import basePackage.Base;
import utilesPackage.ExcelUtils;

public class HomePage extends Base{
	
	By companyMenuLocator = By.xpath("//span[text() =\"Company\"]");
	By aboutCognizantLocator = By.xpath("//span[text() =\"About Cognizant\"]");
	
	By nameLocator = By.xpath("//input[@id='form-text-439508679']");
	By emailLocator = By.xpath("//input[@id='form-text-1296102901']");
	By organizationLocator = By.xpath("//input[@id='form-text-1991476075']");
	By contactLocator = By.xpath("//input[@id='form-text-1176853445']");
	By messageLocator = By.xpath("//textarea[@id='form-text-569162528']");
	By submitButtonLocator = By.xpath("//button[@id='form-button-1003679781']//span[@class='cmp-button__text'][normalize-space()='Submit']");
	
	public void navigateToCompany() {
		
		
		logger = report.createTest("Navigating to About Cognizant");
		
		try {
			
			
			waitUntilWebElementIsVisible(10, companyMenuLocator);
			findElement(companyMenuLocator).click();
			
			Thread.sleep(1000);
			
			waitUntilWebElementIsVisible(10, aboutCognizantLocator);
			findElement(aboutCognizantLocator).click();
			
			Set<String> handles=driver.getWindowHandles();
			Iterator<String> it=handles.iterator();
			
			String beCognizantWindow=(String) it.next();
			String aboutCognizantWindow=(String) it.next();
			
			driver.switchTo().window(aboutCognizantWindow);
				
			Thread.sleep(8000);
			
			if (driver.getTitle().contains("Intuition engineered"))
				captureScreenshot("aboutCognizant");
			reportPass("Navigating to About Cognizant was successful");
			
		}catch (Exception e) {
			e.printStackTrace();
			captureScreenShotOnFailure("NavigationFailed");
			reportFail("Navigating to About Cognizant was unsuccessful");
		}
	}
	
	public void fillCognizantForm() throws Exception {
		ExcelUtils excelUtilObject = new ExcelUtils();
		String[] secondArray = excelUtilObject.readExcelData();
		
		logger = report.createTest("Filling Cognizant question form to get answers");
		
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,4670);");
			
			Thread.sleep(1000);
			
			waitUntilWebElementIsVisible(10, nameLocator);
			findElement(nameLocator).sendKeys(secondArray[0]);
			
			waitUntilWebElementIsVisible(10, emailLocator);
			findElement(emailLocator).sendKeys(secondArray[1]);
			
			waitUntilWebElementIsVisible(10, organizationLocator);
			findElement(organizationLocator).sendKeys(secondArray[2]);
			
			waitUntilWebElementIsVisible(10, contactLocator);
			findElement(contactLocator).sendKeys(secondArray[3]);
			
			Thread.sleep(2000);
			WebElement countryLocator = driver.findElement(By.xpath("//select[@id='form-options-2120717168']"));
			WebElement inquiryTypeLocator = driver.findElement(By.xpath("//select[@id='form-options-2137556778']"));
			
			Select select=new Select(countryLocator);
			select.selectByValue("India");
			
			Select select2=new Select(inquiryTypeLocator);
			select2.selectByValue("Alumni");
			
			waitUntilWebElementIsVisible(10, messageLocator);
			findElement(messageLocator).sendKeys(secondArray[4]);
			
			captureScreenshot("FilledForm");
			reportPass("Filling Cognizant question form was successful");
			Thread.sleep(10000);
		}catch (Exception e) {
			e.printStackTrace();
			captureScreenShotOnFailure("FormFillingFailed");
			reportFail("Filling Cognizant question form was unsuccessful");
		}
	}
}