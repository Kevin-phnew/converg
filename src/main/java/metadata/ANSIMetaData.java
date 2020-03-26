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


    public static List<Relation> getANSIMetaData() {

        String userName = EnvUtil.getProperty("userName");
        String passwd   = EnvUtil.getProperty("passwd");
        String database = EnvUtil.getProperty("database");
        String engine   = EnvUtil.getProperty("engine");
        String schema   = EnvUtil.getProperty("schema");
        String dbName   = EnvUtil.getProperty("dbName");
        String table    = EnvUtil.getProperty("table");

        return getANSIMetaData(engine,database,userName,passwd,dbName,schema,table);

    }

    /**
     * Middle tier, providing data to the outside
     * @para engine:oracle,mysql,postgres   Necessary
     * @return List<Column>
     */
    public static List<Relation> getANSIMetaData(String engine,String database,String userName,String
            passwd,String dbName,String schema,String table){

        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
            String[] env = EnvUtil.getEnvironmentUserPasswd();
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
