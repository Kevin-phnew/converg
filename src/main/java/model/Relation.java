package model;

import util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Relation implements Cloneable {
    private String name;
    private String relation_type;
    private String source;
    private List<String> partitions;
    private List<Column> column;
    private Boolean camelcaseToUnderscore=false;
    private Boolean spacesToUnderscore=false;

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

    public List<Column> getColumn() {
        return column;
    }

    public void setColumn(List<Column> column) {
        this.column = column;
    }

    @Override
    public Object clone() {
        Relation relation = null;
        try{
            relation = (Relation) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return relation;
    }

    public Boolean getCamelcaseToUnderscore() {
        return camelcaseToUnderscore;
    }

    public void setCamelcaseToUnderscore(Boolean camelcaseToUnderscore) {
        this.camelcaseToUnderscore = camelcaseToUnderscore;
        if (camelcaseToUnderscore) {
            this.name = StringUtil.camelcaseToUnderscore(this.name);
            if (this.getSource() != null){
                this.source = StringUtil.camelcaseToUnderscore(this.source);
            }
        }
        List<Column> columnList = this.column.stream().map(x -> {
            x.setCamelcaseToUnderscore(this.camelcaseToUnderscore);
            return x;
        }).collect(Collectors.toList());
        this.setColumn(columnList);
    }

    public Boolean getSpacesToUnderscore() {
        return spacesToUnderscore;
    }

    public void setSpacesToUnderscore(Boolean spacesToUnderscore) {
        this.spacesToUnderscore = spacesToUnderscore;
        if (spacesToUnderscore) {
            this.name = StringUtil.blankSpaceToUnderscore(this.name);
            if (this.getSource() != null){
                this.source = StringUtil.blankSpaceToUnderscore(this.source);
            }
        }
        List<Column> columnList = this.column.stream().map(x -> {
            x.setSpacesToUnderscore(this.spacesToUnderscore);
            x.setBlankTickExpression(this.relation_type);
            return x;
        }).collect(Collectors.toList());
        this.setColumn(columnList);
        this.name = StringUtil.blackTick(this.name);
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
