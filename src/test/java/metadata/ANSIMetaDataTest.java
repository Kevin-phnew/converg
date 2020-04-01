package metadata;

import common.JdbcService;
import common.JdbcServiceFactory;
import metadata.ExtractSchema2ConvergDB;
import model.Column;
import model.DataSource;
import model.Relation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 */
public class ANSIMetaDataTest {

    @Test
    public void getANSIMetaDataTest() {
        List<Column> columns = getMetaData();
        columns.stream().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void changeANSIToConvergeMetaTest() {
        List<Column> fields = getMetaData();
        Relation relation = new Relation();
        relation.setName("");
        relation.setColumn(fields);
        List<Relation> relations = new ArrayList<>();
        relations.add(relation);
        relations = ExtractSchema2ConvergDB.changeANSIToConvergeMeta(relations);
        relations.stream().forEach(e -> System.out.println(e.toString()));
    }

    public List<Column> getMetaData() {
//        java -jar -Ddb_name=testdb \
//        -Dschema=public \
//        -Dtable=company \
//        -Ddatabase=jdbc:postgresql://114.67.96.244:5432/testdb \
//        -Ddb_engine=postgres \
//        -Ddriver_jar=/Users/guguoyu/Documents/app/maven-repo/postgresql/postgresql/9.1-901-1.jdbc4/postgresql-9.1-901-1.jdbc4.jar \
//        -Ddriver_class=org.postgresql.Driver \
//        -DuserName=postgres \
//        -Dpasswd=123456 \
//        schemaExtractor-1.0-SNAPSHOT.jar
        String jdbcType = "postgres";
        String jdbcUrl = "jdbc:postgresql://114.67.96.244:5432/testdb";
        String jdbcUser = "postgres";
        String jdbcPassword = "123456";
        String dbName = "testdb";
        String schema = "public";
        String tbName = "company";
//        String driver_jar = "C:\\Users\\huijun\\.m2\\repository\\postgresql\\postgresql\\9.1-901-1.jdbc4\\postgresql-9.1-901-1.jdbc4.jar";
//        JDBCUtil.loadJdbcJar(driver_jar);//动态加载指定jar
        DataSource param = new DataSource(jdbcType, jdbcUrl, jdbcUser, jdbcPassword, dbName, schema, tbName);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        List<Column> fields = jdbcService.getTableColumnsAndType(tbName);
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
