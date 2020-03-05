package impl;

import common.AbstractJdbcService;
import common.DataSource;

import java.util.List;
import java.util.Map;

/**
 * @Author Kevin
 * @Date 2020/3/5
 */
public class PostgresJdbcService extends AbstractJdbcService {

    public PostgresJdbcService(DataSource dataSource) {
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
    public List<Map<String, Object>> getTableColumnsAndType(String tableName) {
        return null;
    }
}
