package common;


import util.FileUtil;

public abstract class AbstractExtractSchema implements ExtractSchema{

    public String[] args ;

    /**
     * judge arguments,such as --help ect
     */
    public abstract void run();

    /**
     * check parameter contains help or not
     * @param args parameters
     * @return return false by default
     *         return true when parameters contains help and print command note
     */
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
