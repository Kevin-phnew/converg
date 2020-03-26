package util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import util.LogUtil;

import java.util.Map;

public class Env_Test {

    @Test
    public  void envTest() {
        String[] arr = new String[2];
        Map<String, String> env = System.getenv();
        String userName = env.get("USER_NAME");
        String passwd = env.get("PASSWORD");

        System.out.println("USER_NAME : " + userName);
        System.out.println("PASSWORD : " + passwd);

    }
}
