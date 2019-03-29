package ClinicSoftware;
import java.io.*;
import com.opencsv.*;
import java.util.*;
public class FileHandling
{
    private String recordHeader[]={"Name","Phone No.","Age","First Date","Latest Date","Description","Money","Heart Condition","Allergy","Diabetes","Blood Pressure"};
    private String labHeader[]={"Sent Date","Received Date","Lab Name","Description"};
    private String prescriptionHeader[]={"Date","Medicine Name","Instruction"};
    void createRecordFile(Record patient)throws IOException
    {
        FileWriter fw=new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Records/"+patient.getName()+"\t"+patient.getPhone()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(recordHeader);
        writer.writeNext(patient.getName(),patient.getPhone(),patient.getAge(),patient.getFirstDate(),patient.getLatestDate(),patient.getDesc(),patient.getMoney(),patient.getHeartCondition(),patient.getAllergy(),patient.getDiabetes(),patient.getBloodPressure())
        writer.close();
    }

    void createLabFile(LabWork lab)throws IOException
    {
        FileWriter fw =new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Lab Work/"+lab.getPatientName()+"\t"+lab.getSentDate()+".csv");
        CSVWriter writer=new CSVWriter(fw);
        writer.writeNext(labHeader);
        writer.writeNext(lab.getSentDate(),lab.getReceivedDate(),lab.getLabName(),lab.getWork());
        writer.close();
    }

    void createPrescriptionFile(Prescription prescription)throws IOException
    {
        FileWriter fw = new FileWriter("D:/Java-Blue J/src/ClinicSoftware/Lab Work/" + prescription.getPatientName() + "\t" + prescription.getDate() + ".csv");
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(prescriptionHeader);
        writer.writeNext(prescription.getDate(),prescription.getMedicines(),prescription.getInstruction());
        writer.close();
    }
}
