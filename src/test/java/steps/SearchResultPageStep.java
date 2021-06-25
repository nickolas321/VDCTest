package steps;

import cucumber.api.java.en.And;
import pages.SearchResultPage;

public class SearchResultPageStep {
    SearchResultPage searchResultPage = new SearchResultPage();


    @And("^I verify City Name (.*) are matched with search Results$")
        public void isResultDisplayingAsExpected(String expectedText) {
        searchResultPage.isResultDisplayingAsExpected(expectedText);
    }

    @And("^I verify no results are displayed$")
    public void NoResultsDisplayed() {
        searchResultPage.noResultsFound();
    }


    @And("^I click on the (.*) Result link to see weather information$")
    public void clickOnResultLink(String expectedText) {
        searchResultPage.clickOnResult(expectedText);
    }

    @And("^I verify (.*) city title matches with selected value$")
    public void verifyCityTitle(String expectedText) {
        searchResultPage.VerifySelectedCityTitletIsDisplaying(expectedText);
    }
}
