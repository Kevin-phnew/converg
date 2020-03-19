package test;

import model.Column;

public class Column_Test {

    public static void main(String[] args) {
        Column column = new Column();
        column.setColumnType("varchar(20)");
        column.setColumnName("name");
        column.setRequired("true");
        System.out.println(column);
    }
}
