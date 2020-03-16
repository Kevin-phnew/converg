package util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class EnvUtil {

    public static void main(String args[]){
        EnvUtil.getEnvironment();
    }

    /**
     * 需要知道对方在环境变量中怎么存储数据库密码的
     * @return
     */
    public static String[] getEnvironment(){
        String[] arr = new String [2];

        Map<String, String> env = System.getenv();
        String userName = env.get("USER_NAME");
        String passwd = env.get("PASSWD");
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            LogUtil.info("The userName or passwd of the database is empty. " +
                    "Please check that the userName and password in the" +
                    " parameter or environment variable you entered are " +
                    "\"userName\" and \"passwd\"");
            return null;
        }
        arr[0] = userName;
        arr[1] = passwd;

        return arr;
    }
}
