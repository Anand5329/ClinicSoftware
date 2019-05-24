package ClinicSoftware;

public class SingleScheduleEntry {

    private String time;
    private String patientName;
    private Slot slot;
    private Appointment appointment;

    public SingleScheduleEntry(Slot slot,Appointment appointment)
    {
        this.slot=slot;
        this.appointment=appointment;
        time=slot.displaySlot();
        patientName=appointment.displayAppointment();
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getTime()
    {
        return time;
    }

    public String getPatientName() {
        return patientName;
    }

    public Slot getSlot() {
        return slot;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
