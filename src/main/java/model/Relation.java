package model;

import java.util.List;
import java.util.stream.Collectors;

public class Relation {
    private String name;
    private String relation_type;
    private String source;
    private List<String> partitions;
    private List<Column> column;

    public Relation() {

    }

    public Relation(String name, String relation_type, List<Column> column) {
        this.name = name;
        this.relation_type = relation_type;
        this.column = column;
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

    public List<Column> getColumns() {
        return column;
    }

    public void setColumns(List<Column> column) {
        this.column = column;
    }

    /**
     * format relation output struct
     * relation "cars_source" {
     *   relation_type = base
     *   column {
     *     attribute "make" {
     *       required = true
     *       data_type = varchar(128)
     *       expression = "x*3"
     *     }
     *     attribute "year" {
     *       data_type = integer
     *     }
     *   }
     * }
     * @return formatted relation with \n
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("relation \"" + name + "\" {\n");
        builder.append("  relation_type = " + relation_type);
        if (source != null) {
            builder.append(" {\n");
            builder.append("    source = \"" + source + "\"\n");
            builder.append("  }");
        }
        builder.append("\n");

        if (partitions != null && partitions.size() > 0){
            builder.append("  partitions = [");
            String collect = partitions.parallelStream()
                    .map(x -> "\"" + x + "\"")
                    .collect(Collectors.joining(","));
            builder.append(collect);
            builder.append("]\n");
        }
        builder.append("  attributes {\n");
        for (Column attribute : column) {
            builder.append("    " + attribute.toString().replaceAll("\n", "\n    ") + "\n");
        }
        builder.append("  }\n");
        builder.append("}");
        return builder.toString();
    }
}
