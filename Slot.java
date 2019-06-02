package ClinicSoftware;

public class Slot
{
    double time_period;
    double start_time;

    public Slot(double time_period,double start_time)
    {
        this.time_period=time_period;
        this.start_time=start_time;
    }

    public Slot(String slot)
    {
        this.time_period=toSlot(slot).getTimePeriod();
        this.start_time=toSlot(slot).getStartTime();
    }

    public Slot(double start_time)
    {
        this(0.25,start_time);
    }

    public Slot()
    {
        this(0.25,0);
    }

    public double getTimePeriod()
    {
        return time_period;
    }

    public double getStartTime()
    {
        return start_time;
    }

    public double getEndTime()
    {
        return start_time+time_period;
    }

    public void setTimePeriod(double time_period)
    {
        this.time_period=time_period;
    }

    public void setStartTime(double start_time)
    {
        this.start_time=start_time;
    }

    public String displaySlot()
    {
        String s=""+start_time+" - "+(start_time+time_period);
        return s;
    }

    public boolean isGreater(Slot s)
    {
        if(start_time>s.getStartTime())
            return true;
        else
            return false;
    }

    public Slot toSlot(String s)
    {
        String str[]=s.split("-");
        Slot slot=new Slot(Double.valueOf(str[1])-Double.valueOf(str[0]),Double.valueOf(str[0]));
        return slot;
    }

}