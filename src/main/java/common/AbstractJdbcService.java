package common;

import model.Column;
import model.DataSource;
import model.Relation;
import org.apache.commons.lang3.StringUtils;
import util.EnvUtil;
import util.LogUtil;

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
     * check dataSource valid
     *
     * @param dataSource
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

    /**
     * get dataSource
     *
     * @return
     */
    protected DataSource getDataSource() {
        return dataSource;
    }

    /**
     * close Connection
     *
     * @param conn Connection
     */
    protected void close(Connection conn) {
        close(conn, null, null);
    }

    /**
     * close Connection PreparedStatement ResultSet
     *
     * @param conn Connection
     * @param ps   PreparedStatement
     * @param rs   ResultSet
     */
    protected void close(Connection conn, Statement ps, ResultSet rs) {
        //close ResultSet
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
            }
        }
        //close PreparedStatement
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                ps = null;
            }
        }
        //close Connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            }
        }
    }

    /**
     * test connection
     * @return
     */
    @Override
    public boolean test() {
        System.out.println("Attempting connection to " + this.getDataSource().getJdbcUrl() + "...");
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
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            close(conn);
        }
        LogUtil.info("Connected");
        return true;
    }

    /**
     * getConnection
     *
     * @return Connection
     */
    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(loadDriverClass());
            conn = DriverManager.getConnection(dataSource.getJdbcUrl(), dataSource.getJdbcUser(), dataSource.getJdbcPassword());
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
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
            //Parameter 1 int resultSetType
            //ResultSet.TYPE_FORWORD_ONLY: The result set cursor can only scroll down.
            //ResultSet.TYPE_SCROLL_INSENSITIV: The cursor of the result set can be moved up and down, and the current result set remains the same as the database changes.
            //ResultSet.TYPE_SCROLL_SENSITIVE: Returns a scrollable result set that changes synchronously when the database changes.

            //Parameter 2 int resultSetConcurrency
            //ResultSet.CONCUR_READ_ONLY: A result set cannot be used to update a table in the database.
            //ResultSet.CONCUR_UPDATETABLE: Tables in the database can be updated with result sets.

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData meta = conn.getMetaData();
            //Directory name; Database name; Table name; Table type;
            rs = meta.getTables(catalog(), schemaPattern(), tableNamePattern(), types());
            while (rs.next()) {
                result.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
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
        //return null when fields is empty
        if (fields == null || fields.length == 0) {
            return null;
        }
        //return null when table viewer or struct is empty
        List<String> tvs = listAllTables();
        if (tvs == null || tvs.size() == 0) {
            return null;
        }

        List<String> result = new CopyOnWriteArrayList<>();
        //filter table contained special fields by parallel
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
        return EnvUtil.getProperty("schema");
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
     * loadDriverClass
     *
     * @return class
     */
    protected abstract String loadDriverClass();

    /**
     * a schema name pattern; must match the schema name as it is stored in the database; "" retrieves those without a schema; null means that the schema name should not be used to narrow the search
     */
    protected abstract String schemaPattern();

    @Override
    public List<Relation> getAllTablesColumnsAndType(String tableName) {
        if(!StringUtils.isBlank(tableName)){
            tableName = tableName .replaceAll("\"","").replaceAll("'","")
                    .replaceAll("`","");
        }
        List<String> tables = StringUtils.isBlank(tableName) ?
                this.getUserAllTableSql() : this.getParaTablesSql(tableName);
        if(tables == null)
            return null;
        List<Relation> relations = new ArrayList<>();
        tables.stream().forEach(tbName -> {
            Relation relation = new Relation();
            relation.setName(tbName);
            relation.setColumn(this.getTableColumnsAndType(tbName));
            relations.add(relation);
        });
        return relations;
    }

    public abstract List<String> getUserAllTableSql();

    /**
     * @param tableName: The tablename of parameter
     * @return
     */
    public abstract List<String> getParaTablesSql(String tableName);


    /**
     * execute sql to select tables and check input table is exist or not
     * @param sql sql string
     * @return tables list
     */
    public List<String> findTables(String sql){
        Connection conn = getConnection();
        if (conn == null) {
            return null;
        }
        ResultSet rs = null;
        PreparedStatement pStmt = null;
        List<String> result = new ArrayList<>();
        try {
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            if (rs != null) {
                //database Column name
                ResultSetMetaData data = rs.getMetaData();
                // iterate result
                while (rs.next()) {
                    result.add(rs.getString(1));
                }
            }else{
                LogUtil.info("There is no table;");
                return null;
            }
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            close(conn, null, rs);
        }
        return result;
    }

    /**
     * execute sql to select columns information
     * @param sql sql string
     * @return columns list
     */
    public List<Column> findColumns(String sql){
        Connection conn = null;
        PreparedStatement pStmt = null; //define pStmt    
        ResultSet rs = null;//define result set
        List<Column> columns = new ArrayList<>();
        try {
            conn = this.getConnection();
            pStmt = conn.prepareStatement(sql);//obtain pStmt    
            rs = pStmt.executeQuery();//obtain rs     
            if (rs != null) {
                //database column name
                ResultSetMetaData data = rs.getMetaData();
                //iterate result
                while (rs.next()) {
                    columns.add(new Column(
                            rs.getString(4)
                            , rs.getString(5)
                            , rs.getString(6).equals("required") ? "true" : "false"));
                }
            }
        } catch (Exception e) {
            LogUtil.debug(e.getMessage(), e);
        } finally {
            this.close(conn, pStmt, rs);
        }
        return columns;
    }
}