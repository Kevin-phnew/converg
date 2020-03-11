package common;

import java.util.List;

public interface JdbcService {
    /**
     * Test database connectivity
     * @return
     */
    boolean test();

    /**
     * List all tables and views
     * @return
     */
    List<String> listAllTables();

    /**
     * List all tables and views fields
     * @param fields
     * @return
     */
    List<String> listAllTablesFields(String... fields);

    /**
     * List all fields
     * @param tableName
     * @return
     */
    List<String> listAllFields(String tableName);

    /**
     * List all fields and types
     * @return
     */
    List<Column> getTableColumnsAndType();

}
