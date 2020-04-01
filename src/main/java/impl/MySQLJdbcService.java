package impl;

import common.AbstractJdbcService;
import model.Column;
import model.DataSource;
import model.Relation;
import org.apache.commons.lang3.StringUtils;
import util.EnvUtil;
import util.JDBCUtil;
import util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class MySQLJdbcService extends AbstractJdbcService {

    public MySQLJdbcService(DataSource dataSource) {
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
            driver_class = "com.mysql.cj.jdbc.Driver";
        }
        return driver_class;
    }

    @Override
    protected String schemaPattern() {
        // the mysql schemaPattern is database name
        String url = getDataSource().getJdbcUrl();
        //jdbc:mysql://localhost:3306/iso_db?useUnicode=true&characterEncoding=UTF-8
        String database = url.substring(url.indexOf("/", 13) + 1);
        int index = database.indexOf("?");
        if (index > 0) {
            database = database.substring(0, index);
        }
        return database;
    }

    @Override
    public List<String> getUserAllTableSql() {
        return null;
    }

    @Override
    public List<String> getParaTablesSql(String tableName) {
        return null;
    }

    @Override
    public List<Column> getTableColumnsAndType(String tbName) {
        tbName = this.getDataSource().getDbName() + "." + this.getDataSource().gettbName();
        String sql = "select * from " + tbName + " limit 1";
        Connection conn = null;
        //define pStmt
        PreparedStatement pStmt = null;
        //define result set
        ResultSet rs = null;
        List<Column> list = new ArrayList<>();
        try {
            conn = this.getConnection();
            //obtain pStmt
            pStmt = conn.prepareStatement(sql);
            //obtain result set
            rs = pStmt.executeQuery();
            if (rs != null) {
                //database column
                ResultSetMetaData data = rs.getMetaData();
                //iterate result set
                while (rs.next()) {
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        // typeName   type
                        list.add(new Column(data.getColumnName(i), data.getColumnTypeName(i) + data.getColumnType(i)
                                , data.isNullable(i) == 0 ? "true" : "false"));
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            this.close(conn, pStmt, rs);
        }
        return list;
    }

    @Override
    public List<Relation> getAllTablesColumnsAndType(String tableName) {
        return null;
    }
}
