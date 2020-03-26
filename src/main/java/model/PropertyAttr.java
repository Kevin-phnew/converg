package model;

public enum PropertyAttr {
    DB_ENGINE("db_engine"),
    DATABASE("database"),
    SCHEMA("schema"),
    USER_NAME("userName"),
    PASSWD("passwd"),
    DB_NAME("db_name"),
    TABLE("table"),
    DRIVER_JAR("driver_jar"),
    DRIVER_CLASS("driver_class"),
    O("o"),
    OUTPUT_FILE("output-file"),
    CAMELCASE_TO_UNDERSCORE("camelcase-to-underscore"),
    SPACES_TO_UNDERSCORE("spaces-to-underscore");

    private String value;

    // 自定义普通成员方法
    public String getValue(){
        return value;
    }
    //构造方法
    PropertyAttr(String value){
        this.value = value;
    }





}