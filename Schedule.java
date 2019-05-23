package ClinicSoftware;
import java.util.*;
public class Schedule
{
    LinkedList<Slot> time;
    LinkedList<Appointment> patients;
    String date;
    int patient_counter;

    public Schedule(String date)
    {
        this.date=date;
        this.time=new LinkedList<Slot>();
        this.patients=new LinkedList<Appointment>();
        patient_counter=0;
    }

    public Schedule()
    {
        this("");
    }

    public String getDate()
    {
        return date;
    }

    public String getFileName(){ return date;}

    public int getPatientCounter()
    {
        return patient_counter;
    }

    public LinkedList<Slot> getSlots()
    {
        return time;
    }

    public LinkedList<Appointment> getAppointments()
    {
        return patients;
    }

    public void add(Appointment a)
    {
        addTime(a.getTime());
        patients.add(a);
        patient_counter++;
    }

    public void addTime(Slot s)
    {
        if(s.getStartTime()==0&&!time.isEmpty())
        {
            s.setStartTime(time.getLast().getEndTime());
        }
        time.add(s);
    }

    public void display()
    {
        System.out.println("Schedule for "+date+"\n");
        System.out.println("Time Slot\t\t\tPatient\t\t\tProcedure");
        for(int i=0;i<time.size();i++)
        {
            System.out.println(time.get(i).displaySlot()+"\t\t\t"+patients.get(i).displayAppointment());
        }
    }

    public void addBreak()
    {
        Record b=new Record("Break","");
        Appointment Break=new Appointment(b,"",null);
        add(Break);
    }

    public void remove(Appointment patient)
    {
        int index=patients.indexOf(patient);
        patients.remove(patient);
        time.remove(index);
    }

    public void remove(Slot time)
    {
        int index=this.time.indexOf(time);
        this.time.remove(time);
        patients.remove(index);
    }
}