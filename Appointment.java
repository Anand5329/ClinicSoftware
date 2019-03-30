package ClinicSoftware;

public class Appointment {
    Record patient;
    String date;
    String procedure;
    double price;
    double paid;
    Slot time;
    LabWork lab;
    Prescription prescription;

    Appointment(Record patient, String date, String procedure, double price, Slot time) {
        this.patient = patient;
        this.date = date;
        this.procedure = procedure;
        this.price = price;
        paid=0;
        if(time!=null)
            this.time=time;
        else
            this.time=new Slot();
        lab=new LabWork();
        prescription = new Prescription();
    }

    Appointment() {
        this(null, "", "", 0,null);
    }

    Appointment(Record patient, String date,Slot t1) {
        this(patient, date, "", 0,t1);
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

    double getPaid()
    {
        return paid;
    }

    double getPending()
    {
        return (price-paid);
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

    void setPaid(double paid)
    {
        this.paid=paid;
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

    void setLab(String sentDate,String receivedDate,String labName,String work)
    {
        lab.setSentDate(sentDate);
        lab.setReceivedDate(receivedDate);
        lab.setLabName(labName);
        lab.setWork(work);
        lab.setPatientName(patient.getName());
    }
    void setLab(LabWork lab)
    {
        this.lab=lab;
    }

    void setPrescription(String date)
    {
        prescription.setDate(date);
        prescription.setPatientName(patient.getName());
    }

    LabWork getLab()
    {
        return lab;
    }

    Prescription getPrescription()
    {
        return prescription;
    }
}