package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void main(String[] args) {

        logger.info("c1s");
    }

    public static void info(String msg){
        logger.info(msg);
    }

    public static void warn(String msg){
        logger.warn(msg);
    }

    public static void debug(String msg, Throwable t) {
        logger.info(msg);
        logger.info("Please refer to the extractSchema.log in the current path for details");
        logger.debug(msg,t);
    }

}
