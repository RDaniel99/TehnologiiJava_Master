package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Model class for an exam entity
 */
@ManagedBean(name = "exam")
@RequestScoped
@Entity(name = "exam")
@Table(name = "exam")
@NamedQueries({
        @NamedQuery(query = "Select s from exam s", name = "Exam.findAll")
})
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    public Exam() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
