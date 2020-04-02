package model;

public enum JdbcType {
    ORACLE("oracle"),
    MYSQL("mysql"),
    POSTGRESQL("postgres");

    // define value
    private String value;

    // define get value
    public String getValue() {
        return value;
    }

    // define constructor
    JdbcType(String value) {
        this.value = value;
    }
}