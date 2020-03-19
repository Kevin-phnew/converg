package schema;

import common.ExtractSchema;
import metadata.ExtractSchema2ConvergDB;
import util.FileUtil;

public class ConvergDB {
    public static void main(String[] args) {

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        if (checkArgs(args)) {
            return;
        }
        //Metadata transformation and output
        ExtractSchema extraSchema = new ExtractSchema2ConvergDB();
        extraSchema.outPutSchema();



    }

    public static boolean checkArgs(String[] args) {
        boolean status = false;
        for (String e : args) {
            if (e.equals("--help")) {
                status = true;
                System.out.println(FileUtil.getFile("option"));
                break;
            }
        }
        return status;
    }

}
