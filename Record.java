package ClinicSoftware;

public class Record
{
    private String name;
    private String phone;
    private Appointment first_appointment;
    private int age;
    private String desc;
    private Appointment latest_appointment;
    private double money;
    private boolean heart_condition;
    private boolean allergy;
    private boolean blood_pressure;
    private boolean diabetes;

    Record(String name, String phone, Appointment first_appointment,int age, String description, Appointment latest_appointment, boolean heart_condition, boolean allergy,boolean blood_pressure,boolean diabetes)
    {
        this.name=name;
        this.phone=phone;
        this.first_appointment=first_appointment;
        this.age=age;
        this.desc=description;
        this.latest_appointment=latest_appointment;
        money=0;
        this.heart_condition=heart_condition;
        this.allergy=allergy;
        this.blood_pressure=blood_pressure;
        this.diabetes=diabetes;
    }

    Record()
    {
        this("","",null,-1,"",null,false,false,false,false);
    }

    Record(String n, String p)
    {
        this(n, p, null, -1, "", null,false, false,false,false);
    }

    String getName()
    {
        return name;
    }

    String getPhone()
    {
        return phone;
    }

    Appointment getFirstAppointment()
    {
        return first_appointment;
    }

    int getAge()
    {
        return age;
    }

    String getDesc()
    {
        return desc;
    }

    Appointment getLatestAppointment()
    {
        return latest_appointment;
    }

    double getMoney()
    {
        return money;
    }

    boolean getHeartCondition(){return heart_condition;}

    boolean getAllergy(){ return allergy; }

    boolean getBloodPressure(){ return blood_pressure; }

    boolean getDiabetes(){ return diabetes; }

    void setDesc(String d)
    {
        desc=d;
    }

    void setFirstAppointment(Appointment first_appointment)
    {
        this.first_appointment=first_appointment;
    }

    void setLatestAppointment(Appointment latest_appointment)
    {
        this.latest_appointment=latest_appointment;
    }

    void setAge(int a)
    {
        age=a;
    }

    void setHeartCondition(boolean hc){ heart_condition=hc; }

    void setAllergy(boolean all){ allergy=all; }

    void setBloodPressure(boolean bp){ blood_pressure=bp; }

    void setDiabetes(boolean dp){ diabetes=dp; }

    void updateMoney(double mon)
    {
        money+=mon;
    }

    void update(Appointment a)
    {
        setLatestAppointment(a);
        updateMoney(a.getPrice());
    }

    String getFileName()
    {
        return name+" "+phone;
    }

    void display()
    {
        System.out.println("Name: "+name);
        System.out.println("Phone No.: "+phone);
        System.out.println("Age: "+age);
        System.out.println("First Date: "+first_appointment.getDate());
        System.out.println("Latest Date:"+latest_appointment.getDate());
        System.out.println("Description: "+desc);
        System.out.println("Total Money Paid: "+money);
        System.out.println("Heart Condition: "+heart_condition);
        System.out.println("Allergies: "+allergy);
        System.out.println("Diabetes: "+diabetes);
        System.out.println("High Blood Pressure: "+blood_pressure);
    }
}