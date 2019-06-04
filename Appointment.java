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
//TODO: make add appointment window
    public Appointment(Record patient, String date, String procedure, double price, Slot time) {
        this.patient = patient;
        this.date = date;
        this.procedure = procedure;
        this.price = price;
        patient.updateMoney(price);
        paid=0;
        if(time!=null)
            this.time=time;
        else
            this.time=new Slot();
        lab=new LabWork();
        prescription = new Prescription();
    }

    public Appointment() {
        this(null, "", "", 0,null);
    }

    public Appointment(Record patient, String date,Slot t1) {
        this(patient, date, "", 0,t1);
    }

    public Record getRecord() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getProcedure()
    {
        return procedure;
    }

    public double getPrice()
    {
        return price;
    }

    public double getPaid()
    {
        return paid;
    }

    public double getPending()
    {
        return (price-paid);
    }

    public Slot getTime()
    {
        return time;
    }

    public String getFileName(){return patient.getName()+" "+getDate();}

    public void setProcedure(String procedure)
    {
        this.procedure=procedure;
    }

    public void setPrice(double price)
    {
        this.price=price;
        patient.updateMoney(price);
    }

    public void setPaid(double paid)
    {
        this.paid=paid;
        patient.pay(paid);
    }

    public void setTime(Slot time)
    {
        this.time=time;
    }

    public String displayAppointment()
    {
        String s=patient.getName()+"\t\t\t"+procedure;
        return s;
    }

    public void setLab(String sentDate,String receivedDate,String labName,String work)
    {
        lab.setSentDate(sentDate);
        lab.setReceivedDate(receivedDate);
        lab.setLabName(labName);
        lab.setWork(work);
        lab.setPatientName(patient.getName());
    }
    public void setLab(LabWork lab)
    {
        this.lab=lab;
    }

    public void setPrescription(String date)
    {
        prescription.setDate(date);
        prescription.setPatientName(patient.getName());
    }

    public void setPrescription(Prescription p)
    {
        this.prescription=p;
    }

    public LabWork getLab()
    {
        return lab;
    }

    public Prescription getPrescription()
    {
        return prescription;
    }

    public void display()
    {
        patient.display();
        System.out.println("\n"+date+"\n"+time.displaySlot());
        prescription.display();
    }
}