package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.Homepage;

public class HomepageSteps {
    Homepage homepage = new Homepage();

    @Given("^I navigate to weather home page$")
    public void homepageNavigation() {
        homepage.navToWeatherHomepage();
    }

    @Then("^I Search for (.*) in Search field on Header of page$")
    public void searchCity( String cityname){
        homepage.SearchbyTerm(cityname);
    }


}
