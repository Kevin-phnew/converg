package schema;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;
import common.column;

import java.util.List;
import java.util.Scanner;

/**
 * @Author Kevin
 * @Date 2020/3/4
 */
public class ConvergDB {
    public static void main(String[] args) {

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        ConvergDB.convergDBAction();

    }




    /**
     * 该方法执行数据库连接、元数据获取、schema文件输出
     * java -jar convergdb-schema-extractor.jar \
     * schema=clickstream table=clicks \
     * database=jdbc:postgresql//somehostname:5432/web_analytics \
     * db_engine=postgres \
     * driver_jar=/jars/postgres9_jdbc.jar \
     * driver_class=postgresql.jdbc.Driver
     */
    private static void convergDBAction(){
        String userName = System.getProperty("userName");
        String passwd = System.getProperty("passwd");
        String database = System.getProperty("database");
        String engine = System.getProperty("db_engine");
        String schema = System.getProperty("schema");
        String table = System.getProperty("table");

        DataSource param = new DataSource(engine, database, userName, passwd, "", schema, table);

        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        jdbcService.test();
        List<column> fields = jdbcService.getTableColumnsAndType();
        String json = ((JSONArray) JSONObject.toJSON(fields)).toJSONString();
        System.out.println(json);

        System.out.println("Schema extracted successfully!");

        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide path for ConvergDB schema output:");
        String outPath;
        if (scan.hasNext()) {
            outPath = scan.next();
            System.out.println("Schema written to: \n" + outPath);
        }else{
            System.out.println("no output path");
        }

        ExtraSchema extraSchema = new ExtraSchema();
//        try {
//
//        }catch (){
//
//        }
        extraSchema.exportSchema(fields);

        System.out.println("Process complete");

    }
}
