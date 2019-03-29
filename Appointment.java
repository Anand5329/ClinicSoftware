package ClinicSoftware;

public class Appointment {
    Record patient;
    String date;
    String procedure;
    double price;
    Slot time;

    Appointment(Record patient, String date, String procedure, double price, Slot time) {
        this.patient = patient;
        this.date = date;
        this.procedure = procedure;
        this.price = price;
        if(time!=null)
            this.time=time;
        else
            this.time=new Slot();
    }

    Appointment() {
        this(null, "", "", 0,null);
    }

    Appointment(Record patient, String date) {
        this(patient, date, "", 0,null);
    }

    Record getPatient() {
        return patient;
    }

    String getDate() {
        return date;
    }

    String getProcedure()
    {
        return procedure;
    }

    double getPrice()
    {
        return price;
    }

    Slot getTime()
    {
        return time;
    }
    void setProcedure(String procedure)
    {
        this.procedure=procedure;
    }

    void setPrice(double price)
    {
        this.price=price;
    }

    void setTime(Slot time)
    {
        this.time=time;
    }

    String displayAppointment()
    {
        String s=patient.getName()+"\t\t\t"+procedure;
        return s;
    }

}
