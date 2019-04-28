package ClinicSoftware;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.List;

public class ScheduleFile extends ClinicFile {
    private String dir = "C:/Anand/Code Projects!/Directories/";
    private String folderName = "Schedules/";
    private String[] header = {"Slot", "Appointment"};
    private String fileName = "";

    ScheduleFile(Schedule sch) throws IOException {
        Exception e=null;
        fileName = sch.getFileName();
        if(!isFilePresent(dir,folderName,fileName))
            if((e=createFile(sch))==null)
                System.out.println("File created successfully");
            else
                e.printStackTrace();
    }

    ScheduleFile(String fileName)
    {
        this.fileName=fileName;
    }


    Exception createFile(Schedule s) {
        try {
            FileWriter fw = new FileWriter(dir + folderName + s.getDate() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(header);
            for (int i = 0; i < s.getSlots().size(); i++) {
                String entry[] = {s.getSlots().get(i).displaySlot(), s.getAppointments().get(i).getRecord().getName() + " " + s.getAppointments().get(i).getDate() + ".csv"};
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

    Schedule readFile() throws IOException {
        FileReader fr = new FileReader(dir + folderName + fileName + ".csv");
        CSVReader reader = new CSVReader(fr);
        reader.readNext();
        List<String[]> list = reader.readAll();
        Schedule sc = new Schedule(fileName);
        for (String s[] : list) {
            String time[] = s[0].split(" - ");
            Slot sl = new Slot(Double.valueOf(time[1]) - Double.valueOf(time[0]), Double.valueOf(time[0]));
            AppointmentFile af=new AppointmentFile("");
            Appointment a = af.readFile();
            sc.addTime(sl);
            sc.add(a);
        }
        return sc;
    }

    boolean deleteFile() {
        File file = new File(dir + folderName + fileName + ".csv");
        return file.delete();
    }

    Exception deleteEntry(Slot slot) {
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
            reader.close();
            writer.close();
            fr.close();
            fw.close();
            File file = new File(dir + folderName + fileName + ".csv");
            File nFile = new File(dir + folderName + "temp.csv");
            if (!deleteFile())
                System.out.println("Failed1");
            if (!nFile.renameTo(file))
                System.out.println("Failed2");
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    Exception addEntry(Appointment a, Slot s)
    {
        try{
            FileReader fr=new FileReader(dir+folderName+fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            String[] temp;
            reader.readNext();
            FileWriter fw=new FileWriter(dir+folderName+"temp2.csv");
            CSVWriter writer=new CSVWriter(fw);
            writer.writeNext(header);
            while((temp=reader.readNext())!=null)
            {
                Slot e=new Slot();
                Slot n=e.toSlot(temp[0]);
                String next[]=reader.readNext();
                boolean isWritten=false;
                if(next!=null) {
                    Slot m = e.toSlot(next[0]);
                    writer.writeNext(temp);
                    if (s.isGreater(n) && m.isGreater(s)){
                        String slot[] = {s.displaySlot(), a.getFileName()};
                        writer.writeNext(slot);
                        isWritten=true;
                    }
                    writer.writeNext(next);
                    if(!isWritten) {
                        String slot[] = {s.displaySlot(), a.getFileName()};
                        writer.writeNext(slot);
                    }
                }
            }
            reader.close();
            writer.close();
            fr.close();
            fw.close();
            File f=new File(dir+folderName+fileName+".csv");
            File f2=new File(dir+folderName+"temp2.csv");
            f.delete();
            f2.renameTo(f);
            File f3=new File(dir+folderName+"temp2.csv");
            f3.delete();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    Exception editEntry(Slot s, Appointment newA)throws IOException
    {
        Exception e=null;
        if((e=deleteEntry(s))==null)
            if((e=addEntry(newA, s))==null)
            {
                newA.setTime(s);
                return e;
            }
            else
                return e;
        else
            return e;
    }
}
