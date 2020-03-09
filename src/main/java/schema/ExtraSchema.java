package schema;

import common.*;
import metadata.ANSIMetaData;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author Kevin
 * @Date 2020/3/4
 * @Desc 解析json并输出schema文件
 */
public class ExtraSchema extends ExtractSchema {

    @Override
    public void outPutSchema() {
        //Metadata extraction
        List<Column> fields = ANSIMetaData.getANSIMetaData();

        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide path for ConvergDB schema output:");
        String outPath = null;
        if (scan.hasNext()) {
            outPath = scan.next();
            System.out.println("Schema written to: \n" + outPath);
        } else {
            System.out.println("no output path");
        }

        String schema = getSchema(fields);

        boolean res = FileUtil.writeTxtFile(schema, outPath.trim(), "UTF-8");
        if(res) {
            System.out.println("Output file failed, please check the output file path");
        }
        else{
            System.out.println("Process complete");
        }
    }

    //解析格式并形成要求的schema
    public String getSchema(List<Column> fields) {

        /**
         * 待完善：
         * domain schema relation 三个局部变量需要使用System.getPro...
         */
        String domain = "service";
        String schema = "root";
        String relation = "page_click";

        String str = FileUtil.getFile("outschema");

        StringBuffer sb = new StringBuffer();

        for(Column field:fields){
            sb.append("\n       attribute \""+field.getColumnName()+"\" {\n");
            sb.append("         required = "+field.getRequired()+"\n");
            sb.append("         data_type = "+field.getColumnType()+"\n");
            sb.append("       }");
        }
        String res = str.replaceAll("domain_rep", domain)
                .replaceAll("schema_rep", schema)
                .replaceAll("relation_rep", relation)
                .replaceAll("attribute_rep",sb.toString());
//        System.out.println(res);
        return res;
    }

    /**
     * transform fields to attribute
     * @param fields fields from database
     * @return attribute
     */
    public List<Attribute> getAttributes(List<Column> fields) {
        List<Attribute> attributes = new ArrayList<>();
        for (Column column : fields) {
            Attribute attribute = new Attribute();
            attribute.setName(column.getColumnName());
            attribute.setData_type(column.getColumnType());
            attribute.setRequired(Boolean.getBoolean(column.getRequired()));
            attributes.add(attribute);
        }
        return attributes;
    }

    /**
     * get full schema string, default relation type is base
     * @param domain database name
     * @param schemaName schema name or user name
     * @param relationName relation name or table name
     * @return formatted string type schema
     */
    public String getSchema(String domain, String schemaName, String relationName) {
        List<Column> fields = ANSIMetaData.getANSIMetaData();
        List<Attribute> attributes = getAttributes(fields);
        Relation relation = new Relation(relationName, "base", attributes);
        Schema schema = new Schema(domain, schemaName, relation);
        return schema.toString();
    }

}
