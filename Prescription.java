package ClinicSoftware;

import java.util.LinkedList;

public class Prescription
{
    private String date;
    private LinkedList<String> medicines;
    private LinkedList<String> instruction;

    Prescription(String date)
    {
        this.date=date;
        medicines=new LinkedList<>();
        instruction=new LinkedList<>();
    }

    Prescription()
    {
        this("");
    }

    String getDate()
    {
        return date;
    }

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



}
