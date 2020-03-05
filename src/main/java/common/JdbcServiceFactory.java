package common;

import impl.MySQLJdbcService;
import impl.OracleJdbcService;

public class JdbcServiceFactory {
    /**
     * 获取jdbc service
     *
     * @param dataSource 数据源
     * @return jdbc service
     */
    public static JdbcService getJdbcService(DataSource dataSource) {
        if (dataSource.getJdbcType().equals(JdbcType.MYSQL.getValue())) {
            return new MySQLJdbcService(dataSource);
        } else if (dataSource.getJdbcType().equals(JdbcType.ORACLE.getValue())) {
            return new OracleJdbcService(dataSource);
        }
        return null;
    }

}
