import common.Column;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;

public class test {
    public static void main(String[] args) {
//        String jdbcType = "mysql";
//        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//        String jdbcUser = "root";
//        String jdbcPassword = "1234";
//        String dbName = "";
//        String schema = "";
//        String tvName = "emp";
//        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tvName);
//        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
//        List<Column> fields = jdbcService.getTableColumnsAndType();
//        fields.stream().forEach(e -> System.out.println(JSONObject.toJSON(e)));

        String jdbcType = "postgres";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
        String jdbcUser = "postgres";
        String jdbcPassword = "1234";
        String dbName = "test";
        String schema = "pg_catalog";
        String tvName = "pg_user";
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tvName);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Column> fields = jdbcService.getTableColumnsAndType();
        fields.stream().forEach(e -> System.out.println(e.toString()));
    }
}
