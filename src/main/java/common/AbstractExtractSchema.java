package common;


import util.FileUtil;

public abstract class AbstractExtractSchema implements ExtractSchema{

    public String[] args ;

    /**
     * judge arguments,such as --help ect
     */
    public abstract void run();



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
