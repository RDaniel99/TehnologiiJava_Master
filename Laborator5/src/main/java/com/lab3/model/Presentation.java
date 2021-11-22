package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ManagedBean(name = "presentation")
@Entity
@DiscriminatorValue(value = "presentation")
public class Presentation extends Exam {

    int numberOfSlides;

    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    public void setNumberOfSlides(int numberOfSlides) {
        this.numberOfSlides = numberOfSlides;
    }
}
