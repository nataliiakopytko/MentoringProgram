package com.wikipedia.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BrowserWaiter extends Browser {

    private long pollingInterval = TimeOutConstants.DEFAULT_POLLING_INTERVAL_500_MS;

    public void waitForElementDisplayed(final WebElement element, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_TIMEOUT_10_000_MS;
        try {
            Waiter waiter = () -> waitUntilExpected(webDriver -> {
                try {
                    if (element.isDisplayed()) {
                        logger.info(String.format("The element '%s' has been displayed.", element.toString()));
                        return true;
                    } else {
                        logger.info(String.format("The element '%s' isn't displayed on the page. Waiting...", element.toString()));
                        return false;
                    }
                } catch (NoSuchElementException e) {
                    logger.error(String.format("No such element '%s' exception: %s.", element.toString(), e.toString()));
                    return false;
                } catch (StaleElementReferenceException e) {
                    logger.error(String.format("Stale Element Reference Exception for element '%s': %s.", element.toString(), e.toString()));
                    return false;
                }
            }, msToWaitLoc);
            changeTimeOutsAndWait(waiter, TimeOutConstants.DEFAULT_IMPLICIT_WAIT_50_MS, TimeOutConstants.DEFAULT_POLLING_TIMEOUT_10_MS);
        } catch (TimeoutException e) {
            throw new TimeoutException(String.format("The element '%s' is not displayed after timeout '%d' millisec(s).", element, msToWaitLoc));
        }
    }

    public void waitForElementIsNotDisplayed(final By element, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_TIMEOUT_10_000_MS;
        try {
            Waiter waiter = () ->
                    waitUntilExpected(webDriver -> {
                        try {
                            if (!webDriver.findElement(element).isDisplayed()) {
                                logger.info(String.format("The element '%s' has been disappeared.", element.toString()));
                                return true;
                            } else {
                                logger.info(String.format("The element '%s' is still displayed on the page. Waiting...", element.toString()));
                                return false;
                            }
                        } catch (NoSuchElementException | StaleElementReferenceException e) {
                            logger.info(String.format("The element '%s' has been disappeared.", element.toString()));
                            return true;
                        }
                    }, msToWaitLoc);
            changeTimeOutsAndWait(waiter, TimeOutConstants.DEFAULT_IMPLICIT_WAIT_50_MS, TimeOutConstants.DEFAULT_POLLING_TIMEOUT_10_MS);
        } catch (TimeoutException e) {
            throw new TimeoutException(String.format("The Element '%s' is still displayed after timeout '%d' millisec(s).", element, msToWaitLoc));
        }
    }

    public void setPollingInterval(final long... interval) {
        pollingInterval = interval.length > 0 ? interval[0] : TimeOutConstants.DEFAULT_POLLING_INTERVAL_500_MS;
    }

    public void setImplicitlyWait(final long... msToWait) {
        final long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_IMPLICIT_WAIT_3_000_MS;
        getDriver().manage().timeouts().implicitlyWait(msToWaitLoc, TimeUnit.MILLISECONDS);
    }

    public void changeTimeOutsAndWait(Waiter waiter, long implicitlyWait, long pollingInterval) {
        setImplicitlyWait(implicitlyWait);
        setPollingInterval(pollingInterval);
        waiter.applyWait();
    }

    public <T> T waitUntilExpected(Function<WebDriver, T> function, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_TIMEOUT_10_000_MS;
        WebDriverWait wait = new WebDriverWait(getDriver(), msToWaitLoc / 1000);
        wait.pollingEvery(Duration.of(pollingInterval, ChronoUnit.MILLIS));
        return wait.until(function);
    }
}
