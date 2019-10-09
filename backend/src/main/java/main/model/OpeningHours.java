package main.model;

import javax.persistence.Embeddable;

@Embeddable
public class OpeningHours {
    private String mondaysToFridays;
    private String saturdays;
    private String sundaysAndHolidays;

    public String getMondaysToFridays() {
        return mondaysToFridays;
    }

    public void setMondaysToFridays(String mondaysToFridays) {
        this.mondaysToFridays = mondaysToFridays;
    }

    public String getSaturdays() {
        return saturdays;
    }

    public void setSaturdays(String saturdays) {
        this.saturdays = saturdays;
    }

    public String getSundaysAndHolidays() {
        return sundaysAndHolidays;
    }

    public void setSundaysAndHolidays(String sundaysAndHolidays) {
        this.sundaysAndHolidays = sundaysAndHolidays;
    }
}
