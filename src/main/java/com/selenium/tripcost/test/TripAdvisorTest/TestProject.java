package com.selenium.tripcost.test.TripAdvisorTest;

import org.testng.annotations.AfterClass;
//import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.tripcost.homes.TripCostForRentals;
import com.selenium.tripcost.utils.ExtentReportManager;

public class TestProject extends TripCostForRentals {

	ExtentReports report = ExtentReportManager.getReportInstance();
	
	ExtentTest logger = report.createTest("Test Report For Trip Advisor Website");
	
	@Parameters("browserName")
	@Test
	public void test1(String browserName) throws Exception {

		logger.log(Status.INFO, "Initializing the Browser");
		invokeBrowser(browserName,logger);
		logger.log(Status.INFO, "Navigate to Website URL");
		openURL("websiteURL",logger);
		logger.log(Status.INFO, "Entering Holiday Homes Location");
		holidayHomesLocation(logger);
		logger.log(Status.INFO, "Scrolling Down");
		scrollDown();
		logger.log(Status.INFO, "Selecting Number of Days To Stay");
		daysToStay(logger);
		logger.log(Status.INFO, "Selecting Number of Guests");
		numberOfGuests(logger);
		logger.log(Status.INFO, "Sort Hotels By Rating");
		sortByRating(logger);
		logger.log(Status.INFO, "Filtering Hotels For Elevator/Lift Access");
		filterForLiftAccess(logger);
		logger.log(Status.INFO, "Displaying Hotel Name, Charges/Night & Total Amount For 5 Days");
		displayTotalAmountAndCharges(logger);
		logger.log(Status.INFO, "Selecting the Cruise Ship");
		selectCruises(logger);
	
	}
	
	@Test(dependsOnMethods="test1")
	public void test2() throws Exception {
		logger.log(Status.INFO, "Quitting the Browser");
		logger.log(Status.PASS, "Test Executed Successfully");
		quitBrowser();
		
	}
	
	@AfterClass
	public void endReport() {
		report.flush();
	}
}