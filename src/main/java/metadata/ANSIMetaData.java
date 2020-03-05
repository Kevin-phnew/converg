package metadata;

import common.Column;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;

/**
 * @Author Kevin
 * @Date 2020/3/5
 */
public class ANSIMetaData {

    /**
     * 相当于中间层，对外提供数据
     * @return List<Column>
     */
    public static List<Column> getANSIMetaData(){

        String userName = System.getProperty("userName");
        String passwd = System.getProperty("passwd");
        String database = System.getProperty("database");
        String engine = System.getProperty("db_engine");
        String schema = System.getProperty("schema");
        String table = System.getProperty("table");
        DataSource param = new DataSource(engine, database, userName, passwd, "", schema, table);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        jdbcService.test();
        List<Column> fields = jdbcService.getTableColumnsAndType();
        System.out.println("Schema extracted successfully!");
//        String json = ((JSONArray) JSONObject.toJSON(fields)).toJSONString();
//        System.out.println(json);
        return fields;
    }
}
