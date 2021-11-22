package com.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ManagedBean(name = "presentation")
@Entity
@DiscriminatorValue(value = "presentation")
public class Presentation extends Exam {

    boolean powerpoint;

    public boolean isPowerpoint() {
        return powerpoint;
    }

    public void setPowerpoint(boolean powerpoint) {
        this.powerpoint = powerpoint;
    }
}