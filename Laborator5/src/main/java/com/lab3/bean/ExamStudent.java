package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.model.Student;
import com.lab3.repo.ExamStudentRepo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

/**
 * Bean for the operations regarding the relation between a student and an exam
 */
@ManagedBean(name = "examStudentBean")
@RequestScoped
public class ExamStudent {

    ExamStudentRepo examStudentRepo;
    Integer studentId;
    Long examId;

    public Long getExamId() {
        return examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setExamId(Long examId) {

        this.examId = examId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public ExamStudent() throws NamingException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAExample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        this.examStudentRepo = new ExamStudentRepo(entityManager);
    }

    /**
     * Saves the relationship between a {@link Student} and a {@link Exam}
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveExamStudent() throws SQLException, ClassNotFoundException, NamingException {

        examStudentRepo.insertExamStudent(examId, studentId);
    }


}
