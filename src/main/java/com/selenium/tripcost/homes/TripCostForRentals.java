package com.selenium.tripcost.homes;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.selenium.tripcost.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import com.selenium.tripcost.cruise.CruiseDetails;

public class TripCostForRentals {

	public WebDriver driver;
	public HandlingDates dates;
	public CruiseDetails cruise;
	public Properties prop;

	ExtentReports report = ExtentReportManager.getReportInstance();

	public void invokeBrowser(String browserName, ExtentTest logger) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} 
			else if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\msedgedriver.exe");
				driver = new EdgeDriver();
			} 
			else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else {
				driver = new SafariDriver();
			}
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\ObjectRepository\\application.properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage(), logger);
				e.printStackTrace();
			}

		}
	}

	public void openURL(String websiteURLKey, ExtentTest logger) throws InterruptedException {
		try {
			Thread.sleep(2000);
			driver.get(prop.getProperty(websiteURLKey));
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void holidayHomesLocation(ExtentTest logger) throws InterruptedException {
		try {
			WebElement holidayHomeBtn = driver.findElement(By.xpath(prop.getProperty("holidayHomeBtn_xpath")));
			Thread.sleep(1500);
			holidayHomeBtn.click();
			Thread.sleep(1000);
			WebElement location = driver.findElement(By.xpath(prop.getProperty("location_xpath")));
			location.sendKeys("Nairobi");
			Thread.sleep(1500);
			WebElement locationSelect = driver.findElement(By.xpath(prop.getProperty("locationSelect_xpath")));
			locationSelect.click();
			Thread.sleep(1000);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,340)", "");
	}

	public void daysToStay(ExtentTest logger) throws Exception {
		try {
			HandlingDates dates = new HandlingDates();
			dates.calendarHandling(driver);
			Thread.sleep(2000);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void numberOfGuests(ExtentTest logger) throws InterruptedException {
		try {

			driver.findElement(By.xpath(prop.getProperty("noOfGuestsBtn_xpath"))).click();
			Thread.sleep(300);
			WebElement countUp = driver.findElement(By.xpath(prop.getProperty("countUp_xpath"))); // increase guest
																									// count
																									// count
			Actions a = new Actions(driver);
			a.doubleClick(countUp).perform();
			Thread.sleep(1000);
			WebElement applyBtn = driver.findElement(By.xpath(prop.getProperty("applyBtn_xpath")));
			applyBtn.click();
			Thread.sleep(2500);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void sortByRating(ExtentTest logger) throws InterruptedException {
		try {
			WebElement ratingSortBtn = driver.findElement(By.xpath(prop.getProperty("ratingSortBtn_xpath")));
			ratingSortBtn.click();
			Thread.sleep(300);
			driver.findElement(By.xpath(prop.getProperty("sortByRatingOption_xpath"))).click();
			Thread.sleep(4000);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void filterForLiftAccess(ExtentTest logger) throws InterruptedException {
		try {
			// Filter for Elevator availability
			driver.findElement(By.xpath("(//div[@class='Mj']/button)[1]")).click(); // Show more button
			Thread.sleep(300);
			driver.findElement(By.xpath("//*[@id=\"amenities.27_label\"]")).click(); // Check elevator/lift checkbox
			Thread.sleep(300);
			driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[14]/div/div[1]/div[2]/button"))
					.click(); // Close Amenities box
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(3000);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void displayTotalAmountAndCharges(ExtentTest logger) throws InterruptedException {
		try {
			// Displaying Hotel Names
			List<WebElement> names = driver.findElements(By.xpath(prop.getProperty("names_xpath")));
			List<WebElement> chargesPerNight = driver.findElements(By.xpath(prop.getProperty("chargesPerNight_xpath")));
			List<WebElement> totalCost = driver.findElements(By.xpath(prop.getProperty("totalCost_xpath")));

			for (int i = 0; i < 3; i++) {
				System.out.println("Rental Name: " + names.get(i).getText() + " || Charges Per Night: "
						+ chargesPerNight.get(i).getText() + " || Total Amount For 5 Days of Stay: "
						+ totalCost.get(i).getText() + "\n");
			}
			Thread.sleep(3000);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void selectCruises(ExtentTest logger) throws InterruptedException {
		try {
			// Click on Cruises
			driver.findElement(By.xpath("//nav/div/div/div[1]/a[8]")).click();

			cruise = new CruiseDetails();

			cruise.cruiseDetails(driver);
			Thread.sleep(1500);
			cruise.switchToOtherWindow(driver);
			Thread.sleep(3000);
			cruise.languagesList(driver);
			reportPass("Test Executed Succesfully", logger);
		} catch (Exception e) {
			reportFail(e.getMessage(), logger);
		}
	}

	public void quitBrowser() {
		driver.quit();
	}

	/*********************** Reporting Functions *************************/

	public void reportFail(String reportString, ExtentTest logger) {
		logger.log(Status.FAIL, reportString);
		takeScreenshotOnFailure(logger);
		Assert.fail(reportString);
	}

	public void reportPass(String reportString, ExtentTest logger) {
		logger.log(Status.PASS, reportString);
	}

	public void takeScreenshotOnFailure(ExtentTest logger) {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/Screenshots/failed-screen.png");

		try {
			Files.copy(srcFile, destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/failed-screen.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}