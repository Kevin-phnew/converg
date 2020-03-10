package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 获取字符第一串数字
     *
     * @param text
     * @return
     */
    public static String getNumberFromText(String text) {
        try {
            Pattern pat = Pattern.compile("\\d*");
            Matcher mat = pat.matcher(text);
            if (mat.find()) {
                return mat.group();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
