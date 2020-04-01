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

    /**
     * @desc If you just want to use this tool to extract the metadata of the database instead of outputting our defined schema,
     *        you can refer to the following code
     *
     * @param engine        required, such as postgres,oracle
     * @param database      required, jdbc:postgresql://*.*.*.*:5432/testdb
     * @param userName      required, database username ,you can set it to environment by terminal command ,such as export username=user_test
     * @param passwd        required, database password,you can set it to environment by terminal command ,such as export password=userpwd_test
     * @param dbName        required, the database name
     * @param schema        required, db's catalog or user, namespace
     * @param table         not required, table name, use % wildcard, Multiple Spaces are separated by commas.
     *                                  If this parameter is empty, all the table information under this user will be extracted
     */
    @Test
    public void getANSIMetaDataTest(String engine,String database,String userName,String
            passwd,String dbName,String schema,String table){
        List<Relation> list = ANSIMetaData.getANSIMetaData(engine, database, userName, passwd, dbName, schema, table);
        for (Relation r : list){
            System.out.println(r.toString());
        }

    }

}
