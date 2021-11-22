package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Student;

import javax.persistence.EntityManager;

/**
 * Repository class for the relatioship beetween a {@link Student} and an {@link Exam}
 */
public class ExamStudentRepo {

    private EntityManager em;

    public ExamStudentRepo(EntityManager entityManager) {

        this.em = entityManager;
    }

    /**
     * Insert in the student_exam table an entity with the student id and the associated exam id
     */
    public void insertExamStudent(Long examId, Integer studentId) {

        em.getTransaction().begin();

        Student student1 = em.find(Student.class, new Long(studentId));
        Exam exam = em.find(Exam.class, new Long(examId));

        student1.addExam(exam);
        em.persist(student1);

        em.getTransaction().commit();

    }


}
