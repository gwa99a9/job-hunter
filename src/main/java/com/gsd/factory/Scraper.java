package com.gsd.factory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gsd.models.Job;

public interface Scraper {
    static Logger logger = LoggerFactory.getLogger(Scraper.class);

    WebElement getJobListingsElement(ChromeDriver chromeDriver);

    List<WebElement> getJobListingsElements(WebElement webElement);

    List<Job> getJobs(ChromeDriver chromeDriver, Scraper scraper, String url, String jobTitle);

    String getTitle(WebElement webElement);

    String getUrl(WebElement webElement);

    String getDescription(WebElement webElement);

    String getType(WebElement webElement);

    String getExpireDate(WebElement webElement);

    String getCompanyName(WebElement webElement);

    String getLocation(WebElement webElement);

    String getEmploymentType(WebElement webElement);

    String getEmploymentPeriod(WebElement webElement);

    String getJobUrgency(WebElement webElement);

    String getCompanyLogo(WebElement webElement);

    String getListingSite(WebElement webElement);

    int getNumberOfPages(ChromeDriver chromeDriver);
}
