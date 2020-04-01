package impl;

import common.AbstractJdbcService;
import model.Column;
import model.DataSource;
import org.apache.commons.lang3.StringUtils;
import util.EnvUtil;
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
        String driver_jar = EnvUtil.getProperty("driver_jar");
        String driver_class = EnvUtil.getProperty("driver_class");
        if (StringUtils.isNotBlank(driver_jar)) {
            JDBCUtil.loadJdbcJar(driver_jar);
        }
        if (StringUtils.isBlank(driver_class)) {
            driver_class = "oracle.jdbc.driver.OracleDriver";
        }
        return driver_class;
    }

    /**
     * note: oracle username must be uppercase
     */
    @Override
    protected String schemaPattern() {
        //the oracle schemaPattern is username
        return getDataSource().getJdbcUser().toUpperCase();
    }

    @Override
    public List<String> getUserAllTableSql() {
        String sql = "select table_name from all_tables " +
                "where owner ='" + this.getDataSource().getSchema().toUpperCase() + "'";

        return findTables(sql);
    }

    @Override
    public List<String> getParaTablesSql(String tableName) {

        String[] tableNames = tableName.split(",");
        StringJoiner sb = new StringJoiner(" or ");
        Arrays.stream(tableNames).forEach(e -> sb.add("table_name like '" + e + "'"));

        String sql = "SELECT table_name FROM all_tables" +
                " WHERE owner = '" + this.getDataSource().getSchema().toUpperCase() + "'" +
                " and (" + sb.toString() + ")";

        return findTables(sql);
    }

    @Override
    public List<Column> getTableColumnsAndType(String tbName) {
        if (StringUtils.isBlank(tbName)) {
            tbName = this.getDataSource().gettbName();
        }
        String sql = FileUtil.getFile("oracleIR.sql")
                .replace("#{schema}", this.getDataSource().getSchema())
                .replace("#{tbName}", tbName);

        return findColumns(sql);
    }

}