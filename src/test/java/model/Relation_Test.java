package model;

import model.Column;
import model.Relation;
import org.junit.Test;

import java.util.ArrayList;

public class Relation_Test {

    @Test
    public void relationTest() {
        Column column = new Column();
        column.setColumnType("varchar(20)");
        column.setColumnName("name");
        column.setRequired("true");

        Column column1 = new Column();
        column1.setColumnType("int(8)");
        column1.setColumnName("id");
        column1.setRequired("false");

        ArrayList<Column> columnArrayList = new ArrayList<>();
        columnArrayList.add(column);
        columnArrayList.add(column1);
        Relation relation = new Relation();
        relation.setName("car source");
        relation.setRelation_type("base");

        relation.setColumn(columnArrayList);

        System.out.println(relation);
    }

}
