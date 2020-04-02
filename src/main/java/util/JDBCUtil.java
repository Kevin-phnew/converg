package util;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;

public class JDBCUtil {

//    try catch, return when throwing exception
    public Connection getConn(String database,String db_engine){
        return null;
    }

    /**
     * add jar to classpath
     *
     * @param jarPath
     */
    public static void loadJdbcJar(String jarPath) {
        // obtain function from class URLClassLoader, the jar can also see as folder
        File jarFile = new File(jarPath);

        if (jarFile.exists() == false && StringUtils.isNotBlank(jarPath)) {
            System.out.println("jar file not found.");
            return;
        }

        //obtain function addURL from class loader for dynamic call
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }

        // obtain function accessible saving default value
        boolean accessible = method.isAccessible();
        try {
            // modify accessible to enable
            if (accessible == false) {
                method.setAccessible(true);
            }

            // obtain system class loader
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

            // obtain the url of jar file
            java.net.URL url = jarFile.toURI().toURL();

            // append jar url to system url
            method.invoke(classLoader, url);
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            // reset accessible value
            method.setAccessible(accessible);
        }
    }


}
