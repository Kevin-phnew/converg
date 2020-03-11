package common;

import model.Column;
import model.Relation;
import model.Schema;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SchemaTest {
    @Test
    public void createSchemaAndRelation() {
        List<Column> attributes = new ArrayList<>();
        attributes.add(new Column("make", "true","varchar(128)", "x*3"));
        attributes.add(new Column("model", "varchar(128)"));
        attributes.add(new Column("year", "integer"));
        List<Relation> relations = new ArrayList<>();
        relations.add(new Relation("cars_source", "base", attributes));
        Relation relation2 = new Relation("cars", "derived", attributes);
        relation2.setSource("cars_source");
        relations.add(relation2);
        Schema schema = new Schema("inventory", "vehicles", relations);
        System.out.println(schema);
    }
}
