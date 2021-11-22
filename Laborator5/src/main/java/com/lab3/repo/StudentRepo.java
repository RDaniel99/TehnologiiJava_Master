package com.lab3.repo;

import com.lab3.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class StudentRepo {

    private EntityManager em;

    public StudentRepo(EntityManager em) {

        this.em = em;
    }


    /**
     * Reads and returns all the students from the database
     *
     * @return a list with all the students
     */
    public List getAllStudent() {

        Query query = em.createNamedQuery("Student.findAll");
        List results = query.getResultList();
        return results;
    }

    /**
     * Returns a list with all the student ids
     *
     * @return a list of ids
     */
    public List<Integer> getAllStudentsIds() {

        Query query = em.createNamedQuery("Student.getIds");
        List<Integer> results = query.getResultList();
        return results;
    }


    /**
     * Stores a {@link Student} into the database
     *
     * @param student the student that needs to be stored
     */
    public int insertStudent(Student student) {

        int result = 0;

        em.getTransaction().begin();

        em.persist(student);

        em.getTransaction().commit();

        return result;
    }

    public void deleteStudent(Student student) {

        em.getTransaction().begin();
        if (!em.contains(student)) {
            student = em.merge(student);
        }

        em.remove(student);

        em.getTransaction().commit();
    }

    public void update(Student student) {

        em.getTransaction().begin();
        Student oldStudent = em.find(Student.class, student);
        oldStudent.setName(student.getName());

        em.persist(oldStudent);
        em.getTransaction().commit();
    }
}
