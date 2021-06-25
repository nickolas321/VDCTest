package pages;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebActions;


public class SearchResultPage extends WebActions {

    public SearchResultPage() {
        super();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[contains(text(),'Not found')]")
    protected WebElement NoResultFound;
    //div[contains(text(),'Not found')]


    public void isResultDisplayingAsExpected (String expectedText) {
         String text = WordUtils.capitalize(expectedText);
        text = StringUtils.stripAccents(text);
        String xpath = "//a[contains(text(),'" + text + "')]";
        isElementExisting(xpath);
    }

    public void noResultsFound () {
        isElementExisting(NoResultFound);
    }

    public void clickOnResult (String inputCity) {
        String text = WordUtils.capitalize(inputCity);
        text = StringUtils.stripAccents(text);
        String xpath = "//a[contains(text(),'" + text + "')]";
        click(findWebElement(By.xpath(xpath)), text);
    }

    public void VerifySelectedCityTitletIsDisplaying (String expectedText) {
        String text = WordUtils.capitalize(expectedText);
        text = StringUtils.stripAccents(text);
        String xpath = "//h2[contains(text(),'" + text + "')]";
        isElementExisting(xpath);
    }

}
