package util;

import org.junit.Test;


public class EnvUtilTest {

    @Test
    public void getPropertyTest(){


        String engine = EnvUtil.getProperty("engine");
        String database = EnvUtil.getProperty("database");
        String schema = EnvUtil.getProperty("schema");
        String userName = EnvUtil.getProperty("userName");
        String passwd = EnvUtil.getProperty("passwd");
        String dbName = EnvUtil.getProperty("dbName");
        String table = EnvUtil.getProperty("table");
        String driver_jar = EnvUtil.getProperty("driver_jar");
        String driver_class = EnvUtil.getProperty("driver_class");
        String outputPath = EnvUtil.getProperty("outputPath");
        String camelcase_to_underscore = EnvUtil.getProperty("camelcase_to_underscore");
        String spaces_to_underscore = EnvUtil.getProperty("spaces_to_underscore");

        System.out.println("engine:"+engine);
        System.out.println("database:"+database);
        System.out.println("schema:"+schema);
        System.out.println("userName:"+userName);
        System.out.println("passwd:"+passwd);
        System.out.println("dbName:"+dbName);
        System.out.println("table:"+table);
        System.out.println("driver_jar:"+driver_jar);
        System.out.println("driver_class:"+driver_class);
        System.out.println("outputPath:"+outputPath);
        System.out.println("camelcase_to_underscore:"+camelcase_to_underscore);
        System.out.println("spaces_to_underscore:"+spaces_to_underscore);

    }

}
