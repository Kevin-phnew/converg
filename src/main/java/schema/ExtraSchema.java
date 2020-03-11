package schema;

import common.ExtractSchema;
import metadata.ANSIMetaData;
import model.Column;
import model.Relation;
import model.Schema;
import util.FileUtil;

import java.util.List;
import java.util.Scanner;


public class ExtraSchema extends ExtractSchema {

    @Override
    public void outPutSchema() {
        //Metadata extraction
        List<Column> fields = ANSIMetaData.changeANSIToConvergeMeta(ANSIMetaData.getANSIMetaData());

        if(null == fields)
            return ;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide path for ConvergDB schema output:");
        String outPath = null;
        if (scan.hasNext()) {
            outPath = scan.next();
            System.out.println("Schema written to: \n" + outPath);
        } else {
            System.out.println("no output path");
        }
        scan.close();

        String domain = System.getProperty("db_name");
        String schemaName = System.getProperty("schema");
        String relationName = System.getProperty("table");
        Relation relation = new Relation(relationName, "base", fields);
        Schema schema = new Schema(domain, schemaName, relation);

        boolean res = FileUtil.writeTxtFile(schema.toString(), outPath.trim(), "UTF-8");
        if (res) {
            System.out.println("Process complete");
        } else {
            System.out.println("Output file failed, please check the output file path");
        }
    }

}
