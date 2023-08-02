package com.selenium.tripcost.cruise;

import java.time.Duration;
import java.util.ArrayList;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CruiseDetails {

    public void cruiseDetails(WebDriver driver) throws InterruptedException {
        
    	// Cruise Line
        driver.findElement(By.xpath("//div[@class='iDcAX']//div[@class='LXbTK'][1]/div/button")).click();
        Thread.sleep(2000);

        for (int i = 0; i <= 2; i++) {
            try {
                List<WebElement> cruiseLineList = driver.findElements(By.xpath("//div[@class='iDcAX']//div[@class='JLKop w']/div/ul/li"));
                for (WebElement l : cruiseLineList) {
                    if (l.getText().equalsIgnoreCase("Norwegian Cruise Line")) {
                        l.click();
                    }
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        Thread.sleep(300);

        // Cruise Ship
        driver.findElement(By.xpath("//div[@class='iDcAX']//div[@class='LXbTK'][2]/div/button")).click();
        Thread.sleep(300);

        for (int i = 0; i <= 2; i++) {
            try {
                List<WebElement> cruiseShipList = driver.findElements(By.xpath("//div[@class='iDcAX']//div[@class='LXbTK'][2]/div/div/ul/li"));

                for (WebElement l : cruiseShipList) {
                    if (l.getText().equalsIgnoreCase("Norwegian Epic")) {
                        l.click();
                    }
                }  
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(2000);

        // Search Button
        driver.findElement(By.xpath("//div[@class='iDcAX']//div[@class='LXbTK'][3]/span/button")).click();
    } 

    public void switchToOtherWindow(WebDriver driver) {
        String parent = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> it = allWindowHandles.iterator();
        
        while (it.hasNext()) {
            String childWindow = it.next();
            if (!parent.equals(childWindow)) {
                driver.switchTo().window(childWindow);
            }
        }
    }

    public void languagesList(WebDriver driver) throws InterruptedException {
    	
        int passengers = Integer.parseInt(driver.findElement(By.xpath("//div[@class='szsLm']/div[1]")).getText().split(" ")[1].trim().replaceAll(",", ""));
        int crew = Integer.parseInt(driver.findElement(By.xpath("//div[@class='szsLm']/div[1]/span")).getText().split(":")[1].trim().replaceAll(",", ""));
        int launchedYear = Integer.parseInt(driver.findElement(By.xpath("//div[@class='szsLm']/div[4]")).getText().split(" ")[1]);

        System.out.println("Passengers: " + passengers + "\n" + "Crew: " + crew + "\n" + "Launched Year: " + launchedYear + "\n");
        WebElement moreBtn = driver.findElement(By.xpath("//span[@class='text']"));

        if (moreBtn.isDisplayed() == true) {
            driver.findElement(By.xpath("//span[@class='text']")).click(); // Click More Button
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Languages Offered Under Cruise Ship
        List<WebElement> languages = driver.findElements(By.xpath("//div[@class='TocEc _Z S2 H2 _f']/ul/li"));
        List<String> langList = new ArrayList<String>();

        for (WebElement l : languages) {
            if (l == languages.get(0)) {
                continue;
            }
            
            String str = l.getText();
            langList.add(str.split(" ")[0]);
        }

        System.out.println("Languages Offered Under Cruise Ship:");
        
        for (int i = 0; i < langList.size(); i++) {
            System.out.println(langList.get(i));
        }
        Thread.sleep(1000);

        // Close Languages List
        driver.findElement(By.xpath("//div[@class='zPIck _Q Z1 t _U c _S zXWgK']")).click();
    }
}