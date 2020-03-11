package common;

import model.DataSource;
import model.JdbcType;
import impl.MySQLJdbcService;
import impl.OracleJdbcService;
import impl.PostgreSQLJdbcService;

public class JdbcServiceFactory {
    /**
     * get jdbc service
     * @param dataSource
     * @return jdbc service
     */
    public static JdbcService getJdbcService(DataSource dataSource) {
        System.out.println(dataSource.toString());
        if (dataSource.getJdbcType().equals(JdbcType.MYSQL.getValue())) {
            return new MySQLJdbcService(dataSource);
        } else if (dataSource.getJdbcType().equals(JdbcType.ORACLE.getValue())) {
            return new OracleJdbcService(dataSource);
        } else if (dataSource.getJdbcType().equals(JdbcType.POSTGRESQL.getValue())) {
            return new PostgreSQLJdbcService(dataSource);
        }
        return null;
    }

}
