package schema;

import common.Column;

import java.util.List;
import java.util.Scanner;

/**
 * @Author Kevin
 * @Date 2020/3/4
 * @Desc 解析json并输出schema文件
 */
public class ExtraSchema {

    //输出schema
    public void exportSchema(List<Column> fields){

        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide path for ConvergDB schema output:");
        String outPath;
        if (scan.hasNext()) {
            outPath = scan.next();
            System.out.println("Schema written to: \n" + outPath);
        }else{
            System.out.println("no output path");
        }

        String schema = getSchema(fields);

        //下面进行存储的工作...
    }

    //解析json格式并形成要求的schema
    private String getSchema(List<Column> fields){
        return null;
    }

}
