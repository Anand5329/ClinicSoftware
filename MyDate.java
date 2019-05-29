package ClinicSoftware;

import java.time.Month;
import java.util.Date;

public class MyDate {

    private String date[]=new String[3];

    public MyDate()
    {
        Date date=new Date();
        this.date=getDate(date);
    }

    String[] getDate(Date date)
    {
        String arr[]=date.toString().split(" ");
        int d=Integer.valueOf(arr[2]);
        int m=getMonthNumber(arr[1]);
        arr[1]=Integer.toString(m);
        int y=Integer.valueOf(arr[5]);
        String myDate[]={""+d,""+m,""+y};

        if(noOfDigits(d)==1)
            myDate[0]="0"+myDate[2];
        if(noOfDigits(m)==1)
            myDate[1]="0"+myDate[1];
        if(noOfDigits(y)==1)
            myDate[2]="0"+myDate[5];

        return myDate;
    }

    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    public String toString()
    {
        return date[0]+"-"+date[1]+"-"+date[2];
    }

    int noOfDigits(int n)
    {
        int ctr=0;
        while(n>0)
        {
            n=n/10;
            ctr++;
        }
        return ctr;
    }
}
