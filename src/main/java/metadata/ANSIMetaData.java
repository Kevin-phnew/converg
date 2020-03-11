package metadata;

import common.JdbcService;
import common.JdbcServiceFactory;
import model.Column;
import model.DataSource;
import org.apache.commons.lang3.StringUtils;
import util.StringUtil;

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

    /**
     * @return List<Column>
     * int(n) n<8	interger
     * int(n) n>=8	bigint
     * timestamp(n)	timestamp
     * timestamp(n) with time zone	timestamptz
     * time(n)	time
     * time(n) with time zone	time with time zone
     */
    public static List<Column> changeANSIToConvergeMeta(List<Column> columns) {
//        List<Column> columns = getANSIMetaData();
        for (Column e : columns) {
            String columnType = e.getColumnType();
            if (columnType.startsWith("int")) {
                String n = StringUtil.getNumberFromText(columnType);
                if (StringUtils.isBlank(n) || Integer.parseInt(n) < 8) {
                    e.setColumnType("interger");
                } else {
                    e.setColumnType("bigint");
                }
            } else if (columnType.startsWith("timestamp")) {
                if (columnType.endsWith("zone")) {
                    e.setColumnType("timestamptz");
                } else {
                    e.setColumnType("timestamp");
                }
            } else if (columnType.startsWith("time")) {
                if (columnType.endsWith("zone")) {
                    e.setColumnType("time with time zone");
                } else {
                    e.setColumnType("time");
                }
            }
        }
        return columns;
    }

}
