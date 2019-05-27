package ClinicSoftware;

import java.time.Month;
import java.util.Date;

public class MyDate {

    private String date[]=new String[3];

    public MyDate()
    {
        Date date=new Date();
        String arr[]=date.toString().split(" ");
        this.date[0]=arr[2];
        int m=getMonthNumber(arr[1]);
        this.date[1]=Integer.toString(m);
        this.date[2]=arr[5];
    }

    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    public String toString()
    {
        return date[0]+"-"+date[1]+"-"+date[2];
    }
}
