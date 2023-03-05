package com.gsd.factory;

import com.gsd.factory.Scrapers.ScraperLinkedin;
import com.gsd.factory.Scrapers.ScraperXpress;

public class ScraperFactory {
    public static Scraper createNewScraper(String scraperName) {
        if (scraperName.isEmpty()) {
            return null;
        } else if (scraperName.equalsIgnoreCase("xpress")) {
            return new ScraperXpress();
        } else if (scraperName.equalsIgnoreCase("linkedin")) {
            return new ScraperLinkedin();
        }
        return null;
    }
}
