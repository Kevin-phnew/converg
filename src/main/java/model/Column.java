package model;

import util.StringUtil;

public class Column implements Cloneable {

    private String columnName;
    private String columnType;
    private String required;
    private String expression;
    private Boolean camelcaseToUnderscore=false;
    private Boolean spacesToUnderscore=false;

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

    public Boolean getCamelcaseToUnderscore() {
        return camelcaseToUnderscore;
    }

    @Override
    public Object clone() {
        Column column = null;
        try{
            column = (Column) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return column;
    }

    public void setCamelcaseToUnderscore(Boolean camelcaseToUnderscore) {
        this.camelcaseToUnderscore = camelcaseToUnderscore;
        if (camelcaseToUnderscore) {
            this.columnName = StringUtil.camelcaseToUnderscore(this.columnName);
        }
    }

    public Boolean getSpacesToUnderscore() {
        return spacesToUnderscore;
    }

    public void setSpacesToUnderscore(Boolean spacesToUnderscore) {
        this.spacesToUnderscore = spacesToUnderscore;
        if (spacesToUnderscore) {
            this.columnName = StringUtil.blankSpaceToUnderscore(this.columnName);
        }
    }

    public void setBlankTickExpression() {
        if (this.columnName.contains(" ")){
            this.expression = StringUtil.blackTick(this.columnName);
        }
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
