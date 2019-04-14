package ClinicSoftware;

public class Record
{
    private String name;
    private long phone;
    private String first_date;
    private int age;
    private String desc;
    private String latest_date;
    private double money;
    private boolean heart_condition;
    private boolean allergy;
    private boolean blood_pressure;
    private boolean diabetes;

    Record(String n, long p, String d1,int a, String d, String ldate, boolean hc, boolean all,boolean bp,boolean db)
    {
        name=n;
        phone=p;
        first_date=d1;
        age=a;
        desc=d;
        latest_date=ldate;
        money=0;
        heart_condition=hc;
        allergy=all;
        blood_pressure=bp;
        diabetes=db;
    }

    Record()
    {
        this("",0,"",-1,"","",false,false,false,false);
    }

    Record(String n, long p)
    {
        this(n, p, "", -1, "", "",false, false,false,false);
    }

    String getName()
    {
        return name;
    }

    long getPhone()
    {
        return phone;
    }

    String getFirstDate()
    {
        return first_date;
    }

    int getAge()
    {
        return age;
    }

    String getDesc()
    {
        return desc;
    }

    String getLatestDate()
    {
        return latest_date;
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

    void setFirstDate(String date)
    {
        first_date=date;
    }

    void setLatestDate(String ld)
    {
        latest_date=ld;
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
        setLatestDate(a.getDate());
        updateMoney(a.getPrice());
    }

    String getFileName()
    {
        return name+" "+phone;
    }

    void display()
    {
        System.out.println("Name: "+name+);
        System.out.println("Phone No.: "+phone);
        System.out.println("Age: "+age);
        System.out.println("First Date: "+first_date);
        System.out.println("Latest Date:"+latest_date);
        System.out.println("Description: "+desc);
        System.out.println("Total Money Paid: "+money);
        System.out.println("Heart Condition: "+heart_condition);
        System.out.println("Allergies: "+allergy);
        System.out.println("Diabetes: "+diabetes);
        System.out.println("High Blood Pressure: "+blood_pressure);
    }
}