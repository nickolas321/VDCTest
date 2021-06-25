package merge_feature_file;

import utils.FileHelper;
import utils.JavaHelper;

import java.util.Properties;

public class GlobalData {
    protected static Properties properties = FileHelper.loadConfigProb();


//    public static final String KEYWORD = "hồ chí sss";
    public static final String filePathFeatureTemp = "src/main/resources/feature_temp/";
    protected static final String RUN_DATE = JavaHelper.getRunTime();



}
