package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/feature/WeatherPage_VerifySearchResultDisplayed.feature",
//        features = "src/test/resources/features/e2e/casa/newuser_casa_onboarding_flow12/NewUserCasaOnboardingFlow12_Step11_VerifyDetailsOnICore.feature",
        glue= "",
//        plugin = { "pretty","html:target/cucumber-reports" },
        plugin = { "json:E:/VDCReport/report/web/WeatherPage_VerifySearchResultDisplayed.json"},

        monochrome = true
)
public class TestRunner {

}