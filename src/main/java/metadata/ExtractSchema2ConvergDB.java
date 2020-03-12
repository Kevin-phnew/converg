package metadata;

import common.ExtractSchema;
import model.Column;
import model.Relation;
import model.Schema;
import org.apache.commons.lang3.StringUtils;
import util.FileUtil;
import util.LogUtil;
import util.StringUtil;

import java.util.List;
import java.util.Scanner;


public class ExtractSchema2ConvergDB extends ExtractSchema {

    @Override
    public void outPutSchema() {
        List<Column> ansiMetaData = ANSIMetaData.getANSIMetaData();
        if(null == ansiMetaData)
            return ;
        //Metadata extraction
        List<Column> fields = ExtractSchema2ConvergDB.changeANSIToConvergeMeta(ansiMetaData);

        Scanner scan = new Scanner(System.in);
        LogUtil.info("Please provide path for ConvergDB schema output:");
        String outPath = null;
        if (scan.hasNext()) {
            outPath = scan.next();
            LogUtil.info("Schema written to: \n" + outPath);
        } else {
            LogUtil.info("no output path");
            return;
        }
        scan.close();

        String domain = System.getProperty("db_name");
        String schemaName = System.getProperty("schema");
        String relationName = System.getProperty("table");
        Relation relation = new Relation(relationName, "base", fields);
        Schema schema = new Schema(domain, schemaName, relation);

        boolean res = FileUtil.writeTxtFile(schema.toString(), outPath.trim(), "UTF-8");
        if (res) {
            LogUtil.info("Process complete");
        } else {
            LogUtil.info("Output file failed, please check the output file path");
        }
    }




    /**
     * @return List<Column>
     * int(n) n<8	interger
     * int(n) n>=8	bigint
     * timestamp(n)	timestamp
     * timestamp(n) with time zone	timestamptz
     * time(n)	time
     * time(n) with time zone	time with time zone
     */
    public static List<Column> changeANSIToConvergeMeta(List<Column> columns) {
//        List<Column> columns = getANSIMetaData();
        for (Column e : columns) {
            String columnType = e.getColumnType();
            if (columnType.startsWith("int")) {
                String n = StringUtil.getNumberFromText(columnType);
                if (StringUtils.isBlank(n) || Integer.parseInt(n) < 8) {
                    e.setColumnType("interger");
                } else {
                    e.setColumnType("bigint");
                }
            } else if (columnType.startsWith("timestamp")) {
                if (columnType.endsWith("zone")) {
                    e.setColumnType("timestamptz");
                } else {
                    e.setColumnType("timestamp");
                }
            } else if (columnType.startsWith("time")) {
                if (columnType.endsWith("zone")) {
                    e.setColumnType("time with time zone");
                } else {
                    e.setColumnType("time");
                }
            }
        }
        return columns;
    }
}
