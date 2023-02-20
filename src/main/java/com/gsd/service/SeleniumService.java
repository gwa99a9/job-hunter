package com.gsd.service;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gsd.helpers.SeleniumConfiguration;

public class SeleniumService {
    public void getJobsListings() {
        int pageNum = 1;
        int currentPage = 1;
        int maxPage = 0;
        String jobTitle = "Software Engineer";
        String url = "https://xpress.jobs/jobs?page=" + pageNum + "&KeyWord=" + jobTitle;
        ChromeDriver chromeDriver = SeleniumConfiguration.getChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chromeDriver.navigate().to(url);
        WebElement jobListingsElement = chromeDriver
                .findElement(By.xpath("/html/body/div[1]/div/div[2]/div/section[2]/div/div/div/ul"));
        jobListingsElement.findElements(By.className("job_listing"))
                .forEach(job -> System.out.println(job.findElement(By.className("job_listing-title")).getText()));
    }
}
