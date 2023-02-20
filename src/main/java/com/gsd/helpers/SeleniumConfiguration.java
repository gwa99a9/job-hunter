package com.gsd.helpers;

import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumConfiguration {
    private static ChromeDriver chromeDriver = null;

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\Personal\\Projects\\job-hunter\\chromedriver.exe");
    }

    public static synchronized ChromeDriver getChromeDriver() {
        if (chromeDriver == null) {
            chromeDriver = new ChromeDriver();
        }
        return chromeDriver;
    }

    private SeleniumConfiguration() {

    }
}