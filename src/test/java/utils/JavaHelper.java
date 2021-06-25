package utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaHelper {


    public static int getRandomIntBaseOnMaxInt(int max) {
        Random rd = new Random();
        return rd.nextInt(max);
    }
    public static String getRunTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        String strDate = dateFormat.format(date);
        System.out.println("Run Date: " + strDate);
        return strDate;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String convertWardCodeToString_AccentedCharacters(String code) {
        String wardName = null;
        switch (code) {
            case "9244":
                wardName = "Phường Hiệp Bình Chánh";
                break;
            case "9774":
                wardName = "Xã Phú Quý";
                break;
            default:
                System.out.println("Can not convert ward code: " + code);
                break;
        }
        return wardName;
    }


    public static boolean isStringContains(String str, String subStr) {
        String[] temp = str.split("/");
        for (String s : temp) {
            if (s.equalsIgnoreCase(subStr)) {
                return true;
            }
        }
        return false;
    }

}