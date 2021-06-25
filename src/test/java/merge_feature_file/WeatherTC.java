package merge_feature_file;

import steps_init.HomepageStepsInit;
import steps_init.SearchResultPageStepsInit;
import utils.FileHelper;
import utils.GWT;

import java.io.File;
import java.util.List;

public class WeatherTC extends GlobalData {


    public static void verifySearchResultDisplayed(String cont, String desPath) {

        FileHelper.loadConfigProb();
    String keyword = "ho chi minh";

        cont += HomepageStepsInit.navToHomePage(GWT.GIVEN);
        cont += HomepageStepsInit.inputCityName(GWT.THEN, keyword);
        cont += SearchResultPageStepsInit.ResultDisplayingAsExpected(GWT.AND, keyword);
        cont += SearchResultPageStepsInit.clickOnResultLink(GWT.THEN, keyword);
        cont += SearchResultPageStepsInit.verifySelectedValueDisplayedCorrectly(GWT.AND, keyword);

        FileHelper.writeFile(desPath, cont);
    }

    public static void verifyNoResultMatchThenSearchAgain(String cont, String desPath) {

        FileHelper.loadConfigProb();
        String keyword = "hồ chí minh";
        String keyword_new = "coimbatore";

        cont += HomepageStepsInit.navToHomePage(GWT.GIVEN);
        cont += HomepageStepsInit.inputCityName(GWT.THEN, keyword);
        cont += SearchResultPageStepsInit.noResultsAreFound(GWT.AND);
        cont += HomepageStepsInit.inputCityName(GWT.THEN, keyword_new);
        cont += SearchResultPageStepsInit.ResultDisplayingAsExpected(GWT.AND, keyword_new);
        cont += SearchResultPageStepsInit.clickOnResultLink(GWT.THEN, keyword_new);
        cont += SearchResultPageStepsInit.verifySelectedValueDisplayedCorrectly(GWT.AND, keyword_new);

        FileHelper.writeFile(desPath, cont);
    }
}

    class WeatherTCMain extends GlobalData {
        public static void main(String a[]) {

            List<File> listFile = FileHelper.listFileOnDir(filePathFeatureTemp);
            String content = "";
            for (File f : listFile) {
                if (f.isFile()) {
                    switch (f.getName()) {
                        case "TC_01_WeatherPage_VerifySearchResultDisplayed.feature":
                            content = FileHelper.readFile(f.getPath()).replaceAll("@DATE", RUN_DATE);
                            WeatherTC.verifySearchResultDisplayed(content, f.getPath().replaceAll("_temp", ""));
                            System.out.println(String.format("Generated file: \"%s\" successfully", f.getName()));
                            break;
                        case "TC_02_WeatherPage_VerifyNoResultMatchThenSearchAgain.feature":
                            content = FileHelper.readFile(f.getPath()).replaceAll("@DATE", RUN_DATE);
                            WeatherTC.verifyNoResultMatchThenSearchAgain(content, f.getPath().replaceAll("_temp", ""));
                            System.out.println(String.format("Generated file: \"%s\" successfully", f.getName()));
                            break;
                        default:
                            System.out.println("Can not handle feature file:" + f.getName());
                            break;
                    }
                }
            }

        }

}
