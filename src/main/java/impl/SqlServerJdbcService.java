package impl;

import common.AbstractJdbcService;
import model.Column;
import model.DataSource;
import model.Relation;

import java.util.List;

/**
 * @Author Kevin
 * @Date 2020/3/5
 */
public class SqlServerJdbcService extends AbstractJdbcService{

    public SqlServerJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {
        return null;
    }

    @Override
    protected String schemaPattern() {
        return null;
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
    public List<Column> getTableColumnsAndType(String tableName) {
        return null;
    }

    @Override
    public List<Relation> getAllTablesColumnsAndType(String tableName) {
        return null;
    }
}
