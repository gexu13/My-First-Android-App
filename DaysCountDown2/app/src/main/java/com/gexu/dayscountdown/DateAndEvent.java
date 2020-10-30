package com.gexu.dayscountdown;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DateAndEvent {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Event")
    private String event;
    @ColumnInfo(name = "Days")
    private int days;
    @ColumnInfo(name = "sinceOrUntil")
    private String sinceOrUntil;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "StartDateOrTargetDate")
    private String startDateOrTargetDate;


    //构造方法
    public DateAndEvent(String event, int days, String sinceOrUntil, String date, String startDateOrTargetDate) {
        this.event = event;
        this.days = days;
        this.sinceOrUntil = sinceOrUntil;
        this.date = date;
        this.startDateOrTargetDate = startDateOrTargetDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getSinceOrUntil() {
        return sinceOrUntil;
    }

    public void setSinceOrUntil(String sinceOrUntil) {
        this.sinceOrUntil = sinceOrUntil;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartDateOrTargetDate() {
        return startDateOrTargetDate;
    }

    public void setStartDateOrTargetDate(String startDateOrTargetDate) {
        this.startDateOrTargetDate = startDateOrTargetDate;
    }


}
