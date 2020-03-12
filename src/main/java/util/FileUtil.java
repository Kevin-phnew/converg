package util;

import java.io.*;

public class FileUtil {

    /**
     * Read resources file
     */
    public static String getFile(String fileName) {
        // 这种方式也是使用 java 的 ClassLoader 来读取
        StringBuilder out = null;
        try {
            InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            out = new StringBuilder();
            byte[] b = new byte[4096];
            // 读取流
            for (int n; (n = inputStream.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            LogUtil.debug(e.getMessage(), e);
        }
        return out.toString();
    }

    /**
     * Output file
     */
    public static boolean writeTxtFile(String schema, String filePath, String encoding) {
        FileOutputStream output = null;
        boolean result=false;
        File file = new File(filePath);
        try {
            output = new FileOutputStream(file);
            output.write(schema.getBytes(encoding));
            result=true;
        } catch (FileNotFoundException e) {
            LogUtil.debug(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LogUtil.debug(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LogUtil.debug(e.getMessage(), e);
                }
            }
        }
        return result;
    }


}
