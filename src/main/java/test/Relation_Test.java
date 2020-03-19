package test;

import model.Column;
import model.Relation;

import java.util.ArrayList;

public class Relation_Test {

    public static void main(String[] args) {

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

        relation.setColumns(columnArrayList);

        System.out.println(relation);

    }
}
