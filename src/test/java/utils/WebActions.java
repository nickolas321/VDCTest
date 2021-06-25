package utils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebActions {

    protected static DriverFactory driverFactory = new DriverFactory();
    protected static WebDriver driver;
    final int defaultWaitingTime = 50;
    protected static String url_WeatherHomepage;
    protected static Properties properties = FileHelper.loadConfigProb();



    protected void navigateUrl(String url) {
        driverFactory.createDriver();
        driver = DriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("Navigate to url " + url);
        sleep(5000);
    }

    protected void closeBrowser() {
        if (driver != null) {
            driver.close();
        }
    }

    protected void verifyNavigateToWebAddress(String expectedAdress) {
        String currentAddress = driver.getCurrentUrl();
        compareText(currentAddress, expectedAdress);
    }

    public void refreshPage() {
        driver.get(driver.getCurrentUrl());
        waitPageReady();
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected boolean waitElementVisible(WebElement element) {
        try {
            WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
            defaultWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    protected boolean waitJSReady() {
        WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver1) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver1) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        return defaultWait.until(jsLoad) && defaultWait.until(jQueryLoad);
    }

    protected void waitPageReady() {
        waitJSReady();
    }

    protected void click(WebElement element, String eleName) {
        try {
            waitForElementPresent(element);
            waitElementVisible(element);
            waitElementClickable(element);
            highlightElement(element);
            element.click();
            System.out.println(String.format("Clicked on [%s] button successfully", eleName));
        } catch (Exception ex) {
            Assert.assertFalse(ex.getMessage(), true);
        }
    }


    protected void clickOnLink(WebElement element, String eleName) {
        try {
            waitElementClickable(element);
            highlightElement(element);
            element.click();
            //    logger.info(String.format("Clicked on [%s] link successfully", eleName));
        } catch (Exception ex) {
            Assert.assertFalse(ex.getMessage(), true);
        }
    }

    protected void clickByAction(WebElement element, String eleName) {
        try {
            waitElementClickable(element);
            Actions action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
            //	logger.info(String.format("Clicked on [%s] button successfully", eleName));
        } catch (Exception ex) {
            Assert.assertFalse(ex.getMessage(), true);
        }
    }

    protected void clickByUsingJS(WebElement element, String msg) {
        try {
//            scrollToElement(element);
            highlightElement(element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            Assert.assertFalse(ex.getMessage(), true);
        }
    }







    protected void inputBySendKeys(WebElement element, String strInput) {
        try {
            waitForElementPresent(element);
            waitElementVisible(element);
            element.clear();
            element.sendKeys(strInput);
            System.out.println(String.format("Enter value %s into the text field", strInput));
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    protected void inputByAction(WebElement element, String strInput, String eName) {
        try {
            waitElementVisible(element);
            Actions actions = new Actions(driver);
            actions.click(element);
           actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
            actions.click(element).sendKeys(strInput).build().perform();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }



    protected boolean waitForElementVisibleWithTimeoutSecond(WebElement element, int second) {
        try {
            new WebDriverWait(driver, second).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | NullPointerException | TimeoutException ex) {

            return false;
        }
    }




    protected boolean waitElementClickable(WebElement element) {
        try {
            WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
            defaultWait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }



    protected void waitForElementPresent(WebElement element) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    protected void isElementExisting(WebElement element) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, 120);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
        } catch (Exception e) {
            Assert.fail(String.format("Unable to find the %s . ERROR: %s", element, e.getMessage()));

        }
    }
    public void isElementExisting(String xpath) {
        try {
            WebElement element = findWebElement(By.xpath(xpath));
            if(element != null){
                highlightElement(element);
            }
            else {
                Assert.fail(String.format("Unable to identify the web element with xpath: %s", xpath));
            }
        } catch (Exception e) {
            Assert.fail(String.format("Unable to identify the element . ERROR: %s", e.getMessage()));
        }
    }




    protected void isTextDisplayed(WebElement element, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getText();
            Assert.assertEquals(expectedText.trim().toUpperCase(), actualText.trim().toUpperCase());
        } catch (Exception e) {
            Assert.fail(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()));
        }
    }



    protected void isCssValueDisplayed(WebElement element, String cssValue, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getCssValue(cssValue);
            Assert.assertEquals(expectedText.trim().toUpperCase(), actualText.trim().toUpperCase());
        } catch (Exception e) {
            Assert.fail(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()));
        }
    }

    protected void isTextDisplayed(By by, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            WebElement element = driver.findElement(by);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getText();
            Assert.assertEquals(expectedText.trim().toUpperCase(), actualText.trim().toUpperCase());
        } catch (Exception e) {
            Assert.fail(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()));
        }
    }

    protected void isTextContain(WebElement element, String containedText, String elementName) {
        try {
            waitElementVisible(element);
            highlightElement(element);
            String actualText = element.getText();
            Assert.assertThat(actualText.toUpperCase(), CoreMatchers.containsString(containedText.toUpperCase()));
        } catch (Exception e) {
            Assert.fail(element.getText() + "is NOT contained " + containedText);
        }
    }

    protected String getText(WebElement element, String elementName) {
        try {
            waitElementVisible(element);
            waitForElementPresent(element);
            return element.getText();
        } catch (NoSuchElementException e) {
            Assert.fail(String.format("The element %s is not existing", elementName));
            return "";
        }
    }

    protected void compareAttributeEqualValue(WebElement element, String attribute, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getAttribute(attribute);
            Assert.assertEquals(expectedText.trim().toUpperCase(), actualText.trim().toUpperCase());
        } catch (Exception e) {
            Assert.assertFalse(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()), true);
        }
    }

    protected void compareAttributeContainValue(WebElement element, String attribute, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getAttribute(attribute).toUpperCase();
            Assert.assertThat(actualText, CoreMatchers.containsString(expectedText.toUpperCase()));
        } catch (Exception e) {
            Assert.assertFalse(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()), true);
        }
    }

    protected String getAttributeOfElement(WebElement element, String attribute, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getAttribute(attribute);
            return text;
        } catch (Exception e) {
            Assert.assertFalse(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()), true);
        }
        return "";
    }

    protected static void highlightElement(WebElement element) {
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid yellow;");
            sleep(150);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            sleep(150);
        }
    }

    protected static void highlightElement(By by) {
        WebElement element = driver.findElement(by);
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid yellow;");
            sleep(100);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            sleep(100);
        }
    }

    protected boolean waitFrameDisplayAndSwitchTo(WebElement iframe) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void deswitchIframe() {
        driver.switchTo().defaultContent();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementPresentWithTimeout(By locator, int timeout) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class, ElementNotVisibleException.class).ignoring(TimeoutException.class, InvalidElementStateException.class);
            return wait.until(conditions -> driver.findElement(locator).isDisplayed());
        } catch (Exception ex) {
            return false;
        }
    }

    protected WebElement findWebElement(By by) {
        WebElement webElement = null;
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            webElement = driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return webElement;
    }

    protected WebElement findWebElement(By by, int timeoutInSeconds) {
        WebElement webElement = null;
        try {
            driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
            webElement = driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return webElement;
    }

    protected List<WebElement> findListWebElement(By by) {
        List<WebElement> listWebElement = null;
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            listWebElement = driver.findElements(by);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            return null;
        }
        return listWebElement;
    }

    protected boolean waitForElementPresent(By by) {
        try {
            for (int second = 0; ; second++) {
                if (second >= 2) {
                    Assert.assertFalse("Timeout", false);
                }
                try {
                    if (isElementPresent(by)) {
                        highlightElement(by);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(String.format("Waiting for %s second...", second));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElementsPresent(By by) {
        try {
            for (int i = 0; ; i++) {
                System.out.println(String.format("Waiting for %s second", i));
                if (i >= 6) {
                    Assert.assertSame("TIMEOUT", "60s", "> 600s");
                    break;
                }
                List<WebElement> elements = driver.findElements(by);
                if (elements.size() > 0) {
                    break;
                } else {
                    continue;
                }
            }

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    protected void waitForElementsPresent(List<WebElement> listEle) {
        try {
            for (int i = 0; ; i++) {
                System.out.println(String.format("Waiting for %s second", i));
                if (i >= 6) {
                    Assert.assertSame("TIMEOUT", "60s", "> 600s");
                    break;
                }
                if (listEle.size() > 0) {
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    protected void compareText(String actual, String expected) {
        if (actual.equalsIgnoreCase(expected)) {
            System.out.println(String.format("Actual: %s is EQUAL Expected: %s", actual, expected));
        } else {
            Assert.fail("Actual Text: " + actual + " is NOT equal to Expected Text: " + expected);
        }
    }

    protected void compareTextContain(String actual, String expected) {
        if (actual.toUpperCase().trim().contains(expected.toUpperCase().trim())) {

            // logger.info(actual + "is CONTAINS with " + expected);
        } else {
            Assert.fail("Actual string : " + actual + "does NOT contains sub string: " + expected);
        }
    }

    protected void compareText(WebElement element, String expected) {
        String actual = getText(element, "");
        if (actual.equalsIgnoreCase(expected)) {
            highlightElement(element);
            System.out.println(String.format("Actual: %s is EQUAL Expected: %s", actual, expected));
        } else {
            Assert.fail("Actual Text: " + actual + " is NOT equal to Expected Text: " + expected);
        }
    }

    protected void compareTextContain(WebElement element, String expected) {
        String actual = getText(element, "");

        if (actual.toUpperCase().trim().contains(expected.toUpperCase().trim())) {
            highlightElement(element);
            System.out.println(String.format("Actual: %s is contains Expected: %s", actual, expected));
        } else {
            Assert.fail("Actual string : " + actual + "does NOT contains sub string: " + expected);
        }
    }


    protected void uploadFileUsingSendkeys(WebElement element, String filePath, String eleName) {
        try {
            element.sendKeys(filePath);
        } catch
        (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isElementNOTExisting(WebElement element) {
        if(!element.isDisplayed()) {
            return true;
        }else {
            System.out.print("No element displayed");
            return false;
        }
    }

    public void closeAndSwitchToNextTab() {
        driver.close();
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0));
    }

    public void switchToPreviousTab() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0));
    }

    public void closeTabAndReturn() {
        driver.close();
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0));
    }

    public void switchToPreviousTabAndClose() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
        driver.close();
    }

}
