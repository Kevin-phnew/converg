//package java;

import common.AbstractExtractSchema;
import metadata.ExtractSchema2ConvergDB;


public class ConvergdbApplication {

      public static void main(String[] args) {
          System.out.println( "===============================\n" +
                  "ConvergdbApplication JDBC schema extractor\n" +
                  "===============================\n");
          //Metadata transformation and output
          AbstractExtractSchema extraSchema = new ExtractSchema2ConvergDB(args);
          extraSchema.run();


    }
}
