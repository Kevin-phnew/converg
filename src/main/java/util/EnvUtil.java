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
        String userName = env.get("userName");
        String passWord = env.get("passWord");
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)){
            LogUtil.info("The userName or passWord of the database is empty. " +
                    "Please check the parameters you entered ");
            return null;
        }
        arr[0] = userName;
        arr[1] = passWord;

        return arr;
    }
}
