package com.gsd.factory.Scrapers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gsd.factory.Scraper;
import com.gsd.models.Job;

public class ScraperXpress implements Scraper {

    @Override
    public String getTitle(WebElement webElement) {
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getUrl(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getDescription(WebElement webElement) {
        try {
            return webElement.findElement(By.xpath("//div[@class='job_listing-overview job_listing__column']"))
                    .getText();
        } catch (WebDriverException e) {
            System.out.println("description not found");
            return null;
        }
    }

    @Override
    public String getType(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getExpireDate(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.xpath("//li[@class='date']")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getCompanyName(WebElement webElement) {
        try {
            return webElement.findElement(By.xpath("//div[@class='job_listing-company']//strong")).getText();
        } catch (WebDriverException e) {
            System.out.println("company name not found");
            return null;
        }
    }

    @Override
    public String getLocation(WebElement webElement) {
        try {
            return webElement.findElement(By.xpath("//div[@class='location col-md-5 col-lg-4']")).getText();
        } catch (WebDriverException e) {
            System.out.println("location not found");
            return null;
        }
    }

    @Override
    public String getEmploymentType(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getEmploymentPeriod(WebElement webElement) {
        try {
            return webElement.findElement(By.xpath("//li[@class='job-type full-time']")).getText();
        } catch (WebDriverException e) {
            System.out.println("emp period not found");
            return null;
        }
    }

    @Override
    public String getJobUrgency(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public String getCompanyLogo(WebElement webElement) {
        try {
            return webElement.findElement(By.xpath("//img[@class='company_logo']")).getAttribute("src");
        } catch (WebDriverException e) {
            System.out.println("company logo not found");
            return null;
        }
    }

    @Override
    public String getListingSite(WebElement webElement) {
        // TODO Auto-generated method stub
        try {
            return webElement.findElement(By.className("job_listing-title")).getText();
        } catch (WebDriverException e) {
            System.out.println("title not found");
            return null;
        }
    }

    @Override
    public List<WebElement> getJobListingsElements(WebElement webElement) {
        try {
            return webElement.findElements(By.className("job_listing"));
        } catch (WebDriverException e) {
            System.out.println("job listing elements not found");
            return null;
        }
    }

    @Override
    public WebElement getJobListingsElement(ChromeDriver chromeDriver) {
        try {
            return chromeDriver.findElement(By.xpath("//ul[@class='job_listings']"));
        } catch (WebDriverException e) {
            System.out.println("main job element not found");
            return null;
        }
    }

    @Override
    public int getNumberOfPages(ChromeDriver chromeDriver) {
        try {
            List<WebElement> pageNumElements = chromeDriver.findElements(By.xpath("//a[@class='page-numbers']"));
            return Integer.parseInt(pageNumElements.get(pageNumElements.size() - 1).getText());
        } catch (WebDriverException e) {
            System.out.println("main job element not found");
            return 0;
        }
    }

    @Override
    public List<Job> getJobs(ChromeDriver chromeDriver, Scraper scraper, String url, String jobTitle) {
        try {
            List<Job> jobs = new ArrayList<>();
            chromeDriver.navigate().to(url);
            int maxPage = scraper.getNumberOfPages(chromeDriver);
            logger.info("Max page is " + maxPage);
            for (int i = 1; i <= maxPage; i++) {
                logger.info("Scraping page " + i + " of " + maxPage);
                url = "https://xpress.jobs/jobs?page=" + i + "&KeyWord=" + jobTitle;
                logger.info("url is " + url);
                chromeDriver.navigate().to(url);
                WebElement jobListingsElement = scraper.getJobListingsElement(chromeDriver);
                List<WebElement> jobListings = scraper.getJobListingsElements(jobListingsElement);
                for (WebElement jobElement : jobListings) {
                    logger.info("Scraping job");
                    Job newJob = new Job();
                    newJob.title(scraper.getTitle(jobElement));
                    newJob.companyLogo(scraper.getCompanyLogo(jobElement));
                    newJob.companyName(scraper.getCompanyName(jobElement));
                    newJob.location(scraper.getLocation(jobElement));
                    newJob.description(scraper.getDescription(jobElement));
                    newJob.employmentPeriod(scraper.getEmploymentPeriod(jobElement));
                    newJob.expireDate(scraper.getExpireDate(jobElement));
                    jobs.add(newJob);
                    logger.info("Job scraped");
                }
                logger.info("Page " + i + " of " + maxPage + " scraped");
            }
            return jobs;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            chromeDriver.quit();
        }
    }

}
