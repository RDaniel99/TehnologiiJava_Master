package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ManagedBean(name="written")
@Entity
@DiscriminatorValue(value = "written")
public class Written extends Exam {

    boolean allowsResources;

    public void setAllowsResources(boolean allowsResources) {
        this.allowsResources = allowsResources;
    }

    public boolean isAllowsResources() {
        return allowsResources;
    }
}
