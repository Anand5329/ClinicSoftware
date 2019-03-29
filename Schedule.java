package ClinicSoftware;
import java.util.*;
public class Schedule
{
    int no_of_appointments;
    LinkedList<Slot> time;
    LinkedList<Appointment> patients;
    String date;
    int patient_counter;

    Schedule(int no_of_appointments, String date)
    {
        this.no_of_appointments=no_of_appointments;
        this.date=date;
        this.time=new LinkedList<Slot>();
        this.patients=new LinkedList<Appointment>();
        patient_counter=0;
    }

    Schedule()
    {
        this(0,"");
    }

    int getNoOfAppointments()
    {
        return no_of_appointments;
    }

    String getDate()
    {
        return date;
    }

    int getPatientCounter()
    {
        return patient_counter;
    }

    LinkedList<Slot> getSlots()
    {
        return time;
    }

    LinkedList<Appointment> getAppointments()
    {
        return patients;
    }

    void add(Appointment a)
    {
        addTime(a.getTime());
        patients.add(a);
        patient_counter++;
    }

    void addTime(Slot s)
    {
        if(s.getStartTime()==0&&!time.isEmpty())
        {
            s.setStartTime(time.getLast().getEndTime());
        }
        time.add(s);
    }

    void display()
    {
        System.out.println("Schedule for "+date+"\n");
        System.out.println("Time Slot\t\t\tPatient\t\t\tProcedure");
        for(int i=0;i<time.size();i++)
        {
            System.out.println(time.get(i).displaySlot()+"\t\t\t"+patients.get(i).displayAppointment());
        }
    }

    void addBreak()
    {
        Record b=new Record("Break",0);
        Appointment Break=new Appointment(b,"");
        add(Break);
    }

    void remove(Appointment patient)
    {
        int index=patients.indexOf(patient);
        patients.remove(patient);
        time.remove(index);
    }

    void remove(Slot time)
    {
        int index=this.time.indexOf(time);
        this.time.remove(time);
        patients.remove(index);
    }
}