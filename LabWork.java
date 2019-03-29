package ClinicSoftware;

public class LabWork
{
    private String sentDate, receivedDate, labName, work, patientName;

    LabWork(String sentDate,String receivedDate,String labName,String work, String patientName)
    {
        this.sentDate=sentDate;
        this.receivedDate=receivedDate;
        this.labName=labName;
        this.work=work;
        this.patientName=patientName;
    }

    LabWork()
    {
        this("","","","","");
    }

    String getSentDate()
    {
        return sentDate;
    }

    String getReceivedDate()
    {
        return receivedDate;
    }

    String getLabName()
    {
        return labName;
    }

    String getWork()
    {
        return work;
    }

    String getPatientName(){return patientName;}

    //setters:

    void setSentDate(String sentDate)
    {
        this.sentDate=sentDate;
    }

    void setReceivedDate(String receivedDate)
    {
        this.receivedDate=receivedDate;
    }

    void setLabName(String labName)
    {
        this.labName=labName;
    }

    void setWork(String work)
    {
        this.work=work;
    }

    void setPatientName(String patientName) {this.patientName=patientName;}
}
