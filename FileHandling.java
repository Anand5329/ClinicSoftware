package ClinicSoftware;
import java.io.*;
import com.opencsv.*;

import java.util.*;
public class FileHandling {

    private String appointmentHeader[]={"Record","Date","Procedure","Price","LabWork","Slot","Prescription"};
    private String scheduleHeader[]={"Slot","Appointment"};
    private String recordHeader[]={"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String labHeader[]={"Sent Date","Received Date","Lab Name","Description"};
    private String prescriptionHeader[]={"Date","Medicine Name","Instruction"};

    void createRecordFile(Record patient)throws IOException
    {
        FileWriter fw=new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Records/"+patient.getName()+"\t"+patient.getPhone()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(recordHeader);
        String recordDetails[]={patient.getName(),""+patient.getPhone(),""+patient.getAge(),patient.getFirstDate(),patient.getLatestDate(),patient.getDesc(),patient.getMoney()+"",patient.getHeartCondition()+"",patient.getAllergy()+"",patient.getDiabetes()+"",patient.getBloodPressure()+""};
        writer.writeNext(recordDetails);
        writer.close();
    }

    void createLabFile(LabWork lab)throws IOException
    {
        FileWriter fw =new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Lab Work/"+lab.getPatientName()+"\t"+lab.getSentDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(labHeader);
        String labDetails[]={lab.getSentDate(),lab.getReceivedDate(),lab.getLabName(),lab.getWork()}
        writer.writeNext(labDetails);
        writer.close();
    }

    void createPrescriptionFile(Prescription prescription)throws IOException
    {
        FileWriter fw = new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Lab Work/" + prescription.getPatientName() + "\t" + prescription.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(prescriptionHeader);
        String prescriptionDetails[]={prescription.getDate(),prescription.getMedicines(),prescription.getInstruction()};
        writer.writeNext(prescriptionDetails);
        writer.close();
    }

    void createAppointmentFile(Appointment a)throws IOException
    {
        FileWriter fw=new FileWriter("C:/Anand/Code Projects!/Appointments/"+a.getPatient().getName()+"\t"+a.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(appointmentHeader);
        String[] dat={a.getPatient().getFileName(),a.getDate(),a.getProcedure(),""+a.getPrice(),a.getLab().getPatientName()+"\t"+a.getLab().getSentDate()+".csv",a.getTime().displaySlot(),a.getPrescription().getPatientName()+"\t"+a.getPrescription().getDate()+".csv"};
        writer.writeNext(dat);
        writer.close();
    }

    void createScheduleFile(Schedule s)throws IOException
    {
        FileWriter fw=new FileWriter("C:/Anand/Code Projects!/Schedules/"+s.getDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(scheduleHeader);
        writer.close();
    }

}
