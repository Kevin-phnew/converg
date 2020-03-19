package metadata;

import common.JdbcService;
import common.JdbcServiceFactory;
import model.DataSource;
import model.Relation;
import org.apache.commons.lang3.StringUtils;
import util.EnvUtil;
import util.LogUtil;

import java.util.List;


public class ANSIMetaData {

    /**
     * Middle tier, providing data to the outside
     *
     * @return List<Column>
     */
    public static List<Relation> getANSIMetaData() {

        String userName = System.getProperty("userName");
        String passwd = System.getProperty("passwd");
        String database = System.getProperty("database");
        String engine = System.getProperty("db_engine");
        String schema = System.getProperty("schema");
        String dbName = System.getProperty("db_name");
        String table = System.getProperty("table");
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            String[] env = EnvUtil.getEnvironment();
            if(null == env)
                return null;
            userName = env[0];
            passwd   = env[1];
        }

        DataSource param = new DataSource(engine, database, userName, passwd, dbName, schema, table);
        JdbcService jdbcService = JdbcServiceFactory.getJdbcService(param);
        //Connection test
        boolean testConn = jdbcService.test();
        if(!testConn)
            return null;
        List<Relation> relations = jdbcService.getAllTablesColumnsAndType();
        if(relations == null)
            return null;
        LogUtil.info("Schema extracted successfully!");
        return relations;
    }


}
