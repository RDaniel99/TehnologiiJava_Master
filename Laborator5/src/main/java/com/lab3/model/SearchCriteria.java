package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "search")
@RequestScoped
public class SearchCriteria {

    private String name;
    private String duration;
    private String dayInExamSession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayInExamSession() {
        return dayInExamSession;
    }

    public void setDayInExamSession(String dayInExamSession) {
        this.dayInExamSession = dayInExamSession;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
