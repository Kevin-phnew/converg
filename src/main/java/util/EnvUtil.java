package util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class EnvUtil {

    public static void main(String args[]){
        EnvUtil.getEnvironment();
    }

    /**
     *
     * @return String[]
     */
    public static String[] getEnvironment(){
        String[] arr = new String [2];
        Map<String, String> env = System.getenv();
        String userName = env.get("USER_NAME");

        String passwd = env.get("PASSWORD");

        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            LogUtil.info("The userName or password of the database is empty. " +
                    "Please check out the userName and password, in the" +
                    " environment variable you entered are " + "\"USER_NAME\"" +
                    " and \"PASSWORD\"");
            return null;
        }
        arr[0] = userName;
        arr[1] = passwd;

        return arr;
    }

    public static boolean checkProperty(){



        boolean status = false;
        String engine = System.getProperty("db_engine");
        String database = System.getProperty("database");
        String schema = System.getProperty("schema");
        String info = " is null";

        if(FileUtil.outputPathConfirm()){
            //if output path Illegal,return true
            status = true ;
        }
        if(StringUtils.isBlank(engine)){
            LogUtil.info("\"db_engine\""+ info);
            status = true ;
        }
        if(StringUtils.isBlank(database)){
            LogUtil.info("\"database\""+ info);
            status = true ;
        }
        if(StringUtils.isBlank(schema)){
            LogUtil.info("\"schema\""+ info);
            status = true ;
        }
        return status;
    }

    /**
     * get property
     * @param para
     * @return
     */
    public static String getProperty(String para){
        String engine = System.getProperty("db_engine");
        String database = System.getProperty("database");
        String schema = System.getProperty("schema");
        String userName = System.getProperty("userName");
        String passwd = System.getProperty("passwd");
        String dbName = System.getProperty("db_name");
        String table = System.getProperty("table");
        String driver_jar = System.getProperty("driver_jar");
        String driver_class =System.getProperty("driver_class");
        String outputPath = null;
//        -o or -output-file
        String outputPath1 = System.getProperty("o");
        String outputPath2 = System.getProperty("output-file");

        if(StringUtils.isBlank(outputPath1) && StringUtils.isBlank(outputPath2)){
            outputPath = System.getProperty("user.dir");
        }else{
            outputPath = StringUtils.isBlank(outputPath1) ? outputPath2:outputPath1;
        }

        Map<String,String> map = new HashMap();
        map.put("engine",engine);
        map.put("database",database);
        map.put("schema",schema);
        map.put("userName",userName);
        map.put("passwd",passwd);
        map.put("dbName",dbName);
        map.put("table",table);
        map.put("driver_jar",driver_jar);
        map.put("driver_class",driver_class);
        map.put("outputPath",outputPath);

        return map.get(para);
    }

}
