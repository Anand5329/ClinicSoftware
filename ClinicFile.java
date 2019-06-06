package ClinicSoftware;

import java.io.*;

public class ClinicFile {
    String dir = "C:/Anand/Code Projects!/Directories/";

    boolean isFilePresent(String dir,String folderName,String fileName)
    {
        try
        {
            FileReader fr=new FileReader(dir+folderName+fileName);
        }
        catch(FileNotFoundException e)
        {
                return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
