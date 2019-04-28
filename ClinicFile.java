package ClinicSoftware;

import java.io.*;

public class ClinicFile {

    boolean isFilePresent(String dir,String folderName,String fileName)
    {
        try
        {
            FileReader fr=new FileReader(dir+folderName+fileName);
        }
        catch(Exception e)
        {
            Exception f=new FileNotFoundException();
            if(e.equals(f))
                return false;
        }
        return true;
    }
}