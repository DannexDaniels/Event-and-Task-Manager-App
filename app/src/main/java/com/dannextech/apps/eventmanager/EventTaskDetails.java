package com.dannextech.apps.eventmanager;

/**
 * Created by amoh on 12/8/2017.
 */

public class EventTaskDetails {
    private String title, venue, date, time,description,remindMe;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemindMe() {
        return remindMe;
    }

    public void setRemindMe(String remindMe) {
        this.remindMe = remindMe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public EventTaskDetails() {

    }
}
