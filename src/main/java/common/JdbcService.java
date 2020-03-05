package common;

import java.util.List;

public interface JdbcService {
    /**
     * 测试数据库的连通性
     *
     * @return true表示成功
     */
    boolean test();

    /**
     * 列出所有的表和视图
     *
     * @return 所有的表和视图
     */
    List<String> listAllTables();

    /**
     * 列出所有包含某些字段的表和视图
     *
     * @param fields 字段
     * @return 所有包含某些字段的表和视图
     */
    List<String> listAllTablesFields(String... fields);

    /**
     * 列出所有的字段
     *
     * @param tableName表名或视图名
     * @return 所有的字段
     */
    List<String> listAllFields(String tableName);

}
