package schema;

import common.Column;
import common.ExtractSchema;
import common.Relation;
import common.Schema;
import metadata.ANSIMetaData;
import util.FileUtil;

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
        List<Column> fields = ANSIMetaData.changeANSIToConvergeMeta();

        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide path for ConvergDB schema output:");
        String outPath = null;
        if (scan.hasNext()) {
            outPath = scan.next();
            System.out.println("Schema written to: \n" + outPath);
        } else {
            System.out.println("no output path");
        }

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
