package common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Relation {
    private String name;
    private String relation_type;
    private String source;
    private List<String> partitions;
    private List<Attribute> attributes;

    public Relation() {

    }

    public Relation(String name, String relation_type, List<Attribute> attributes) {
        this.name = name;
        this.relation_type = relation_type;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(String relation_type) {
        this.relation_type = relation_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<String> partitions) {
        this.partitions = partitions;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("relation \"" + name + "\" {\n");
        builder.append("  relation_type = " + relation_type + "\n");
        if (partitions != null && partitions.size() > 0){
            builder.append("  partitions = [");
            String collect = partitions.parallelStream()
                    .map(x -> "\"" + x + "\"")
                    .collect(Collectors.joining(","));
            builder.append(collect);
            builder.append("]\n");
        }
        builder.append("  attributes {\n");
        for (Attribute attribute : attributes) {
            builder.append("    " + attribute.toString().replaceAll("\n", "\n    ") + "\n");
        }
        builder.append("  }\n");
        builder.append("}");
        return builder.toString();
    }

    public static void main(String[] args) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("make", true,"varchar(128)", "x*3"));
        attributes.add(new Attribute("model", "varchar(128)"));
        attributes.add(new Attribute("year", "integer"));
        Relation relation = new Relation("cars_source", "base", attributes);
        System.out.println(relation);
    }
}
