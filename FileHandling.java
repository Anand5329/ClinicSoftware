package ClinicSoftware;
import java.io.*;
import com.opencsv.*;

import java.util.*;
public class FileHandling {

    private String appointmentHeader[]={"Record","Date","Procedure","Price","LabWork","Slot","Prescription"};
    private String scheduleHeader[]={"Slot","Appointment"};
    private String recordHeader[]={"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String labHeader[]={"Sent Date","Received Date","Lab Name","Description","Patient Name"};
    private String prescriptionHeader[]={"Patient Name","Date","Medicine Name","Instruction"};
    private String dir="D:/Java-Blue J/src/ClinicSoftware/";

    void createRecordFile(Record patient)throws IOException
    {
        FileWriter fw=new FileWriter(dir+"Records/"+patient.getName()+" "+patient.getPhone()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(recordHeader);
        String recordDetails[]={patient.getName(),""+patient.getPhone(),""+patient.getAge(),patient.getFirstDate(),patient.getLatestDate(),patient.getDesc(),patient.getMoney()+"",patient.getHeartCondition()+"",patient.getAllergy()+"",patient.getDiabetes()+"",patient.getBloodPressure()+""};
        writer.writeNext(recordDetails);
        writer.close();
        fw.close();
    }

    void createLabFile(LabWork lab)throws IOException
    {
        FileWriter fw =new FileWriter(dir+"Lab Work/"+lab.getPatientName()+" "+lab.getSentDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(labHeader);
        String labDetails[]={lab.getSentDate(),lab.getReceivedDate(),lab.getLabName(),lab.getWork(),lab.getPatientName()};
        writer.writeNext(labDetails);
        writer.close();
        fw.close();
    }

    void createPrescriptionFile(Prescription prescription)throws IOException
    {
        FileWriter fw = new FileWriter(dir+"Prescriptions/" + prescription.getPatientName() + " " + prescription.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(prescriptionHeader);
        String prescriptionDetails[]={prescription.getPatientName(),prescription.getDate()};

        writer.writeNext(prescriptionDetails);
        for(int i=0;i<prescription.getMedicines().size();i++)
        {
            String temp[]={"","",prescription.getMedicines().get(i),prescription.getInstruction().get(i)};
            writer.writeNext(temp);
        }
        writer.close();
        fw.close();
    }

    void createAppointmentFile(Appointment a)throws IOException
    {
        FileWriter fw=new FileWriter(dir+"Appointments/"+a.getPatient().getName()+" "+a.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(appointmentHeader);
        String[] dat={a.getPatient().getFileName(),a.getDate(),a.getProcedure(),""+a.getPrice(),a.getLab().getPatientName()+" "+a.getLab().getSentDate()+".csv",a.getTime().displaySlot(),a.getPrescription().getPatientName()+" "+a.getPrescription().getDate()+".csv"};
        writer.writeNext(dat);
        writer.close();
        fw.close();
    }

    void createScheduleFile(Schedule s)throws IOException
    {
        FileWriter fw=new FileWriter(dir+"Schedules/"+s.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(scheduleHeader);
        for(int i=0;i<s.getSlots().size();i++)
        {
            String entry[]={s.getSlots().get(i).displaySlot(),"Appointments/"+s.getAppointments().get(i).getPatient().getName()+" "+s.getAppointments().get(i).getDate()+".csv"};
            writer.writeNext(entry);
        }
        writer.close();
        fw.close();
    }

    LabWork getLabWorkFile(String patientName,String sentDate)throws IOException
    {
        FileReader fr=new FileReader(dir+"Lab Work/"+patientName+" "+sentDate+".csv");
        CSVReader reader=new CSVReader(fr);
        String LabWork[]=new String[1];
        //while(reader.readNext()!=labHeader)
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

    Prescription getPrescriptionFile(String patientName,String date)throws IOException
    {
       FileReader fr=new FileReader(dir+"Prescriptions/"+patientName+" "+date+".csv");
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


}