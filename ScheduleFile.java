package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
//TODO: testing
public class ScheduleFile extends ClinicFile {
    private String dir = "C:/Anand/Code Projects!/Directories/";
    private String folderName = "Schedules/";
    private String[] header = {"Slot", "Appointment"};
    private String fileName = "";
    public int userSignature=0;

    public ScheduleFile(Schedule sch) throws IOException {
        Exception e=null;
        fileName = sch.getFileName();
        if(!isFilePresent(dir,folderName,fileName))
            if((e=createFile(sch))==null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
    }

    public ScheduleFile(String fileName)
    {
        this.fileName=fileName;
    }

    public void setUserSignature(int userSignature)
    {
        this.userSignature=userSignature;
    }

    public Exception createFile(Schedule s) {
        try {
            FileWriter fw = new FileWriter(dir + folderName + s.getDate() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(header);
            for (int i = 0; i < s.getSlots().size(); i++) {
                String entry[] = {s.getSlots().get(i).displaySlot(), s.getAppointments().get(i).getFileName()};
                writer.writeNext(entry);
            }
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public Schedule readFile() throws IOException {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            reader.readNext();
            List<String[]> list = reader.readAll();
            Schedule sc = new Schedule(fileName);
            if(list!=null) {
                for (String s[] : list) {
                    String time[] = s[0].split(" - ");
                    Slot sl = new Slot(Double.valueOf(time[1]) - Double.valueOf(time[0]), Double.valueOf(time[0]));
                    AppointmentFile af = new AppointmentFile(s[1]);
                    Appointment a = af.readFile();
                    if(a.getUserSignature()==userSignature) {
                        a.setTime(sl);
                        sc.add(a);
                    }

                }
            }
            return sc;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Exception occurred: File not found");
            //e.printStackTrace();
            return new Schedule();
        }
        catch(Exception e)
        {
            System.err.println("An unknown exception occurred:");
            e.printStackTrace();
            return new Schedule();
        }
    }

    public boolean deleteFile() {
        File file = new File(dir + folderName + fileName + ".csv");
        return file.delete();
    }

    public Exception deleteEntry(Slot slot) {
        try {
            FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            FileWriter fw = new FileWriter(dir + folderName + "temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(reader.readNext());
            String[] t;
            while ((t = reader.readNext()) != null) {
                if (!(Double.valueOf(t[0].split("-")[0])==slot.getStartTime()))
                    writer.writeNext(t);
            }
            writer.close();
            fw.close();
            reader.close();
            fr.close();
            try {
                System.gc();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            File file = new File(dir + folderName + fileName + ".csv");
            File nFile = new File(dir + folderName + "temp.csv");
            System.gc();
            if (!deleteFile())
                System.out.println("Failed1");
            if (!nFile.renameTo(file))
                System.out.println("Failed2");
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    public Exception addEntry(Appointment a)
    {
        Slot s=a.getTime();
        try{
            FileReader fr=new FileReader(dir+folderName+fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            FileWriter fw=new FileWriter(dir+folderName+"temp2.csv");
            CSVWriter writer=new CSVWriter(fw);
            writer.writeNext(header);
            LinkedList<String[]> data=new LinkedList(reader.readAll());
            boolean isWritten=false;
            String arr[]={s.displaySlot(),a.getFileName()};
            for(int i=1;i<data.size();i++)
            {
                Slot e=new Slot();
                Slot slot=e.toSlot(data.get(i)[0]);
                if(slot.isGreater(s)&&!isWritten)
                {
                    writer.writeNext(arr);
                    isWritten=true;
                }
                writer.writeNext(data.get(i));
            }
            if(!isWritten)
            {
                writer.writeNext(arr);
                isWritten=true;
            }
            reader.close();
            writer.close();
            fr.close();
            fw.close();
            File f=new File(dir+folderName+fileName+".csv");
            File f2=new File(dir+folderName+"temp2.csv");
            boolean err_flag=f.delete();
            System.out.println("Operation: "+err_flag);
            System.out.println(err_flag);
            System.out.println("Operation 2: "+f2.renameTo(f));
            File f3=new File(dir+folderName+"temp2.csv");
            f3.delete();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    public Exception editEntry(Appointment newAppointment)
    {
        Slot slot=newAppointment.getTime();
        Exception e=null;
        if((e=deleteEntry(slot))==null)
            if((e=addEntry(newAppointment))==null)
            {
                newAppointment.setTime(slot);
                return e;
            }
            else
                return e;
        else
            return e;
    }

    public Exception editEntry(Appointment a,Slot nSlot)throws IOException
    {
        Exception e=null;
        a.setTime(nSlot);
        Schedule schedule=readFile();
        if((e=deleteEntry(schedule.searchSlot(a)))==null)
            if((e=addEntry(a))==null)
            {
                return e;
            }
            else
                return e;
        else
            return e;
    }
}