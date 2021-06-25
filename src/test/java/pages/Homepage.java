package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebActions;

public class Homepage extends WebActions {
    public Homepage() {
       super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
    protected WebElement searchField;

    protected WebElement searchField() {
        return findWebElement(By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']"));
    }

    public void navToWeatherHomepage() {
        navigateUrl(url_WeatherHomepage);
        waitPageReady();
    }

//    public void SearchbyTerm(String terms) {
//        waitForElementPresent(searchField());
//        inputBySendKeys(searchField(), terms);
//        searchField().sendKeys(Keys.ENTER);
//    }

    public void SearchbyTerm(String terms) {
        waitForElementPresent(searchField);
        inputBySendKeys(searchField, terms);
        searchField().sendKeys(Keys.ENTER);
    }

}
