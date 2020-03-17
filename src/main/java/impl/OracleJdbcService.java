package impl;

import common.AbstractJdbcService;
import model.Column;
import model.DataSource;
import org.apache.commons.lang3.StringUtils;
import util.FileUtil;
import util.JDBCUtil;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class OracleJdbcService extends AbstractJdbcService {

    public OracleJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {
        String driver_jar = System.getProperty("driver_jar");
        String driver_class = System.getProperty("driver_class");
        if (StringUtils.isNotBlank(driver_jar)) {
            JDBCUtil.loadJdbcJar(driver_jar);
        }
        if (StringUtils.isBlank(driver_class)) {
            driver_class = "oracle.jdbc.driver.OracleDriver";
        }
        return driver_class;
    }

    /**
     * 注意：oracle用户名称须大写
     */
    @Override
    protected String schemaPattern() {
        //oracle的schemaPattern为用户名
        return getDataSource().getJdbcUser().toUpperCase();
    }

    @Override
    public List<String> getUserAllTableSql() {
        String sql = "select table_name from user_tables " +
                "where tablespace_name ='" + this.getDataSource().getSchema() + "'";

        return findTables(sql);
    }

    @Override
    public List<String> getParaTablesSql(String tableName) {

        String[] tableNames = tableName.split(";");
        StringJoiner sb = new StringJoiner(" or ");
        Arrays.stream(tableNames).forEach(e -> sb.add("table_name like '" + e + "'"));

        String sql = "SELECT table_name FROM user_tables" +
                " WHERE tablespace_name = '" + this.getDataSource().getSchema() + "'" +
                " and (" + sb.toString() + ")";

        return findTables(sql);
    }

    @Override
    public List<Column> getTableColumnsAndType(String tvName) {
        if (StringUtils.isBlank(tvName)) {
            tvName = this.getDataSource().gettvName();
        }
        String sql = FileUtil.getFile("oracleIR.sql")
//                .replace("#{dbName}", this.getDataSource().getDbName())
                .replace("#{schema}", this.getDataSource().getSchema())
                .replace("#{tbName}", tvName);

        return findColumns(sql);
    }

}