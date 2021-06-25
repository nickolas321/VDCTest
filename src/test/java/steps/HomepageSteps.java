package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.Homepage;

public class HomepageSteps {
    Homepage homepage;

    @Given("^I navigate to weather home page$")
    public void homepageNavigation() {
        homepage = new Homepage();
        homepage.navToWeatherHomepage();
    }

    @Then("^I Search for (.*) in Search field on Header of page$")
    public void searchCity( String cityname){
        homepage = new Homepage();
        homepage.SearchbyTerm(cityname);
    }


}
