package ClinicSoftware;

import java.util.LinkedList;

public class Prescription
{
    private String date,patientName;
    private LinkedList<String> medicines;
    private LinkedList<String> instruction;

    Prescription(String date,String patientName)
    {
        this.date=date;
        this.patientName=patientName;
        medicines=new LinkedList<>();
        instruction=new LinkedList<>();
    }

    Prescription()
    {
        this("","");
    }

    String getDate()
    {
        return date;
    }

    String getPatientName() {return patientName;}

    String getFileName(){return patientName+" "+date;}

    LinkedList<String> getMedicines()
    {
        return medicines;
    }

    LinkedList<String> getInstruction()
    {
        return instruction;
    }

    void setDate(String date)
    {
        this.date=date;
    }

    void setPatientName(String patientName) {this.patientName=patientName;}

    void addMedicine(String medicine)
    {
        medicines.add(medicine);
    }

    void addInstruction(String instruction)
    {
        this.instruction.add(instruction);
    }

    void remove(String medicine)
    {
        int index=medicines.indexOf(medicine);
        medicines.remove(medicine);
        instruction.remove(index);
    }

    void display()
    {
        int i=1,j=1;
        System.out.println("Patient Name: "+getPatientName());
        System.out.println("Date: "+getDate());
        System.out.println("Medicines\t\tInstruction");
        for(int a=0;a<getMedicines().size();a++)
        System.out.println(getMedicines().get(a)+"\t\t"+getInstruction().get(a));
    }



}
