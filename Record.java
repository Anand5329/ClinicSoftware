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

    public Record(String name, String phone, Appointment first_appointment,int age, String description, Appointment latest_appointment, boolean heart_condition, boolean allergy,boolean blood_pressure,boolean diabetes)
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

    public Record()
    {
        this("","",null,-1,"",null,false,false,false,false);
    }

    public Record(String n, String p)
    {
        this(n, p, null, -1, "", null,false, false,false,false);
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public Appointment getFirstAppointment()
    {
        return first_appointment;
    }

    public int getAge()
    {
        return age;
    }

    public String getDesc()
    {
        return desc;
    }

    public Appointment getLatestAppointment()
    {
        return latest_appointment;
    }

    public double getMoney()
    {
        return money;
    }

    public boolean getHeartCondition(){return heart_condition;}

    public boolean getAllergy(){ return allergy; }

    public boolean getBloodPressure(){ return blood_pressure; }

    public boolean getDiabetes(){ return diabetes; }

    public void setDesc(String d)
    {
        desc=d;
    }

    public void setFirstAppointment(Appointment first_appointment)
    {
        this.first_appointment=first_appointment;
    }

    public void setLatestAppointment(Appointment latest_appointment)
    {
        this.latest_appointment=latest_appointment;
    }

    public void setAge(int a)
    {
        age=a;
    }

    public void setHeartCondition(boolean hc){ heart_condition=hc; }

    public void setAllergy(boolean all){ allergy=all; }

    public void setBloodPressure(boolean bp){ blood_pressure=bp; }

    public void setDiabetes(boolean dp){ diabetes=dp; }

    public void updateMoney(double mon)
    {
        money+=mon;
    }

    public void update(Appointment a)
    {
        setLatestAppointment(a);
        updateMoney(a.getPrice());
    }

    public String getFileName()
    {
        return name+" "+phone;
    }

    public void display()
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