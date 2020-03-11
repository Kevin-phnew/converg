package common;

import java.util.Arrays;
import java.util.List;

public class Schema {
    private String domain;
    private String schema;
    private List<Relation> relations;

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
