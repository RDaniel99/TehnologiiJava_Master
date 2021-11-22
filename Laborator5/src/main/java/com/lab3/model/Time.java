package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Model that represents a time value with a hour and a minute
 */
@ManagedBean(name = "time")
@RequestScoped
@Embeddable
public class Time implements Serializable {

    private int hour;

    private int minutes;

    public Time() {

    }

    public Time(int hour, int minute) {

        this.hour = hour;
        this.minutes = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
