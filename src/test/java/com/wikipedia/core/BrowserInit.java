package com.wikipedia.core;

import hometask2.PropertiesLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.bonigarcia.wdm.DriverManagerType.*;

public class BrowserInit {
    public static String browserName;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    final static Logger logger = LoggerFactory.getLogger(Browser.class);

    @AllArgsConstructor
    public enum BrowserName {
        EDGE("edge"),
        CHROME("chrome"),
        FIREFOX("firefox");

        private final String name;

        @Override
        public String toString() {
            return name;
        }
    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            browserName = PropertiesLoader.getBrowserName();
            init(browserName);
        }
        return webDriver.get();
    }

    public static void closeDriver() {
        if (webDriver.get() != null) {
            logger.info("======Closing browser======");
            try {
                getDriver().close();
                getDriver().quit();
            } catch (Exception e) {
                logger.warn(e.getMessage());
            } finally {
                webDriver.remove();
                browserName = null;
                logger.info("The driver has been closed.");
            }
        }
    }

    private static void init(String browser) {
        BrowserName name = BrowserName.valueOf(BrowserName.class, browser.toUpperCase());
        switch (name) {
            case FIREFOX:
                logger.info("======Setting up Firefox browser======");
                WebDriverManager.getInstance(FIREFOX).setup();
                webDriver.set(new FirefoxDriver());
                break;
            case CHROME:
                logger.info("======Setting up Chrome browser======");
                WebDriverManager.getInstance(CHROME).setup();
                webDriver.set(new ChromeDriver());
                break;
            case EDGE:
                logger.info("======Setting up Edge browser======");
                WebDriverManager.getInstance(EDGE).setup();
                webDriver.set(new EdgeDriver());
                break;
            default:
                logger.error(String.format("Can't find driver for '%s' browser", browser));
                throw new IllegalArgumentException(String.format("Can't initialize browser '%s'", browser));
        }
    }
}
