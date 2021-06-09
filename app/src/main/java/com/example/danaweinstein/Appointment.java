package com.example.danaweinstein;

public class Appointment {
    private int hour;
    private boolean selected;

    public Appointment(int hour) {
        this.hour = hour;
        this.selected = false;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        String str = "";
        if (this.hour < 10)
            str = "0" + this.hour +  ":00";
        else
            str = this.hour + ":00";
        if (this.hour < 9)
            str += " - 0" + (this.hour + 1) + ":00";
        else
            str += " - " + (this.hour + 1) + ":00";
        return str;
    }

}
