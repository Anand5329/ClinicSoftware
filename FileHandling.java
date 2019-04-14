package ClinicSoftware;
import java.io.*;
import com.opencsv.*;

import java.util.*;
public class FileHandling {

    private String appointmentHeader[]={"Record","Date","Procedure","Price","LabWork","Slot","Prescription"};
    private String scheduleHeader[]={"Slot","Appointment"};
    private String recordHeader[]={"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String labHeader[]={"Patient Name","Sent Date","Received Date","Lab Name","Description"};
    private String prescriptionHeader[]={"Date","Medicine Name","Instruction"};
    private String dir="C:/Anand/Code Projects!/Directories/";

    String recordFolder="Records/";
    String appointmentFolder="Appointments/";
    String schedulesFolder="Schedules/";
    String labFolder="Lab Work/";
    String prescriptionFolder="Prescriptions/";

    String getDirectory()
    {
        return dir;
    }

    void setDirectory(String dir)
    {
        this.dir=dir;
    }

    void createRecordFile(Record patient)throws IOException
    {
        FileWriter fw=new FileWriter(dir+recordFolder+patient.getName()+" "+patient.getPhone()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(recordHeader);
        String recordDetails[]={patient.getName(),""+patient.getPhone(),""+patient.getAge(),patient.getFirstDate(),patient.getLatestDate(),patient.getDesc(),patient.getMoney()+"",patient.getHeartCondition()+"",patient.getAllergy()+"",patient.getDiabetes()+"",patient.getBloodPressure()+""};
        writer.writeNext(recordDetails);
        writer.close();
        fw.close();
    }

    void createLabFile(LabWork lab)throws IOException
    {
        FileWriter fw =new FileWriter(dir+labFolder+lab.getPatientName()+" "+lab.getSentDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(labHeader);
        String labDetails[]={lab.getPatientName(),lab.getSentDate(),lab.getReceivedDate(),lab.getLabName(),lab.getWork()};
        writer.writeNext(labDetails);
        writer.close();
        fw.close();
    }

    void createPrescriptionFile(Prescription prescription)throws IOException
    {
        FileWriter fw = new FileWriter(dir+prescriptionFolder + prescription.getPatientName() + " " + prescription.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(prescriptionHeader);
        String prescriptionDetails[]={prescription.getDate()};

        writer.writeNext(prescriptionDetails);
        for(int i=0;i<prescription.getMedicines().size();i++)
        {
            String temp[]={"",prescription.getMedicines().get(i),prescription.getInstruction().get(i)};
            writer.writeNext(temp);
        }
        writer.close();
        fw.close();
    }

    void createAppointmentFile(Appointment a)throws IOException
    {
        FileWriter fw=new FileWriter(dir+appointmentFolder+a.getPatient().getName()+" "+a.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(appointmentHeader);
        String[] dat={a.getPatient().getFileName(),a.getDate(),a.getProcedure(),""+a.getPrice(),a.getLab().getPatientName()+" "+a.getLab().getSentDate()+".csv",a.getTime().displaySlot(),a.getPrescription().getPatientName()+" "+a.getPrescription().getDate()+".csv"};
        writer.writeNext(dat);
        writer.close();
        fw.close();
    }

    void createScheduleFile(Schedule s)throws IOException
    {
        FileWriter fw=new FileWriter(dir+schedulesFolder+s.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(scheduleHeader);
        for(int i=0;i<s.getSlots().size();i++)
        {
            String entry[]={s.getSlots().get(i).displaySlot(),appointmentFolder+s.getAppointments().get(i).getPatient().getName()+" "+s.getAppointments().get(i).getPatient().getPhone()+".csv"};
            writer.writeNext(entry);
        }
        writer.close();
        fw.close();
    }

    Record readRecordFile(String name,long phone)throws IOException
    {
        String fileName=name+" "+phone+".csv";
        FileReader fr=new FileReader(dir+recordFolder+fileName);
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        String arr[]=reader.readNext();
        Record r=new Record(arr[0],Long.valueOf(arr[1]));
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

    Record readRecordFile(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+recordFolder+fileName);
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        String arr[]=reader.readNext();
        Record r=new Record(arr[0],Long.valueOf(arr[1]));
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

    Appointment readAppointmentFile(String name, String date)throws IOException
    {
        String fileName=name+" "+date+".csv";
        return readAppointmentFile(fileName);
    }

    Appointment readAppointmentFile(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+appointmentFolder+fileName);
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        String arr[]=reader.readNext();
        Slot s=new Slot(Double.valueOf(arr[5].split("-")[0]));
        Appointment a=new Appointment(readRecordFile(arr[0]),arr[1],s);
        a.setPrice(Double.valueOf(arr[3]));
        a.setLab(readLabWorkFile(arr[4]));
        a.setProcedure(arr[2]);
        a.setPrescription(readPrescriptionFile(arr[6]));
        return a;
    }

    LabWork readLabWorkFile(String patientName,String sentDate)throws IOException
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

    LabWork readLabWorkFile(String fileName)throws IOException
    {
       return readLabWorkFile(fileName.split(" ")[0],fileName.split(" ")[1]);
    }

    Prescription readPrescriptionFile(String patientName,String date)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+patientName+" "+date+".csv");
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        String nameDate[]=reader.readNext();
        Prescription pre=new Prescription();
        pre.setPatientName(patientName);
        pre.setDate(date);
        String temp[];
        while((temp=reader.readNext())!=null)
        {
            pre.addMedicine(temp[2]);
            pre.addInstruction(temp[3]);
        }
        return pre;
    }

    Prescription readPrescriptionFile(String fileName)throws IOException
    {
        return readPrescriptionFile(fileName.split(" ")[0],fileName.split(" ")[1]);
    }

    Schedule readScheduleFile(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName);
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        List<String[]> list=reader.readAll();
        Schedule sc=new Schedule(0,fileName);
        for(String s[] : list)
        {
            String time[]=s[0].split(" - ");
            Slot sl=new Slot(Double.valueOf(time[1])-Double.valueOf(time[0]),Double.valueOf(time[0]));
            Appointment a=readAppointmentFile(dir+s[1]);
            sc.addTime(sl);
            sc.add(a);
        }
        return sc;
    }

}
