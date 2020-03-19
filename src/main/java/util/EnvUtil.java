package util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class EnvUtil {

    public static void main(String args[]){
        EnvUtil.getEnvironment();
    }

    /**
     *
     * @return String[]
     */
    public static String[] getEnvironment(){
        String[] arr = new String [2];
        Map<String, String> env = System.getenv();
        String userName = env.get("USER_NAME");

        String passwd = env.get("PASSWORD");

        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            LogUtil.info("The userName or password of the database is empty. " +
                    "Please check out the userName and password, in the" +
                    " environment variable you entered are " + "\"USER_NAME\"" +
                    " and \"PASSWORD\"");
            return null;
        }
        arr[0] = userName;
        arr[1] = passwd;

        return arr;
    }
}
