package utilesPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {
	public static ExtentReports report;
	
	// Creation of Extent Reports
		public static ExtentReports getReportInstance() {

			if (report == null) {

				String reportName = "Test_Summary_Report_"+ DateUtils.getTimeStamp()+".html";
				ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\test-output\\" + reportName);
				report = new ExtentReports();
				report.attachReporter(htmlReporter);

				report.setSystemInfo("OS", "Windows 10");
				report.setSystemInfo("Environment", "Test");
				report.setSystemInfo("Build Number", "1.0");
				report.setSystemInfo("Browser", "Chrome");

				htmlReporter.config().setDocumentTitle("UI Automation Results: Be.Cognizant");
				htmlReporter.config().setReportName("Be.cognizant and Filling question form in About Cognizant");
				htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
				htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			}
			return report;
		}
}
