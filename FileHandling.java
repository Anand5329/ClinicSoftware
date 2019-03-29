package ClinicSoftware;
import java.io.*;
import com.opencsv.*;

import java.util.*;
public class FileHandling {
    String recordHeader[]={"Name","Phone No.","First Date","Age","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};

    void createRecordFile()throws IOException
    {
        FileWriter fw=new FileWriter("Record.csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(recordHeader);
        writer.close();
    }
}
