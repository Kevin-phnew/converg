package model;

import util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Schema {
    private String domain;
    private String schema;
    private List<Relation> relations;
    private Boolean camelcaseToUnderscore=false;
    private Boolean spacesToUnderscore=false;

    public Schema() {

    }

    public Schema(String domain, String schema, Relation relation) {
        this.domain = domain;
        this.schema = schema;
        this.relations = Arrays.asList(relation);
    }

    public Schema(String domain, String schema, List<Relation> relations) {
        this.domain = domain;
        this.schema = schema;
        this.relations = relations;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }


    public Boolean getCamelcaseToUnderscore() {
        return camelcaseToUnderscore;
    }

    public void setCamelcaseToUnderscore(Boolean camelcaseToUnderscore) {
        this.camelcaseToUnderscore = camelcaseToUnderscore;
        if (camelcaseToUnderscore) {
            this.domain = StringUtil.camelcaseToUnderscore(this.domain);
            this.schema = StringUtil.camelcaseToUnderscore(this.schema);
        }
        List<Relation> relationStream = this.relations.stream().map(x -> {
            x.setCamelcaseToUnderscore(this.camelcaseToUnderscore);
            return x;
        }).collect(Collectors.toList());
        this.setRelations(relationStream);
    }

    public Boolean getSpacesToUnderscore() {
        return spacesToUnderscore;
    }

    public void setSpacesToUnderscore(Boolean spacesToUnderscore) {
        this.spacesToUnderscore = spacesToUnderscore;
        if (spacesToUnderscore) {
            this.domain = StringUtil.blankSpaceToUnderscore(this.domain);
            this.schema = StringUtil.blankSpaceToUnderscore(this.schema);
        }
        List<Relation> relationStream = this.relations.stream().map(x -> {
            x.setSpacesToUnderscore(this.spacesToUnderscore);
            return x;
        }).collect(Collectors.toList());
        this.setRelations(relationStream);
        this.domain = StringUtil.blackTick(this.domain);
        this.schema = StringUtil.blackTick(this.schema);
    }

    /**
     * format schema output struct
     * domain "inventory" {
     *   schema "vehicles" {
     *     relation "cars_source" {
     *       relation_type = base
     *       attributes {
     *         attribute "make" {
     *           required = true
     *           data_type = varchar(128)
     *           expression = "x*3"
     *         }
     *       }
     *     }
     *     relation "cars" {
     *       relation_type = derived {
     *         source = "cars_source"
     *       }
     *       attributes {
     *         attribute "year" {
     *           data_type = integer
     *         }
     *       }
     *     }
     *   }
     * }
     *
     * @return formatted schema with \n
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("domain \"" + domain + "\" {\n");
        builder.append("  schema \"" + schema + "\" {\n");
        for (Relation relation : relations) {
            builder.append("    " + relation.toString().replaceAll("\n", "\n    ") + "\n");
        }
        builder.append("  }\n");
        builder.append("}");
        return builder.toString();
    }
}
