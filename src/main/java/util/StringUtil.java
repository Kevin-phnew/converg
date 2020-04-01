package util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * obtain the first string of number
     * @param text original string
     * @return the first string of number
     */
    public static String getNumberFromText(String text) {
        try {
            Pattern pat = Pattern.compile("\\d+");
            Matcher mat = pat.matcher(text);
            if (mat.find()) {
                return mat.group();
            }
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        }
        return "";
    }

    private static Pattern camelPattern = Pattern.compile("(?!^)([_ ]*)([A-Z])");
    /**
     * camel case to snake case
     *      * like:
     *      *   "StudentName" is translated to student_name
     *      *   "Student_Name_IsTom" is translated to student_name_is_tom
     */
    public static String camelcaseToUnderscore(String str) {
        Matcher matcher = camelPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        int toLowerCase = 0;
        while (matcher.find()) {
            toLowerCase = 1;
            matcher.appendReplacement(sb, "_" + matcher.group(2));
        }
        matcher.appendTail(sb);
        return toLowerCase > 0 ? sb.toString().toLowerCase() : sb.toString();
    }

    private static Pattern blankPattern = Pattern.compile("( )+");
    /**
     * blank space to snake case
     * like:
     *   "Student name" is translated to student_name
     *   "Student  name is Tom" is translated to student_name_is_tom
     */
    public static String blankSpaceToUnderscore(String str) {
        Matcher matcher = blankPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        int toLowerCase = 0;
        while (matcher.find()) {
            toLowerCase = 1;
            matcher.appendReplacement(sb, "_");
        }
        matcher.appendTail(sb);
        return toLowerCase > 0 ? sb.toString().toLowerCase() : sb.toString();
    }

    /**
     * if string contains blank space return `string`
     * like input string "ab cd", return "`ab cd`"
     * @param str
     * @return `str`
     */
    public static String blackTick(String str) {
        if (StringUtils.isNotEmpty(str) && str.contains(" ")) {
            return "`".concat(str).concat("`");
        }
        return str;
    }
}
