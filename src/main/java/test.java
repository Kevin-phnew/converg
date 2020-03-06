import common.Column;
import common.DataSource;
import common.JdbcService;
import common.JdbcServiceFactory;
import org.junit.Test;

import java.util.List;

/**
 * 测试类
 */
public class test {

    @Test
    public void postgresSql(){
        String jdbcType = "postgres";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
        String jdbcUser = "postgres";
        String jdbcPassword = "1234";
        String dbName = "test";
        String schema = "pg_catalog";
        String tvName = "pg_user";

        getData(jdbcType,jdbcUrl,jdbcUser,jdbcPassword,dbName,schema,tvName);
    }

    @Test
    public void oracleSql(){
        System.out.println("hello oracle");
    }

    @Test
    public void mySql(){

    }

    public void getData(String jdbcType, String jdbcUrl, String jdbcUser, String jdbcPassword, String dbName, String schema, String tvName){
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tvName);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Column> fields = jdbcService.getTableColumnsAndType();
        fields.stream().forEach(e -> System.out.println(e.toString()));
    }


}
