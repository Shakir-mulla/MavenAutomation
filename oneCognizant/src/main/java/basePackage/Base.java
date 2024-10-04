package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilesPackage.ExtentReportManager;

public class Base {
	
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Properties prop;
	public static WebDriverWait wait;
	public static String screenhotsFilePath = System.getProperty("user.dir") + "\\screenshots\\";
	public static String screenhotsOnFailureFilePath = System.getProperty("user.dir") + "\\screenshotsfailure\\";
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	
	// Invoking the Browser
	public void invokeBrowser() {

		prop = new Properties();
		try {

			prop.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\config\\config.properties"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		String browserName = prop.getProperty("browser");

		try {

			// Invoking the Chrome Browser if the opted browser is Chrome
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}

			// Invoking the Firefox browser if the opted browser is Firefox
			if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

			// Invoking the Edge browser if the opted browser is Edge
			if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

		} catch (Exception e) {
			System.out.println("Opted browser must be Chrome, Firefox Or Edge only");
			System.exit(0);
		}
		
		// To maximize the browser window
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	// Opening the Website URL
		public void launchURL(String URLKey) {
			driver.get(prop.getProperty(URLKey));
	}
		
		// Finding the WebElement
		public WebElement findElement(By by) throws Exception {
			WebElement element = driver.findElement(by);
			return element;
		}

		// Finding the WebElement
		public List<WebElement> findElements(By by) throws Exception {
			List<WebElement> elements = driver.findElements(by);
			return elements;
		}

		// Explicit wait for visibility of WebElements
		public void waitUntilWebElementIsVisible(int sec, By locator) {
			wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		// Capturing screenshot of passed test cases
		public void captureScreenshot(String fileName) throws IOException {
			TakesScreenshot capture = (TakesScreenshot) driver;
			File srcFile = capture.getScreenshotAs(OutputType.FILE);
			File destFile = new File(screenhotsFilePath + fileName + ".png");
			Files.copy(srcFile, destFile);
		}

		// Capturing screenshot of failed test cases
		public void captureScreenShotOnFailure(String fileName) {
			TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
			File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
			File dest = new File(screenhotsOnFailureFilePath + fileName + ".png");

			try {
				FileUtils.copyFile(src, dest);
				logger.addScreenCaptureFromPath(screenhotsOnFailureFilePath + fileName + ".png");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Logging passed test Cases in the report
		public void reportPass(String report) {
			logger.log(Status.PASS, report);
		}

		// Logging failed test Cases in the report
		public void reportFail(String report) {
			logger.log(Status.FAIL, report);
		}

		// To send all the input data to the report
		public void endReport() {
			report.flush();
		}

		// Closing the browser
		public void closeBrowser() {
			driver.quit();
		}

}
