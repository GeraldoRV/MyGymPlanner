package main.model;

import javax.persistence.Embeddable;

@Embeddable
public class WorkingHours {
    private String mondayToFriday;
    private String saturday;
    private String sunday;

    public String getMondayToFriday() {
        return mondayToFriday;
    }

    public void setMondayToFriday(String mondayToFriday) {
        this.mondayToFriday = mondayToFriday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
