package common;

import model.Column;
import model.Relation;

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
     * List a table all fields and types
     * @return
     */
    List<Column> getTableColumnsAndType(String tableName);

    /**
     * List a db's tables all fields and types
     *
     * @return
     */
    List<Relation> getAllTablesColumnsAndType(String tableName);
}
