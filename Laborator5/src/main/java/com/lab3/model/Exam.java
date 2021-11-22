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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "discriminator",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "exam")
@NamedQueries({
        @NamedQuery(query = "Select s from exam s where s.discriminator = :disc", name = "Exam.findAll"),
        @NamedQuery(query = "Select s.id from exam s", name = "Exam.getIds")
})
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dayinexamsession")
    private Integer dayInExamSession;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "hour", column = @Column(name = "hour")),
            @AttributeOverride(name = "minutes", column = @Column(name = "minute")),
    })
    private Time hour;

    String discriminator;

    @Column(name = "duration")
    private Float duration;

    @ManyToMany(mappedBy = "exams")
    Set<Student> students;


    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Exam() {

    }


    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Time getHour() {
        return hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getDayInExamSession() {
        return dayInExamSession;
    }

    public void setDayInExamSession(Integer dayInExamSession) {
        this.dayInExamSession = dayInExamSession;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
