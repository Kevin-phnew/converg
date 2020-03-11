import common.JdbcService;
import common.JdbcServiceFactory;
import metadata.ANSIMetaData;
import model.Column;
import model.DataSource;
import org.junit.Test;
import util.JDBCUtil;

import java.util.List;

/**
 * 测试类
 */
public class MetaDataTest {

    @Test
    public void getANSIMetaDataTest() {
        List<Column> columns = getMetaData();
        columns.stream().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void changeANSIToConvergeMetaTest() {
        List<Column> fields = getMetaData();
        fields = ANSIMetaData.changeANSIToConvergeMeta(getMetaData());
        fields.stream().forEach(e -> System.out.println(e.toString()));
    }

    public List<Column> getMetaData() {
        String jdbcType = "postgres";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
        String jdbcUser = "postgres";
        String jdbcPassword = "1234";
        String dbName = "test";
        String schema = "pg_catalog";
        String tvName = "pg_user";
        String driver_jar = "C:\\Users\\huijun\\.m2\\repository\\postgresql\\postgresql\\9.1-901-1.jdbc4\\postgresql-9.1-901-1.jdbc4.jar";
        JDBCUtil.loadJdbcJar(driver_jar);//动态加载指定jar
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tvName);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Column> fields = jdbcService.getTableColumnsAndType();
        return fields;
    }


    @Test
    public void oracleSql() {
        System.out.println("hello oracle");
    }

    @Test
    public void mySql() {

    }

}
