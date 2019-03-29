package ClinicSoftware;

public class Slot
{
    double time_period;
    double start_time;

    Slot(double time_period,double start_time)
    {
        this.time_period=time_period;
        this.start_time=start_time;
    }

    Slot(double start_time)
    {
        this(0.25,start_time);
    }

    Slot()
    {
        this(0.25,0);
    }

    double getTimePeriod()
    {
        return time_period;
    }

    double getStartTime()
    {
        return start_time;
    }

    double getEndTime()
    {
        return start_time+time_period;
    }

    void setTimePeriod(double time_period)
    {
        this.time_period=time_period;
    }

    void setStartTime(double start_time)
    {
        this.start_time=start_time;
    }

    String displaySlot()
    {
        String s=""+start_time+" - "+(start_time+time_period);
        return s;
    }


}
