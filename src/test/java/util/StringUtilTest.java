package util;

import org.junit.Test;

public class StringUtilTest {
    @Test
    public void translate() {
        String str = "StudentName";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.camelcaseToUnderscore(str));
        str = "studentNameIsTom";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.camelcaseToUnderscore(str));
        str = "student_Name_Is_Tom";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.camelcaseToUnderscore(str));
        str = "Student Name";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.blankSpaceToUnderscore(str));
        str = "Student  name is Tom";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.blankSpaceToUnderscore(str));
        str = "pro__Mark_Loca";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.blankSpaceToUnderscore(str));
        str = "pro__sale";
        System.out.println("\"" + str + "\" is translated to " + StringUtil.blankSpaceToUnderscore(str));

    }
}
