package common;

public class Attribute {
    private String name;
    private Boolean required;
    private String data_type;
    private String expression;

    public Attribute() {

    }

    public Attribute(String name, String data_type) {
        this.name = name;
        this.data_type = data_type;
    }

    public Attribute(String name, Boolean required, String data_type) {
        this.name = name;
        this.required = required;
        this.data_type = data_type;
    }

    public Attribute(String name, Boolean required, String data_type, String expression) {
        this.name = name;
        this.required = required;
        this.data_type = data_type;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("attribute \"" + name + "\" {\n");
        if (required != null) {
            builder.append("  required = " + required + "\n");
        }

        builder.append("  data_type = " + data_type + "\n");

        if (expression != null) {
            builder.append("  expression = \"" + expression + "\"\n");
        }
        builder.append("}");
        return builder.toString();
    }

    public static void main(String[] args) {
        Attribute attribute = new Attribute("make", true,"varchar(128)", "x*3");
        System.out.println(attribute);
    }
}
