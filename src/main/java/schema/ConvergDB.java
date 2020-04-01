package schema;

import common.AbstractExtractSchema;
import metadata.ExtractSchema2ConvergDB;

/**
 * main class
 */
public class ConvergDB {

    public static void main(String[] args) {
        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");
        //Metadata transformation and output
        AbstractExtractSchema extraSchema = new ExtractSchema2ConvergDB(args);
        extraSchema.run();

    }
}
