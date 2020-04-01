package model;

import java.io.Serializable;

//数据源信息
public class DataSource implements Serializable {
    //database driver type
    private String jdbcType;
    //url
    private String jdbcUrl;
    //user
    private String jdbcUser;
    //pwd
    private String jdbcPassword;
    //schema
    private String schema;
    //
    private String dbName;
    //table name or viewer name
    private String tbName;

    public DataSource(String jdbcType, String jdbcUrl, String jdbcUser, String jdbcPassword, String dbName, String schema, String tbName) {
        this.jdbcType = jdbcType;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
        this.schema = schema;
        this.tbName = tbName;
        this.dbName = dbName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String gettbName() {
        return tbName;
    }

    public void settbName(String tbName) {
        this.tbName = tbName;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "jdbcType='" + jdbcType + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcUser='" + jdbcUser + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", schema='" + schema + '\'' +
                ", dbName='" + dbName + '\'' +
                ", tbName='" + tbName + '\'' +
                '}';
    }
}
