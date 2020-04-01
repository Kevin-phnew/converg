package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * log info level massage
     * @param msg massage
     */
    public static void info(String msg){
        logger.info(msg);
    }

    /**
     * log warning level massage
     * @param msg massage
     */
    public static void warn(String msg){
        logger.warn(msg);
    }

    /**
     * log debug level massage
     * @param msg massage
     * @param t throwable exception
     */
    public static void debug(String msg, Throwable t) {
        logger.info(msg);
        logger.info("Please refer to the extractSchema.log in the current path for details");
        logger.debug(msg,t);
    }

}
