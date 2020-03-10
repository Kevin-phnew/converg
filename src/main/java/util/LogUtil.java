package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void main(String args[]){
        LogUtil.warn("nihao ");
    }

    public static void info(String msg){
        logger.info(msg);
    }

    public static void warn(String msg){
        logger.warn(msg);
    }



}
