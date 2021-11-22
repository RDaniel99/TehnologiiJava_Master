package com.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ManagedBean(name = "test")
@Entity
@DiscriminatorValue(value = "test")
public class WrittenTest extends Exam {

    boolean code;

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }
}
