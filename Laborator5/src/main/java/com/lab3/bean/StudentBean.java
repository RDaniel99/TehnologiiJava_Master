package com.lab3.bean;

import com.lab3.model.Student;
import com.lab3.repo.StudentRepo;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bean that will handle the operation regarding a student
 */
@ManagedBean(name = "studentBean")
@RequestScoped
public class StudentBean {

    StudentRepo studentRepo;
    private Map<Long, Student> studentsAsMap;

    public StudentBean() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAExample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        this.studentRepo = new StudentRepo(entityManager);
    }

    /**
     * Gets a list of students
     *
     * @return a list of {@link Student}s
     */
    public List<Student> getStudentList() {

        return studentRepo.getAllStudent();
    }

    /**
     * Gets the list of student ids saved
     *
     * @return a list of ids
     */
    public List<Integer> getStudentIds() {

        return studentRepo.getAllStudentsIds();
    }

    public Map<Long, Student> getStudentsAsMap() throws SQLException, ClassNotFoundException, NamingException {

        if (studentsAsMap == null) {
            studentsAsMap = getStudentList().stream().collect(Collectors.toMap(Student::getId, student -> student));
        }
        return studentsAsMap;
    }

    /**
     * Saves a student
     *
     * @param student The {@link Student} entity that needs to be saved
     */
    public void saveStudent(Student student) {

        int result = studentRepo.insertStudent(student);
        if (result != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The exam was successfully saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The exam was not saved"));
        }
    }

    public void remove(Student student) {

        studentRepo.deleteStudent(student);
    }

    public void rowCancel(RowEditEvent<Student> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void updateRow(RowEditEvent<Student> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
