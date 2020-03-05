import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String jdbcType = "mysql";
        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String jdbcUser = "root";
        String jdbcPassword = "1234";
//        String dbName = "";
        String tvname = "emp";
        //将数据库连接信息封装为一个对象，传入jdbc工厂，匹配对应的jdbc连接连接服务，
        //并返回jdbc连接服务对象，进行后续的抽取元数据
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, tvname);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Map<String, Object>> fields = jdbcService.getTableColumnsAndType(tvname);
        fields.stream().forEach(e -> System.out.println(e));
    }
}
