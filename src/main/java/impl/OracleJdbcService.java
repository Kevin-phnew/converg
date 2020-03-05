package impl;

import common.AbstractJdbcService;
import common.DataSource;
import common.Column;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleJdbcService extends AbstractJdbcService {

    public OracleJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {
        return "oracle.jdbc.driver.OracleDriver";
    }

    /**
     * 注意：oracle用户名称须大写
     */
    @Override
    protected String schemaPattern() {
        //oracle的schemaPattern为用户名
        return getDataSource().getJdbcUser().toUpperCase();
    }

    @Override
    public List<Column> getTableColumnsAndType() {
        String tvName = this.getDataSource().getDbName() + "." + this.getDataSource().gettvName();
        String sql = "select * from " + tvName + " where 0<>0";
        Connection conn = null;
        PreparedStatement pStmt = null; //定义盛装SQL语句的载体pStmt    
        ResultSet rs = null;//定义查询结果集rs
        List<Column> list = new ArrayList<>();
        try {
            conn = this.getConnection();
            pStmt = conn.prepareStatement(sql);//<第4步>获取盛装SQL语句的载体pStmt    
            rs = pStmt.executeQuery();//<第5步>获取查询结果集rs     
            if (rs != null) {
                //数据库列名
                ResultSetMetaData data = rs.getMetaData();
                //遍历结果   getColumnCount 获取表列个数
                while (rs.next()) {
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        // typeName 字段名 type 字段类型
                        Map<String, Object> map = new HashMap<>();
                        map.put(data.getColumnName(i), data.getColumnTypeName(i));//具体长度data.getColumnType(i)
                        list.add(new Column(data.getColumnName(i), data.getColumnTypeName(i), data.getColumnType(i)
                                , data.isNullable(i) == 0));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(conn, pStmt, rs);
        }
        return list;
    }
}