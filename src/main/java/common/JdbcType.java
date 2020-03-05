package common;

public enum JdbcType {
    ORACLE("oracle"),
    MYSQL("mysql"),
    POSTGRESQL("postgresql");

    // 自定义变量
    private String value;

    // 自定义普通成员方法
    public String getValue() {
        return value;
    }

    // 构造方法
    JdbcType(String value) {
        this.value = value;
    }
}