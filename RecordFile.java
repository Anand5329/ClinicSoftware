package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;

public class RecordFile extends ClinicFile{
    private String folderName = "Records/";
    private String[] header = {"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String fileName = "";

    RecordFile(Record r)
    {
        Exception e=null;
        fileName=r.getFileName();
        if(!isFilePresent(dir,folderName,fileName))
            if((e=createFile(r))==null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
    }

    RecordFile(String fileName)
    {
        this.fileName=fileName;
    }

    Exception createFile(Record patient) {
        try {
            FileWriter fw = new FileWriter(dir + folderName + patient.getFileName() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(header);
            String recordDetails[] = {patient.getName(), "" + patient.getPhone(), "" + patient.getAge(), patient.getFirstAppointment().getFileName(), patient.getLatestAppointment().getFileName(), patient.getDesc(), patient.getMoney() + "", patient.getHeartCondition() + "", patient.getAllergy() + "", patient.getDiabetes() + "", patient.getBloodPressure() + ""};
            writer.writeNext(recordDetails);
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    Record readFile() throws IOException {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            reader.readNext();
            String arr[] = reader.readNext();
            Record r = new Record(arr[0], arr[1]);
            r.updateMoney(Double.valueOf(arr[6]));
            r.setAge(Integer.valueOf(arr[2]));
            AppointmentFile af1 = new AppointmentFile(arr[3]);
            AppointmentFile af2 = new AppointmentFile(arr[4]);
            r.setFirstAppointment(af1.readFile());
            r.setLatestAppointment(af2.readFile());
            r.setDesc(arr[5]);
            r.setHeartCondition(Boolean.valueOf(arr[7]));
            r.setAllergy(Boolean.valueOf(arr[8]));
            r.setDiabetes(Boolean.valueOf(arr[9]));
            r.setBloodPressure(Boolean.valueOf(arr[10]));
            return r;
        }
        catch (Exception e)
        {
            System.err.println("Exception occurred");
            //e.printStackTrace();
            return null;
        }
    }

    boolean deleteFile()
    {
        File file = new File(dir+folderName + fileName + ".csv");
        return file.delete();
    }

    Exception editFile(int index,String value)
    {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName);
            CSVReader reader = new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+folderName+"temp.csv");
            CSVWriter writer=new CSVWriter(fw);
            reader.readNext();
            String edit[]=reader.readNext();
            reader.close();
            fw.close();
            edit[index]=value;
            writer.writeNext(header);
            writer.writeNext(edit);
            writer.close();
            fw.close();
            File file=new File(dir+folderName+fileName);
            file.delete();
            File nFile=new File(dir+folderName+"temp.csv");
            nFile.renameTo(file);
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    Exception editFile(int index, boolean condition)
    {
        return editFile(index,condition+"");
    }

    Exception editFile(int index, double money)
    {
        return editFile(index,money+"");
    }

    Exception editFile(int index, int  age)
    {
        return editFile(index,age+"");
    }


}
