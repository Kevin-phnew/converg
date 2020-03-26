package model;

import model.Column;
import model.Relation;
import model.Schema;
import org.junit.Test;
import util.FileUtil;
import util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaTest {
    @Test
    public void createSchemaAndRelation() {
        List<Column> attributes = new ArrayList<>();
        attributes.add(new Column("make", "true","varchar(128)", "x*3"));
        attributes.add(new Column("model", "varchar(128)"));
        attributes.add(new Column("year", "integer"));

        List<Relation> relations1 = new ArrayList<>();
        relations1.add(new Relation("cars_source", "base", attributes));

        Relation relation2 = new Relation("cars", "derived", attributes);
        relation2.setSource("cars_source");

        relations1.add(relation2);
        Schema schema1 = new Schema("inventory", "vehicles", relations1);
        System.out.println(schema1);


        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        List<Relation> relations2 = new ArrayList<>();
        Relation baseRelation = new Relation("cars", "base", attributes);
        relations2.add(baseRelation);
        relations2.add(new Relation("test", "base", attributes));

        List<Relation> collect = relations2.parallelStream().flatMap(x -> {
            List<Relation> relationList = new ArrayList<>();
            x.setRelation_type("base");
            Relation relation = new Relation(x.getName().concat("_target"), "derived", x.getColumns());
            relation.setSource(x.getName());
            relationList.add(x);
            relationList.add(relation);
            return relationList.stream();
        }).collect(Collectors.toList());
        Schema schema2 = new Schema("domain", "schemaName", collect);
        System.out.println(schema2);

        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        List<Relation> relations3 = new ArrayList<>();
        relations3.add(new Relation("cars", "base", attributes));
        relations3.add(new Relation("test", "base", attributes));
        relations3.parallelStream().forEach(x -> {
            List<Relation> tempList = new ArrayList<>();
            x.setRelation_type("base");
            Relation relation = new Relation(x.getName().concat("_target"), "derived", x.getColumns());
            relation.setSource(x.getName());
            tempList.add(x);
            tempList.add(relation);
            Schema schema3 = new Schema("domain", "schemaName", tempList);
            System.out.println(schema3);
        });
    }
}
