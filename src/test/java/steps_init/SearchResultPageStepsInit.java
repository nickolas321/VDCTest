package steps_init;

public class SearchResultPageStepsInit {

    public static String ResultDisplayingAsExpected(String gwt, String city) {
        return "\n\t" + gwt + String.format("I verify City Name " + city + " are matched with search Results");
    }

    public static String noResultsAreFound(String gwt) {
        return "\n\t" + gwt + String.format("I verify no results are displayed");
    }

    public static String clickOnResultLink(String gwt, String city) {
        return "\n\t" + gwt + String.format("I click on the %s Result link to see weather information", city);
    }


    public static String verifySelectedValueDisplayedCorrectly(String gwt, String city) {
        return "\n\t" + gwt + String.format("I verify %s city title matches with selected value", city);
    }

}
