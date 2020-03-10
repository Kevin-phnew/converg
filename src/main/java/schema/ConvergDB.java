package schema;

import common.ExtractSchema;

public class ConvergDB {
    public static void main(String[] args) {

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        //Metadata transformation and output
        ExtractSchema extraSchema = new ExtraSchema();
        extraSchema.outPutSchema();



    }

}
