package ClinicSoftware;
import java.io.*;
import java.util.*;
import com.opencsv.*;
public class LabWorkFile extends ClinicFile
{
    private String labHeader[]={"Patient Name","Sent Date","Received Date","Lab Name","Description"};
    private String labFolder="Lab Work/";
    private String fileName;

    LabWorkFile(String fileName)
    {
        this.fileName=fileName;
    }

    LabWorkFile(LabWork labWork)
    {
        fileName=labWork.getFileName();
        if(!isFilePresent(dir,labFolder,fileName))
            System.out.println(createFileWithMessage(labWork));
    }

    String getDirectory()
    {
        return dir+labFolder+fileName;
    }

    String getFileName()
    {
        return fileName;
    }

    String createFileWithMessage(LabWork labWork)
    {
        Exception e;
        if((e=createLabFile(labWork))==null)
            return "File Created Successfully!";
        else
           return e.getMessage();
    }

    Exception createLabFile(LabWork lab)
    {
        try {
            FileWriter fw = new FileWriter(dir + labFolder + lab.getPatientName() + " " + lab.getSentDate() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(labHeader);
            String labDetails[] = {lab.getPatientName(), lab.getSentDate(), lab.getReceivedDate(), lab.getLabName(), lab.getWork()};
            writer.writeNext(labDetails);
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    LabWork readFile(String patientName,String sentDate)throws IOException
    {
        FileReader fr=new FileReader(dir+labFolder+patientName+" "+sentDate+".csv");
        CSVReader reader=new CSVReader(fr);
        String LabWork[];
        reader.readNext();

        LabWork=reader.readNext();

        LabWork lab=new LabWork();
        lab.setPatientName(patientName);
        lab.setSentDate(sentDate);
        lab.setReceivedDate(LabWork[1]);
        lab.setLabName(LabWork[2]);
        lab.setWork(LabWork[3]);
        return lab;
    }

    LabWork readFile(String fileName)throws IOException
    {
        if(!fileName.equals(" "))
            return readFile(fileName.split(" ")[0],fileName.split(" ")[1]);
        else
            return null;
    }


    boolean deleteFile(String fileName)
    {
        File file =new File("D:\\Java-Blue J\\src\\ClinicSoftware\\Lab Work\\"+fileName+".csv");
        return file.delete();
    }

    boolean deleteFile(String patientName,String sentDate)
    {
        String fileName=patientName+" "+sentDate;
        return deleteFile(fileName);
    }

    Exception editFile(int index, String entry)
    {
        try {
            FileReader fr = new FileReader(dir + labFolder + fileName + ".csv");
            CSVReader reader=new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+labFolder+"temp.csv");
            CSVWriter writer=new CSVWriter(fw);
            writer.writeNext(reader.readNext());
            String temp[]=reader.readNext();
            temp[index]=entry;
            writer.writeNext(temp);
            File file=new File(dir+labFolder+fileName+".csv");
            file.delete();
            File newFile=new File(dir+labFolder+"temp.csv");
            newFile.renameTo(file);
            reader.close();
            fr.close();
            writer.close();
            fw.close();
        }
        catch (Exception e)
        {
            return e;
        }
        return null;
    }

    int getIndex(String title)throws IOException
    {
        FileReader fr=new FileReader(dir+labFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[]=reader.readNext();
        for(int i=0;i<temp.length;i++)
        {
           if(temp[i].equals(title))
               return i;
        }
        return -999;
    }
}
