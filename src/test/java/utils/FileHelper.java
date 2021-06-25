package utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.codehaus.plexus.util.FileUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileHelper {

    public static String readPDFFile(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        System.out.println(text);
        //Closing the document
        document.close();
        return text;
    }

    public static Properties configProp;

    public static Properties loadConfigProb() {
        try {
            configProp = new Properties();
            //String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
            String filePath = "src/main/resources/config.properties";
            InputStream input = new FileInputStream(new File(filePath));
            configProp.load(input);
        } catch (Exception e) {
            System.out.println("Unable to load Config.properties file. ERROR: " + e.getMessage());
        }
        return configProp;
    }

    public static Properties readPropertiesByFilePath(String path) {
        Properties prop = new Properties();
        try {
            prop = new Properties();
            InputStream input = new FileInputStream(new File(path));
            prop.load(input);
        } catch (Exception e) {
            System.out.println("Unable to load Config.properties file. ERROR: " + e.getMessage());
        }
        return prop;
    }

    public static String getConfigValue(String key) {
        return configProp.getProperty(key);
    }

    public static void writeFile(String filePath, String content) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            System.err.format("IOException: %s%n", e.getMessage());
        }
    }

    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath.trim())));
        } catch (Exception e) {
            System.err.format("Exception: %s%n", e.getMessage());
            return "";
        }
    }

    public static void copyFile(File source, File dest) {
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            System.err.format("EXCEPTION: " + e.getMessage());
        }
    }

    public static List<File> listFileOnDir(String directoryName) {
        File directory = new File(directoryName);
        List<File> resultList = new ArrayList<File>();
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
            } else if (file.isDirectory()) {
                resultList.addAll(listFileOnDir(file.getAbsolutePath()));
            }
        }
        return resultList;
    }


}