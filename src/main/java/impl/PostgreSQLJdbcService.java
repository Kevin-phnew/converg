package impl;

import common.AbstractJdbcService;
import model.Column;
import model.DataSource;
import org.apache.commons.lang3.StringUtils;
import util.FileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class PostgreSQLJdbcService extends AbstractJdbcService {

    public PostgreSQLJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {
        return "org.postgresql.Driver";
    }

    @Override
    protected String schemaPattern() {
        //mysql的schemaPattern为数据库名称
        String url = getDataSource().getJdbcUrl();
        //jdbc:mysql://localhost:3306/iso_db?useUnicode=true&characterEncoding=UTF-8
        String database = url.substring(url.indexOf("/", 13) + 1);
        int index = database.indexOf("?");
        if (index > 0) {
            database = database.substring(0, index);
        }
        return database;
    }

    /**
     * no table name
     *
     * @return
     */
    @Override
    public List<String> getUserAllTableSql() {

        String sql = "SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema = '" + this.getDataSource().getSchema() + "'";

        return findTables(sql);
    }

    /**
     * tableName asd%;adc;
     * @param tableName
     * @return
     */
    @Override
    public List<String> getParaTablesSql(String tableName) {

        String[] tableNames = tableName.split(";");
        StringJoiner sb = new StringJoiner(" or ");
        Arrays.stream(tableNames).forEach(e -> sb.add("table_name like '" + e + "'"));

        String sql = "SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema = '" + this.getDataSource().getSchema() + "'" +
                " and (" + sb.toString() + ")";

        return findTables(sql);
    }

    @Override
    public List<Column> getTableColumnsAndType(String tvName) {
        if (StringUtils.isBlank(tvName)) {
            tvName = this.getDataSource().gettvName();
        }
        String sql = FileUtil.getFile("postgresIR.sql")
                .replace("#{dbName}", this.getDataSource().getDbName())
                .replace("#{schema}", this.getDataSource().getSchema())
                .replace("#{tbName}", tvName);

        return findColumns(sql);
    }


}
