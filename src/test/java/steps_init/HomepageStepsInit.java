package steps_init;

public class HomepageStepsInit {

            public static String navToHomePage(String gwt){
                return "\n\t" + gwt + String.format("I navigate to weather home page");
            }

            public static String inputCityName(String gwt, String city) {
                return "\n\t" + gwt + String.format("I Search for " + city + " in Search field on Header of page");
            }
}
