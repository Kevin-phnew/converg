package util;

import org.apache.commons.lang3.StringUtils;

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
            LogUtil.debug("File not fount", e);
        } catch (UnsupportedEncodingException e) {
            LogUtil.debug("Unsupported Encoding", e);
        } catch (IOException e) {
            LogUtil.debug("IOException", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LogUtil.debug("", e);
                }
            }
        }
        return result;
    }

    public static boolean outputPathConfirm(){
        boolean status = false;

        String outputPath1 = System.getProperty("o");
        String outputPath2 = System.getProperty("output-file");
        if( !StringUtils.isBlank(outputPath1) && !StringUtils.isBlank(outputPath2)){
            LogUtil.info("Parameters \"output-file\" and \"o\" just need one");
            status = true;
        }

        if(!StringUtils.isBlank(outputPath1)){
            File file = new File(outputPath1);
            if(!file.exists()){
//                boolean dir = createDir(output_o);
//                if(dir){
//                    LogUtil.info("Create folder :" + output_o);
//                }
                LogUtil.info("\""+outputPath1+"\"" + " not exist");
                status = true;
            }
        }
        if(!StringUtils.isBlank(outputPath2)){
            File file = new File(outputPath2);
            if(!file.exists()){
                LogUtil.info("\""+outputPath2+"\"" + " not exist");
                status = true;
            }
        }
        return status;

    }

    public static boolean createDir(String output){
//        String output = "C:\\Users\\PH\\Desktop\\ConvergDB相关\\123\\";
//        String output1 = "sdsa";
        File file = new File(output);
        boolean mkdirs = file.mkdirs();
        if(!mkdirs){
            LogUtil.info("Illegal folder name");
        }
        return mkdirs;
    }
}
