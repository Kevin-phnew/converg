import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        String jdbcType = "mysql";
//        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//        String jdbcUser = "root";
//        String jdbcPassword = "1234";
////        String dbName = "";
//        String tvName = "emp";
//        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, tvName);
//        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
//        List<Map<String, Object>> fields = jdbcService.getTableColumnsAndType(tvName);
//        fields.stream().forEach(e -> System.out.println(e));

        String jdbcType = "postgresql";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/";
        String jdbcUser = "postgres";
        String jdbcPassword = "1234";
        String dbName = "test";
        String schema = "testSchema";
        String tvName = "emp";
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tvName);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Map<String, Object>> fields = jdbcService.getTableColumnsAndType();
        JSONArray json = (JSONArray) JSONObject.toJSON(fields);//,,,
        fields.stream().forEach(e -> System.out.println(e));
    }
}
