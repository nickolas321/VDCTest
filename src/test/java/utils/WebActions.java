package utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
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

    public boolean isElementNOTExisting(WebElement element) {
        if(!element.isDisplayed()) {
            return true;
        }else {
            System.out.print("No element displayed");
            return false;
        }
    }


}
