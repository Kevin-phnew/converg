package schema;

import common.ExtractSchema;

/**
 * @Author Kevin
 * @Date 2020/3/4
 */
public class ConvergDB {
    public static void main(String[] args) {

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        // TODO: 从自定义参数中读取
        String domain = "";
        String schemaName = "";
        String relationName = "";

        //Metadata transformation and output
        ExtractSchema extraSchema = new ExtraSchema();
        extraSchema.outPutSchema();



    }

}
