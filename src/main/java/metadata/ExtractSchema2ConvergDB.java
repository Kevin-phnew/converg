package metadata;

import common.AbstractExtractSchema;
import model.Column;
import model.Relation;
import model.Schema;
import org.apache.commons.lang3.StringUtils;
import util.EnvUtil;
import util.FileUtil;
import util.LogUtil;
import util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class ExtractSchema2ConvergDB extends AbstractExtractSchema {

    private String sepa = java.io.File.separator;

    public void ExtractSchema2ConvergDB(String[] args){
        EnvUtil.checkProperty();
        checkArgs(args);
    }

    @Override
    public void outPutSchema() {
        List<Relation> ansiMetaData = ANSIMetaData.getANSIMetaData();
        if(null == ansiMetaData)
            return ;
        //Metadata extraction
        List<Relation> relations = ExtractSchema2ConvergDB.changeANSIToConvergeMeta(ansiMetaData);

        Scanner scan = new Scanner(System.in);
        LogUtil.info("Please provide path for ConvergDB schema output:");
        String outPath = null;
        if (scan.hasNext()) {
            outPath = scan.next();
            LogUtil.info("Schema written to: " + outPath);
        } else {
            LogUtil.info("no output path");
            return;
        }
        scan.close();
        File file = new File(outPath);
        if(!file.exists()){
            LogUtil.info("Output path not exist!");
            return ;
        }

        // save all in one schema file
        String domain = System.getProperty("db_name");
        String schemaName = System.getProperty("schema");
        List<Relation> relationList = new ArrayList<>();
        // save each schema files
        String finalOutPath = outPath;
        AtomicInteger tableNum = new AtomicInteger(0);
        AtomicInteger success  = new AtomicInteger(0);
        AtomicInteger failed   = new AtomicInteger(0);
        relations.forEach(x -> {        //parallelStream().forEach 相当于多线程，容易出错
            tableNum.getAndAdd(1);
            x.setRelation_type("base");
            Relation relation = new Relation(x.getName(), "derived", x.getColumns());
            relation.setSource(x.getName());
            relationList.add(x);
            relationList.add(relation);
            Schema schema = new Schema(domain, schemaName, relationList);
            boolean falg = FileUtil.writeTxtFile(
                    schema.toString(),
                    finalOutPath.trim().concat(sepa)
                            .concat(domain).concat(".").concat(x.getName()).concat(".schema")
                            .toLowerCase()
                            .replace(sepa + sepa, sepa),
                    "UTF-8");
            if (falg) {
                success.getAndAdd(1);
                LogUtil.info(x.getName() + " process complete");
            } else {
                failed.getAndAdd(1);
                LogUtil.info(x.getName() + " output failed");
            }
            relationList.clear();
        });
        LogUtil.info("Total:" + tableNum + ",Success:" + success + ",Failed:" +failed);
    }




    /**
     * @return List<Column>
     * int(n) n<8	integer
     * int(n) n>=8	bigint
     * timestamp(n)	timestamp
     * timestamp(n) with time zone	timestamptz
     * time(n)	time
     * time(n) with time zone	time with time zone
     */
    public static List<Relation> changeANSIToConvergeMeta(List<Relation> relations) {
        for (Relation r : relations) {
            for (Column e : r.getColumns()) {
                String columnType = e.getColumnType();
                if (columnType.startsWith("int")) {
                    String n = StringUtil.getNumberFromText(columnType);
                    if (StringUtils.isBlank(n) || Integer.parseInt(n) < 8) {
                        e.setColumnType("integer");
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
        }
        return relations;
    }
}
