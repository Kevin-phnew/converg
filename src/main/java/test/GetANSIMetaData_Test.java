package test;

import common.JdbcService;
import common.JdbcServiceFactory;
import model.Column;
import model.DataSource;

import java.util.List;

public class GetANSIMetaData_Test {

    public static void main(String[] args) {
        String userName = "postgres";
        String passwd = "123456";
        String database = "jdbc:postgresql://hadoop001:5432/testdb";
        String engine = "postgres";
        String schema = "public";
        String dbName = "testdb";
        String table = "company";
        DataSource param = new DataSource(engine, database, userName, passwd, dbName, schema, table);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        //Connection test
        boolean testConn = jdbcService.test();

        List<Column> fields = jdbcService.getTableColumnsAndType(table);
        System.out.println("Schema extracted successfully!");
        System.out.println(fields);

    }

}
