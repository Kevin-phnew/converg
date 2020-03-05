package impl;

import common.AbstractJdbcService;
import common.DataSource;
import common.Column;

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
    public List<Column> getTableColumnsAndType() {
        return null;
    }
}
