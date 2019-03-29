package ClinicSoftware;

public class test extends Record {

    public static void main(String[] args) {
        Record p1=new Record("Name",1234567890);
        System.out.println(p1.getName());

        Slot t1=new Slot(0.5,16);
        Appointment a1=new Appointment(p1,"12/1/19");
        Schedule s1=new Schedule(2,"12/01/2019");
        s1.add(a1);
        s1.addBreak();
        s1.display();
        s1.remove(a1);
        s1.display();
    }

}
