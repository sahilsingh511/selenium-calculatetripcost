package com.selenium.tripcost.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance() {
		
		if(htmlReporter == null && report == null) {
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/testreport.html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 11");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReporter.config().setDocumentTitle("UAT UI Automation Results");
			htmlReporter.config().setReportName("All Headlines UI Test Results");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			
		}
		
		return report;
	}
}
