package util;

import model.JdbcType;
import model.PropertyAttr;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class EnvUtil {
    private static Map<String,String> map = new HashMap();
    static {
        String engine = System.getProperty(PropertyAttr.DB_ENGINE.getValue());
        String database = System.getProperty(PropertyAttr.DATABASE.getValue());
        String schema = System.getProperty(PropertyAttr.SCHEMA.getValue());
        String userName = System.getProperty(PropertyAttr.USER_NAME.getValue());
        String passwd = System.getProperty(PropertyAttr.PASSWD.getValue());
        String dbName = System.getProperty(PropertyAttr.DB_NAME.getValue());
        String table = System.getProperty(PropertyAttr.TABLE.getValue());
        String driver_jar = System.getProperty(PropertyAttr.DRIVER_JAR.getValue());
        String driver_class =System.getProperty(PropertyAttr.DRIVER_CLASS.getValue());
        // -o or -output-file
        String outputPath1 = System.getProperty(PropertyAttr.O.getValue());
        String outputPath2 = System.getProperty(PropertyAttr.OUTPUT_FILE.getValue());
        String camelcase_to_underscore = System.getProperty(PropertyAttr.CAMELCASE_TO_UNDERSCORE.getValue());
        String spaces_to_underscore = System.getProperty(PropertyAttr.SPACES_TO_UNDERSCORE.getValue());


        String outputPath = null;
        if(StringUtils.isBlank(outputPath1) && StringUtils.isBlank(outputPath2)){
            outputPath = System.getProperty("user.dir");
        }else{
            outputPath = StringUtils.isBlank(outputPath1) ? outputPath2:outputPath1;
        }
        map.put("engine", engine);
        map.put("database", database);
        map.put("schema", schema);
        map.put("userName", userName);
        map.put("passwd", passwd);
        map.put("dbName", dbName);
        map.put("table", table);
        map.put("driver_jar", driver_jar);
        map.put("driver_class", driver_class);
        map.put("outputPath", outputPath);
        map.put("camelcase_to_underscore", camelcase_to_underscore);
        map.put("spaces_to_underscore", spaces_to_underscore);
    }

    /**
     *
     * @return String[]
     */
    public static String[] getEnvironmentUserPasswd(){
        String[] arr = new String [2];
        Map<String, String> env = System.getenv();
        String userName = env.get("username");

        String passwd = env.get("password");

        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            LogUtil.info("The username or password of the database is empty. " +
                    "Please check out the username and password in the" +
                    " environment variable you entered are " + "\"username\"" +
                    " and \"password\"");
            return null;
        }
        arr[0] = userName;
        arr[1] = passwd;

        return arr;
    }

    /**
     * Check the parameters entered by the console
     * @return booelan
     */
    public static boolean checkProperty(){

        boolean status = false;
        String engine = EnvUtil.getProperty("engine");
        String database = EnvUtil.getProperty("database");
        String schema = EnvUtil.getProperty("schema");
        String db_name = EnvUtil.getProperty("dbName");
        String info = " is null";

        if(FileUtil.outputPathConfirm()){
            //if output path Illegal,return true
            status = true ;
        }
        if(StringUtils.isBlank(db_name)){
            LogUtil.info("\"db_name\""+ info);
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
        if(!JdbcType.MYSQL.getValue().equalsIgnoreCase(engine)
        && !JdbcType.ORACLE.getValue().equalsIgnoreCase(engine)
        && !JdbcType.POSTGRESQL.getValue().equalsIgnoreCase(engine)) {
            LogUtil.info("Parameter engine must be \"oracle\" or \"mysql\" or \"postgres\"");
            status = true;
        }

        return status;
    }

    /**
     * get property
     * @param para
     * @return
     */
    public static String getProperty(String para){
        return map.get(para);
    }

}
