package schema;

import common.Column;
import metadata.ANSIMetaData;

import java.util.List;

/**
 * @Author Kevin
 * @Date 2020/3/4
 */
public class ConvergDB {
    public static void main(String[] args) {

        System.out.println( "===============================\n" +
                            "ConvergDB JDBC schema extractor\n" +
                            "===============================\n");

        //Metadata extraction
        List<Column> fields = ANSIMetaData.getANSIMetaData();

        /**
         * 待开发部分
         */
        //Metadata transformation and output
        ExtraSchema extraSchema = new ExtraSchema();
        extraSchema.exportSchema(fields);

        System.out.println("Process complete");

    }

}
