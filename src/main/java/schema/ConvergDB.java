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

        //Metadata transformation and output
        ExtractSchema extraSchema = new ExtraSchema();
        extraSchema.outPutSchema();

        System.out.println("Process complete");

    }

}
