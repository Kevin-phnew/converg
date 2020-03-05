package common;

public class Column {

    private String columnName;
    private String columnTypeName;
    private int columnType;
    private boolean required;

    public Column(String columnName, String columnTypeName, int columnType, boolean required) {
        this.columnName = columnName;
        this.columnTypeName = columnTypeName;
        this.columnType = columnType;
        this.required = required;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
