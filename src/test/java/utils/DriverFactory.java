package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class DriverFactory {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        DriverFactory.webDriver = webDriver;
    }

    public void createDriver() {
        Properties prop = FileHelper.loadConfigProb();
        String browserType=prop.getProperty("browser");
        String os = prop.getProperty("os");
        if(os.equalsIgnoreCase("linux")){
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            HashMap<String, Object> chromePref = new HashMap<>();
            chromePref.put("profile.default_content_settings.popups", 0);
            chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "TestDataTemp" + File.separator + "Contract" + File.separator + "Input" + File.separator);
            chromePref.put("download.prompt_for_download", false);
            chromePref.put("download.directory_upgrade", true);
            chromePref.put("plugins.always_open_pdf_externally", true);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePref);
            options.addArguments("use-fake-device-for-media-stream");
            options.addArguments("use-fake-ui-for-media-stream");
            options.addArguments("incognito");
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--allow-insecure-localhost");
            options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized", "use-fake-ui-for-media-stream"));
            options.addArguments("--disable-notifications");


            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            caps.setCapability("acceptInsecureCerts", true);
            WebDriver driver = new ChromeDriver(caps);
            setWebDriver(driver);
        }
        else{
            switch (browserType) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    System.out.println("Driver is downloaded in path " + System.getProperty("webdriver.chrome.driver"));
                    if (prop.getProperty("os").equalsIgnoreCase("mac")) {
                        String[] args = new String[]{"/bin/bash", "-c", String.format("xattr -d com.apple.quarantine %s", System.getProperty("webdriver.chrome.driver")), "with", "args"};
                        try {
                            new ProcessBuilder(args).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    HashMap<String, Object> chromePref = new HashMap<>();
                    chromePref.put("profile.managed_default_content_settings.popups",2);
                    chromePref.put("profile.managed_default_content_settings.geolocation",2);
                    chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "TestDataTemp" + File.separator + "Contract" + File.separator + "Input" + File.separator);
                    chromePref.put("download.prompt_for_download", false);
                    chromePref.put("download.directory_upgrade", true);
                    chromePref.put("plugins.always_open_pdf_externally", true);
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePref);
                    options.addArguments("use-fake-device-for-media-stream");
                    options.addArguments("use-fake-ui-for-media-stream");
                    options.addArguments("incognito");
                    options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized", "use-fake-ui-for-media-stream"));
                    WebDriver driver = new ChromeDriver(options);
                    setWebDriver(driver);
                    break;

                default:
                    System.out.println("Can not find browser match with " + browserType);
                    break;
            }
        }
    }
}
