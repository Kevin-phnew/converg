import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;

public class test {
    public static void main(String[] args) {
        String jdbcType = "mysql";
        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String jdbcUser = "root";
        String jdbcPassword = "1234";
//        String dbName = "";
        String tvname = "emp";
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, tvname);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<String> tables = jdbcService.listAllTables();
        tables.stream().forEach(e -> System.out.println(e));
    }
}
