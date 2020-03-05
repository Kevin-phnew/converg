package schema;

import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author Kevin
 * @Date 2020/3/4
 */
public class ConvergDB {
    public static void main(String args[]){

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        ConvergDB.convergDBAction();

    }




    /**
     * 该方法执行数据库连接、元数据获取、schema文件输出
     */
    private static void convergDBAction(){
        String userName = System.getProperty("table");
        String passwd = System.getProperty("table");
        String jdbcUrl = System.getProperty("database");
        String jdbcType = System.getProperty("db_engine");
        String info = "Attempting connection to "+jdbcUrl +"as analytics_user...";
        System.out.println(info);

        /**
         * 根据不同数据库信息参数设置
         */
        String jdbcUser = "root";
        String jdbcPassword = "1234";
//        String dbName = "";
        String tvname = "emp";

        /**
         * 元数据抽取部分
         */
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, tvname);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Map<String, Object>> fields = jdbcService.getTableColumnsAndType(tvname);
        fields.stream().forEach(e -> System.out.println(e));

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
        /**
         * DDL到Schema
         */
        extraSchema.exportSchema(fields);

        System.out.println("Process complete");

    }
}
