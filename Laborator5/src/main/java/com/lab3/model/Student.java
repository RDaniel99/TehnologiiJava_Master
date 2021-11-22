package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Model class for a student entity
 */
@ManagedBean(name = "student")
@RequestScoped
@Entity(name = "student")
@Table(name = "student")
@NamedQueries({
        @NamedQuery(query = "Select s from student s", name = "Student.findAll"),
        @NamedQuery(query = "Select s.id from student s", name = "Student.getIds")
})
public class Student implements Serializable, Comparable<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToMany
    @JoinTable(
            name = "student_exam",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "examid"))
    Set<Exam> exams;

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Student:" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
