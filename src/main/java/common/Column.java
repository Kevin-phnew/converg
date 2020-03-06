package common;

public class Column {

    private String columnName;
    private String columnType;
    private String required;

    public Column(String columnName, String columnType, String required) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.required = required;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", required='" + required + '\'' +
                '}';
    }
}
