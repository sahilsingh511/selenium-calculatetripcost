package com.selenium.tripcost.homes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HandlingDates {
	
	public String checkInDate() {
	
		LocalDate today = LocalDate.now();
	    LocalDate tomorrow = today.plusDays(1);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy");
	    String formatted = tomorrow.format(dtf);
	    // System.out.println(formatted);
	    return formatted;
    }
	
	public String checkOutDate() {
		
		LocalDate today = LocalDate.now();
	    LocalDate tomorrow = today.plusDays(1);
	    LocalDate checkOutDay = tomorrow.plusDays(5);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy");
	    String formatted = checkOutDay.format(dtf);
	    // System.out.println(formatted);
	    return formatted;
    }
	
	public void calendarHandling(WebDriver driver) throws Exception {
		
		WebElement enterDates = driver.findElement(By.xpath("//*[@id=\"component_2\"]/div/div[3]/div/div[1]/div/div/div[2]/div/button"));
		enterDates.click();
		Thread.sleep(2000);
		
		// Select Check in date in calendar
		driver.findElement(By.xpath("//div[@class='CNFtj']/div[@role='rowgroup']/div[@class='IxycC f']/div[@aria-label='"+checkInDate()+"']")).click(); 
		Thread.sleep(300);
		
		// Get Calendar Instance
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
		Date formattedCheckOutDate;
		try {
			sdf.setLenient(false);
			formattedCheckOutDate = sdf.parse(checkOutDate());
			calendar.setTime(formattedCheckOutDate);
			
			// int checkOutDay = calendar.get(Calendar.DAY_OF_MONTH);
			int checkOutMonth = calendar.get(Calendar.MONTH);
			int checkOutYear = calendar.get(Calendar.YEAR); 
			// System.out.println(checkOutDay+" "+checkOutMonth+" "+checkOutYear);
			
			
			String checkInDate = driver.findElement(By.xpath("//div[@class='cKDuq f JgaNG']/div[@class='CNFtj']/h2[@class='tXvFj c']")).getText(); // June 2023
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(checkInDate));
			// System.out.println("CheckInDate: "+checkInDate);
			
			int checkInMonth = calendar.get(Calendar.MONTH);
			int checkInYear = calendar.get(Calendar.YEAR);
			// System.out.println(checkInMonth+" "+checkInYear);
			
			while(checkOutMonth > checkInMonth || checkOutYear > checkInYear) {			
				driver.findElement(By.xpath("//div[@class='lYukI f k _Q w']/button[@data-testid='nav_next']")).click();
				checkInDate = driver.findElement(By.xpath("//div[@class='cKDuq f JgaNG']/div[@class='CNFtj']/h2[@class='tXvFj c']")).getText(); // July 2023
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(checkInDate));
				// System.out.println(checkInDate);
				
				checkInMonth = calendar.get(Calendar.MONTH);
				checkInYear = calendar.get(Calendar.YEAR);
				// System.out.println(checkInMonth+" "+checkInYear);
			}

			Thread.sleep(1500);
			// Select Check out date in calendar
			driver.findElement(By.xpath("//div[@class='CNFtj']/div[@role='rowgroup']/div[@class='IxycC f']/div[@aria-label='"+checkOutDate()+"']")).click();
		}
		catch(ParseException e) {
			throw new Exception("Invaid date is provided, please check input date");
		}
		
		driver.findElement(By.xpath("//*[@id=\"component_2\"]/div/div[2]")).click();
		Thread.sleep(3000);
	}
	
}
