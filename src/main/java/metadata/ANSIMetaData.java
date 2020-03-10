package metadata;

import common.Column;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;


public class ANSIMetaData {

    /**
     * 相当于中间层，对外提供数据
     *
     * @return List<Column>
     */
    public static List<Column> getANSIMetaData() {

        String userName = System.getProperty("userName");
        String passwd = System.getProperty("passwd");
        String database = System.getProperty("database");
        String engine = System.getProperty("db_engine");
        String schema = System.getProperty("schema");
        String dbName = System.getProperty("db_name");
        String table = System.getProperty("table");
        DataSource param = new DataSource(engine, database, userName, passwd, dbName, schema, table);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        //测试数据据连接
        boolean testConn = jdbcService.test();
        if(!testConn)
            return null;
        List<Column> fields = jdbcService.getTableColumnsAndType();
        System.out.println("Schema extracted successfully!");
//        String json = ((JSONArray) JSONObject.toJSON(fields)).toJSONString();
//        System.out.println(json);
        return fields;
    }
}
