package util;

import model.PropertyAttr;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class FileUtil {

    /**
     * Read resources file
     */
    public static String getFile(String fileName) {
        // loading by using java ClassLoader
        StringBuilder out = null;
        try {
            InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            out = new StringBuilder();
            byte[] b = new byte[4096];
            // read input-stream
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

    /**
     * check output path exist or not
     * @return true or false
     */
    public static boolean outputPathConfirm(){
        boolean status = false;

        String outputPath1 = System.getProperty(PropertyAttr.O.getValue());
        String outputPath2 = System.getProperty(PropertyAttr.OUTPUT_FILE.getValue());
        if( !StringUtils.isBlank(outputPath1) && !StringUtils.isBlank(outputPath2)){
            LogUtil.info("Parameters \""+PropertyAttr.O.getValue()+"\" or \""+PropertyAttr.OUTPUT_FILE.getValue()+"\" just need one");
            status = true;
        }

        if(!StringUtils.isBlank(outputPath1)){
            File file = new File(outputPath1);
            if(!file.exists()){
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

    /**
     * Create directory
     * @param output
     * @return boolean
     */
    public static boolean createDir(String output){
        File file = new File(output);
        boolean mkdirs = file.mkdirs();
        if(!mkdirs){
            LogUtil.info("Illegal folder name");
        }
        return mkdirs;
    }
}
