package util;

import java.io.IOException;
import java.io.InputStream;

public class readFileUtil {

    public static String getIRSql(String fileName) {
        // 这种方式也是使用 java 的 ClassLoader 来读取
        StringBuilder out = null;
        try {
            InputStream inputStream = readFileUtil.class.getClassLoader().getResourceAsStream("postgresIR.sql");
            out = new StringBuilder();
            byte[] b = new byte[4096];
            // 读取流
            for (int n; (n = inputStream.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
