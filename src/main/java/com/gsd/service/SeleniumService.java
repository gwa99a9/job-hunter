package com.gsd.service;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import com.gsd.helpers.SeleniumConfiguration;
import com.gsd.models.Job;

public class SeleniumService {
    private static Logger logger = LoggerFactory.getLogger(SeleniumService.class);

    public void getJobsListings() {
        // TODO : save all the xpath per site
        // TODO : load the xpath from site name
        int pageNum = 1;
        int currentPage = 1;
        int maxPage = 0;
        String jobTitle = "Software Engineer";
        String url = "https://xpress.jobs/jobs?page=" + pageNum + "&KeyWord=" + jobTitle;
        ChromeDriver chromeDriver = SeleniumConfiguration.getChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            chromeDriver.navigate().to(url);
            WebElement jobListingsElement = chromeDriver
                    .findElement(By.xpath("//ul[@class='job_listings']"));
            List<WebElement> jobListings = jobListingsElement.findElements(By.className("job_listing"));
            for (WebElement jobListing : jobListings) {
                Job newJob = new Job();
                newJob.title(jobListing.findElement(By.className("job_listing-title")).getText());
                newJob.companyLogo(
                        jobListing.findElement(By.xpath("//img[@class='company_logo']")).getAttribute("src"));
                newJob.companyName(
                        jobListing.findElement(By.xpath("//div[@class='job_listing-company']//strong")).getText());
                newJob.location(
                        jobListing.findElement(By.xpath("//div[@class='location col-md-5 col-lg-4']")).getText());
                newJob.description(jobListing
                        .findElement(By.xpath("//div[@class='job_listing-overview job_listing__column']")).getText());
                newJob.employmentPeriod(
                        jobListing.findElement(By.xpath("//li[@class='job-type full-time']")).getText());
                newJob.expireDate(jobListing.findElement(By.xpath("//li[@class='date']")).getText());
                logger.info(newJob.toString());
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error(e.getMessage());
        } finally {
            chromeDriver.quit();
        }
    }
}
