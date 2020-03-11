package common;

public class Column {

    private String columnName;
    private String columnType;
    private String required;
    private String expression;

    public Column() {

    }

    public Column(String columnName, String columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public Column(String columnName, String columnType, String required) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.required = required;
    }

    public Column(String columnName, String columnType, String required, String expression) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.required = required;
        this.expression = expression;
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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * format attribute output struct
     * attribute "make" {
     *   required = true
     *   data_type = varchar(128)
     *   expression = "x*3"
     * }
     * @return formatted attribute with \n
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("attribute \"" + columnName + "\" {\n");
        if (required != null) {
            builder.append("  required = " + required + "\n");
        }

        builder.append("  data_type = " + columnType + "\n");

        if (expression != null) {
            builder.append("  expression = \"" + expression + "\"\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
