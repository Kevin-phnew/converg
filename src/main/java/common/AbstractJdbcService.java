package common;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractJdbcService implements JdbcService {
    private final DataSource dataSource;

    public AbstractJdbcService(DataSource dataSource) {
        this.dataSource = dataSource;
        checkDataSource(dataSource);
    }

    /**
     * 校验数据源信息是否有效
     *
     * @param dataSource 数据源
     */
    private void checkDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("dataSource is null. ");
        }
        if (StringUtils.isBlank(dataSource.getJdbcUrl())) {
            throw new IllegalStateException("jdbc url is null. ");
        }
        if (StringUtils.isAnyBlank(dataSource.getJdbcUser(), dataSource.getJdbcPassword())) {
            throw new IllegalStateException("jdbc user or password is null. ");
        }
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 关闭(释放)资源
     *
     * @param conn Connection
     */
    protected void close(Connection conn) {
        close(conn, null, null);
    }

    /**
     * 关闭(释放)资源
     *
     * @param conn Connection
     * @param ps   PreparedStatement
     * @param rs   ResultSet
     */
    protected void close(Connection conn, Statement ps, ResultSet rs) {
        //关闭ResultSet
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
            }
        }
        //关闭PreparedStatement
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                ps = null;
            }
        }
        //关闭Connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            }
        }
    }

    @Override
    public boolean test() {
        Connection conn = null;
        try {
            conn = getConnection();
            if (conn == null) {
                return false;
            }

            DatabaseMetaData meta = conn.getMetaData();
            if (meta == null) {
                return false;
            }
        } catch (SQLException e) {

        } finally {
            close(conn);
        }
        return true;
    }

    /**
     * 获取连接
     *
     * @return Connection
     */
    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(loadDriverClass());
            conn = DriverManager.getConnection(dataSource.getJdbcUrl(), dataSource.getJdbcUser(), dataSource.getJdbcPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public List<String> listAllTables() {
        Connection conn = getConnection();
        if (conn == null) {
            return null;
        }

        List<String> result = new ArrayList<String>();
        ResultSet rs = null;
        try {
            //参数1 int resultSetType
            //ResultSet.TYPE_FORWORD_ONLY 结果集的游标只能向下滚动。
            //ResultSet.TYPE_SCROLL_INSENSITIVE 结果集的游标可以上下移动，当数据库变化时，当前结果集不变。
            //ResultSet.TYPE_SCROLL_SENSITIVE 返回可滚动的结果集，当数据库变化时，当前结果集同步改变。

            //参数2 int resultSetConcurrency
            //ResultSet.CONCUR_READ_ONLY 不能用结果集更新数据库中的表。
            //ResultSet.CONCUR_UPDATETABLE 能用结果集更新数据库中的表

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData meta = conn.getMetaData();
            //目录名称; 数据库名; 表名称; 表类型;
            rs = meta.getTables(catalog(), schemaPattern(), tableNamePattern(), types());
            while (rs.next()) {
                result.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, null, rs);
        }

        return result;
    }

    @Override
    public List<String> listAllFields(String tableName) {
        Connection conn = getConnection();
        if (conn == null) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        ResultSet rs = null;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getColumns(catalog(), schemaPattern(), tableName.trim(), null);
            while (rs.next()) {
                result.add(rs.getString("COLUMN_NAME"));
            }
        } catch (Exception e) {

        } finally {
            close(conn, null, rs);
        }
        return result;
    }

    @Override
    public List<String> listAllTablesFields(String... fields) {
        //如果字段为空, 则返回null
        if (fields == null || fields.length == 0) {
            return null;
        }
        //如果表结构(视图)为空, 则返回null
        List<String> tvs = listAllTables();
        if (tvs == null || tvs.size() == 0) {
            return null;
        }

        List<String> result = new CopyOnWriteArrayList<>();
        //并发筛选包含特定字段的表结构
        tvs.parallelStream().forEach(tv -> {
            List<String> fieldsList = listAllFields(tv);
            if (fieldsList != null && fieldsList.size() > 0) {
                if (fieldsList.containsAll(Arrays.asList(fields))) {
                    result.add(tv);
                }
            }
        });

        return result;
    }

    /**
     * a catalog name; must match the catalog name as it is stored in the database; "" retrieves those without a catalog; null means that the catalog name should not be used to narrow the search
     */
    protected String catalog() {
        return null;
    }

    /**
     * a table name pattern; must match the table name as it is stored in the database
     */
    protected String tableNamePattern() {
        return "%";
    }

    /**
     * a list of table types, which must be from the list of table types returned from {},to include; null returns all types
     */
    protected String[] types() {

        return new String[]{"TABLE", "VIEW"};
    }

    /**
     * 加载驱动class
     *
     * @return class
     */
    protected abstract String loadDriverClass();

    /**
     * a schema name pattern; must match the schema name as it is stored in the database; "" retrieves those without a schema; null means that the schema name should not be used to narrow the search
     */
    protected abstract String schemaPattern();
}