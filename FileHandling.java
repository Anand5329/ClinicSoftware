package ClinicSoftware;
import java.io.*;
import com.opencsv.*;

import java.util.*;
public class FileHandling {

    private String appointmentHeader[]={"Record","Date","Procedure","Price","LabWork","Slot","Prescription"};
    private String scheduleHeader[]={"Slot","Appointment"};
    private String recordHeader[]={"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String labHeader[]={"Patient Name","Sent Date","Received Date","Lab Name","Description"};
    private String prescriptionHeader[]={"Medicine Name","Instruction"};

    private String dir = "C:/Anand/Code Projects!/Directories/";

    String recordFolder = "Records/";
    String appointmentFolder = "Appointments/";
    String schedulesFolder = "Schedules/";
    String labFolder = "Lab Work/";
    String prescriptionFolder = "Prescriptions/";

    String getDirectory() {
        return dir;
    }

    void setDirectory(String dir) {
        this.dir = dir;
    }

    void createRecordFile(Record patient) throws IOException {
        FileWriter fw = new FileWriter(dir + recordFolder + patient.getName() + " " + patient.getPhone() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(recordHeader);
        String recordDetails[] = {patient.getName(), "" + patient.getPhone(), "" + patient.getAge(), patient.getFirstDate(), patient.getLatestDate(), patient.getDesc(), patient.getMoney() + "", patient.getHeartCondition() + "", patient.getAllergy() + "", patient.getDiabetes() + "", patient.getBloodPressure() + ""};
        writer.writeNext(recordDetails);
        writer.close();
        fw.close();
    }

    void createLabFile(LabWork lab) throws IOException {
        FileWriter fw = new FileWriter(dir + labFolder + lab.getPatientName() + " " + lab.getSentDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(labHeader);
        String labDetails[] = {lab.getPatientName(), lab.getSentDate(), lab.getReceivedDate(), lab.getLabName(), lab.getWork()};
        writer.writeNext(labDetails);
        writer.close();
        fw.close();
    }

    void createPrescriptionFile(Prescription prescription) throws IOException {
        FileWriter fw = new FileWriter(dir + prescriptionFolder + prescription.getFileName() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(prescriptionHeader);
        //String prescriptionDetails[]={prescription.getDate()};

        //writer.writeNext(prescriptionDetails);
        for(int i=0;i<prescription.getMedicines().size();i++)
        {
            String temp[]={prescription.getMedicines().get(i),prescription.getInstruction().get(i)};
            writer.writeNext(temp);
        }
        writer.close();
        fw.close();
    }

    void createAppointmentFile(Appointment a) throws IOException {
        FileWriter fw = new FileWriter(dir + appointmentFolder + a.getRecord().getName() + " " + a.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(appointmentHeader);
        String[] dat = {a.getRecord().getFileName(), a.getDate(), a.getProcedure(), "" + a.getPrice(), a.getLab().getPatientName() + " " + a.getLab().getSentDate(), a.getTime().displaySlot(), a.getPrescription().getPatientName() + " " + a.getPrescription().getDate()};
        writer.writeNext(dat);
        writer.close();
        fw.close();
    }

    void createScheduleFile(Schedule s) throws IOException {
        FileWriter fw = new FileWriter(dir + schedulesFolder + s.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(scheduleHeader);
        for (int i = 0; i < s.getSlots().size(); i++) {
            String entry[] = {s.getSlots().get(i).displaySlot(), s.getAppointments().get(i).getRecord().getName() + " " + s.getAppointments().get(i).getDate() + ".csv"};
            writer.writeNext(entry);
        }
        writer.close();
        fw.close();
    }

    Record readRecordFile(String name, long phone) throws IOException {
        String fileName = name + " " + phone;
        return readRecordFile(fileName);
    }

    Record readRecordFile(String fileName) throws IOException {
        FileReader fr = new FileReader(dir + recordFolder + fileName + ".csv");
        CSVReader reader = new CSVReader(fr);
        reader.readNext();
        String arr[] = reader.readNext();
        Record r = new Record(arr[0], Long.valueOf(arr[1]));
        r.updateMoney(Double.valueOf(arr[6]));
        r.setAge(Integer.valueOf(arr[2]));
        r.setFirstDate(arr[3]);
        r.setLatestDate(arr[4]);
        r.setDesc(arr[5]);
        r.setHeartCondition(Boolean.valueOf(arr[7]));
        r.setAllergy(Boolean.valueOf(arr[8]));
        r.setDiabetes(Boolean.valueOf(arr[9]));
        r.setBloodPressure(Boolean.valueOf(arr[10]));
        return r;
    }

    Appointment readAppointmentFile(String name, String date) throws IOException {
        String fileName = name + " " + date;
        return readAppointmentFile(fileName);
    }

    Appointment readAppointmentFile(String fileName) throws IOException {
        FileReader fr = new FileReader(dir + appointmentFolder + fileName + ".csv");
        CSVReader reader = new CSVReader(fr);
        reader.readNext();
        String arr[] = reader.readNext();
        Slot s = new Slot(Double.valueOf(arr[5].split("-")[0]));
        Appointment a = new Appointment(readRecordFile(arr[0]), arr[1], s);
        a.setPrice(Double.valueOf(arr[3]));
        a.setLab(readLabWorkFile(arr[4]));
        a.setProcedure(arr[2]);
        a.setPrescription(readPrescriptionFile(arr[6]));
        return a;
    }

    LabWork readLabWorkFile(String patientName, String sentDate) throws IOException {
        FileReader fr = new FileReader(dir + labFolder + patientName + " " + sentDate + ".csv");
        CSVReader reader = new CSVReader(fr);
        String LabWork[];
        reader.readNext();

        LabWork = reader.readNext();

        LabWork lab = new LabWork();
        lab.setPatientName(patientName);
        lab.setSentDate(sentDate);
        lab.setReceivedDate(LabWork[1]);
        lab.setLabName(LabWork[2]);
        lab.setWork(LabWork[3]);
        return lab;
    }

    LabWork readLabWorkFile(String fileName) throws IOException {
        if (!fileName.equals(" "))
            return readLabWorkFile(fileName.split(" ")[0], fileName.split(" ")[1]);
        else
            return null;
    }

    Prescription readPrescriptionFile(String patientName, String date) throws IOException {
        FileReader fr = new FileReader(dir + prescriptionFolder + patientName + " " + date + ".csv");
        CSVReader reader = new CSVReader(fr);
        reader.readNext();
        String nameDate[] = reader.readNext();
        Prescription pre = new Prescription();
        pre.setPatientName(patientName);
        pre.setDate(date);
        String temp[];
        while((temp=reader.readNext())!=null)
        {
            pre.addMedicine(temp[0]);
            pre.addInstruction(temp[1]);
        }
        return pre;
    }

    Prescription readPrescriptionFile(String fileName) throws IOException {
        if (!fileName.equals(" "))
            return readPrescriptionFile(fileName.split(" ")[0], fileName.split(" ")[1]);
        else
            return null;
    }

    Schedule readScheduleFile(String fileName) throws IOException {
        FileReader fr = new FileReader(dir + schedulesFolder + fileName + ".csv");
        CSVReader reader = new CSVReader(fr);
        reader.readNext();
        List<String[]> list = reader.readAll();
        Schedule sc = new Schedule(fileName);
        for (String s[] : list) {
            String time[] = s[0].split(" - ");
            Slot sl = new Slot(Double.valueOf(time[1]) - Double.valueOf(time[0]), Double.valueOf(time[0]));
            Appointment a = readAppointmentFile(s[1]);
            sc.addTime(sl);
            sc.add(a);
        }
        return sc;
    }

    boolean deleteScheduleFile(String fileName) {
        File file = new File(dir + schedulesFolder + fileName + ".csv");
        return file.delete();
    }

    boolean deleteAppointment(String fileName) {
        File file = new File(dir + appointmentFolder + fileName + ".csv");
        return file.delete();
    }

    boolean deleteAppointment(String name, String date) {
        String fileName = name + " " + date;
        return deleteAppointment(fileName);
    }


    boolean deletePrescriptionFile(String fileName)
    {
        File file=new File(dir+prescriptionFolder+fileName+".csv");
       return file.delete();
    }

    boolean deleteLabWorkFile(String fileName)
    {
        File file =new File("D:\\Java-Blue J\\src\\ClinicSoftware\\Lab Work\\"+fileName+".csv");
        return file.delete();
    }

    boolean deleteRecordFile(String fileName)
    {
        File file = new File("D:\\Java-Blue J\\src\\ClinicSoftware\\Records\\" + fileName + ".csv");
        return file.delete();
    }

    boolean deletePrescriptionFile(String patientName,String date)
        {
        String fileName=patientName+" "+date;
        return deletePrescriptionFile(fileName);
    }

    boolean deleteLabWorkFile(String patientName,String sentDate)
    {
        String fileName=patientName+" "+sentDate;
        return deleteLabWorkFile(fileName);
    }

    boolean deleteRecordFile(String patientName,long phone)
    {
        String fileName=patientName+" "+phone;
        return deletePrescriptionFile(fileName);
    }

    boolean deleteScheduleEntry(String fileName, Slot slot) throws IOException {
        try {
            FileReader fr = new FileReader(dir + schedulesFolder + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            FileWriter fw = new FileWriter(dir + schedulesFolder + "temp.csv");
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
            File file = new File(dir + schedulesFolder + fileName + ".csv");
            File nFile = new File(dir + schedulesFolder + "temp.csv");
            if (!deleteScheduleFile(fileName))
                System.out.println("Failed1");
            if (!nFile.renameTo(file))
                System.out.println("Failed2");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean addScheduleEntry(String fileName, Appointment a, Slot s)
    {
        try{
        FileReader fr=new FileReader(dir+schedulesFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String[] temp;
        reader.readNext();
        FileWriter fw=new FileWriter(dir+schedulesFolder+"temp2.csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(scheduleHeader);
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
        File f=new File(dir+schedulesFolder+fileName+".csv");
        File f2=new File(dir+schedulesFolder+"temp2.csv");
        f.delete();
        f2.renameTo(f);
        File f3=new File(dir+schedulesFolder+"temp2.csv");
        f3.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean deletePrescriptionEntry(String fileName,int index)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        FileWriter fw=new FileWriter(dir+prescriptionFolder+"temp.csv");
        CSVWriter writer=new CSVWriter(fw);
        int ctr=0;
        String temp2[];
        while((temp2=reader.readNext())!=null)
        {
            if(ctr!=index) {
                writer.writeNext(temp2);
            }
            ctr++;
        }
        boolean flag=true;
        reader.close();
        writer.close();
        fr.close();
        fw.close();
        File file=new File(dir+prescriptionFolder+fileName+".csv");
        File nFile=new File(dir+prescriptionFolder+"temp.csv");
        if(!deletePrescriptionFile(fileName))
            flag=false;
        if(!nFile.renameTo(file))
            flag=false;
        return flag;
    }

    boolean deletePrescriptionEntry(String patientName,String date,int index)throws IOException
    {
        String fileName=patientName+" "+date;
        return deletePrescriptionEntry(fileName,index);
    }

    boolean addPrescriptionEntry(String fileName,String medicine,String instruction)throws IOException
    {
        boolean flag =true;
        try {
            FileWriter fw = new FileWriter(dir+prescriptionFolder+"temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            String temp[];
            while((temp=reader.readNext())!=null)
            {
                writer.writeNext(temp);
            }
            String medicinesArr[] = {medicine, instruction};
            writer.writeNext(medicinesArr);
            writer.close();
            fw.close();
            reader.close();
            fr.close();
            File file=new File(dir+prescriptionFolder+fileName+".csv");
            if(!file.delete())
                flag=false;
            File temp2=new File(dir+prescriptionFolder+"temp.csv");
            temp2.renameTo(file);
        }
        catch(Exception e)
        {
            flag=false;
        }
        return flag;
    }

    boolean addPrescriptionEntry(String patientName,String date,String medicine,String instruction)throws IOException
    {
        String fileName=patientName+" "+date;
        return addPrescriptionEntry(fileName,medicine,instruction);
    }

    boolean editScheduleEntry(String date, Slot s, Appointment newA)throws IOException
    {
        boolean flag=false;
        if(deleteScheduleEntry(date, s))
            if(addScheduleEntry(date, newA, s))
                flag=true;
        return flag;
    }

    boolean addPrescriptionEntry(String fileName,String medicine,String instruction,int predecessorIndex)throws IOException
    {
        boolean flag =true;
        //try
        {
            FileWriter fw = new FileWriter(dir+prescriptionFolder+"temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            String temp[];
            int ctr=0;
            writer.writeNext(reader.readNext());
            String medicinesArr[] = {medicine, instruction};
            while(true)
            {
                temp=reader.readNext();
                if(ctr==predecessorIndex) {
                    writer.writeNext(medicinesArr);
                    ctr++;
                    writer.writeNext(temp);
                }
                else
                {
                    writer.writeNext(temp);
                    ctr++;
                }
                if(temp==null) {
                    if(ctr<predecessorIndex)
                        writer.writeNext(medicinesArr);
                    break;
                }
            }
            writer.close();
            fw.close();
            reader.close();
            fr.close();
            File file=new File(dir+prescriptionFolder+fileName+".csv");
            if(!file.delete())
                flag=false;
            File temp2=new File(dir+prescriptionFolder+"temp.csv");
            temp2.renameTo(file);
        }
        //catch(Exception e)
        {
            //flag=false;
        }
        return flag;
    }

    boolean addPrescriptionEntry(String patientName,String date,String medicine,String instruction,int index)throws IOException
    {
        String fileName=patientName+" "+date;
        return addPrescriptionEntry(fileName,medicine,instruction,index);
    }

    boolean editPrescriptionEntry(String fileName,String medDel,String medEnter,String instructionEnter)throws IOException
    {
        boolean flag=true;
        int index=getPrescriptionIndex(fileName,medDel);
        if(!deletePrescriptionEntry(fileName,index))
            flag=false;
        if(!addPrescriptionEntry(fileName,medEnter,instructionEnter))
            flag=false;
        return flag;
    }

    int getPrescriptionIndex(String fileName,String medicine)throws IOException
    {
        int ctr=1;
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[];
        reader.readNext();
        while((temp=reader.readNext())!=null)
        {
            if(temp[0].equals(medicine)) {
                reader.close();
                fr.close();
                return ctr;
            }
            else
                ctr++;
        }
        reader.close();
        fr.close();
        return ctr;
    }

    int getPrescriptionLastIndex(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        int ctr=0;
        reader.readNext();
        while(reader.readNext()!=null)
        {
            ctr++;
        }
        return ctr;
    }
}