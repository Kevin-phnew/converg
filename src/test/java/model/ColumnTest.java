package model;

import model.Column;
import org.junit.Test;

public class ColumnTest {


    @Test
    public void columenTest() {
        Column column = new Column();
        column.setColumnType("varchar(20)");
        column.setColumnName("name");
        column.setRequired("true");
        System.out.println(column);
    }
}
