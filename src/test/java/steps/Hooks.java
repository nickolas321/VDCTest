package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.FileHelper;
import utils.WebActions;

public class Hooks extends WebActions {
    @Before
    public void beforeScenario() {
        FileHelper.loadConfigProb();
        System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
        	//Web URL
        url_WeatherHomepage = FileHelper.getConfigValue("homepage");

    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
//        closeBrowser();
    }
}
