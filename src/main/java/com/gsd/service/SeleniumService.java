package com.gsd.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsd.factory.Scraper;
import com.gsd.factory.ScraperFactory;
import com.gsd.helpers.SeleniumConfiguration;
import com.gsd.models.Job;

public class SeleniumService {
    private static Logger logger = LoggerFactory.getLogger(SeleniumService.class);

    public void getJobsListings() {
        // TODO : save all the xpath per site
        // TODO : load the xpath from site name
        String jobTitle = "Software Engineer";
        String url = "https://xpress.jobs/jobs?page=1" + "&KeyWord=" + jobTitle;
        ChromeDriver chromeDriver = SeleniumConfiguration.getChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Scraper xpressScraper = ScraperFactory.createNewScraper("xpress");
        List<Job> jobs = new ArrayList<>();
        jobs.addAll(xpressScraper.getJobs(chromeDriver, xpressScraper, url, jobTitle));
        logger.info("jobs size: {}", jobs.size());
    }
}
