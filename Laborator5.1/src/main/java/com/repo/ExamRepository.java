package com.repo;

import com.model.Exam;
import com.model.Presentation;
import com.model.WrittenTest;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ExamRepository {

    @PersistenceContext
    private EntityManager em;

    public ExamRepository() {

    }

    /*public ExamRepository(EntityManager entityManager) {

        this.em = entityManager;
    }*/

    public List getAllExams() {

        Query query = em.createNamedQuery("Exam.findAll");

        return query.getResultList();
    }

    public List getAllPresentations() {

        Query query = em.createNamedQuery("Exam.findAllByType");
        query.setParameter("disc", "presentation");

        return query.getResultList();
    }

    public List getAllTests() {

        Query query = em.createNamedQuery("Exam.findAllByType");
        query.setParameter("disc", "test");

        return query.getResultList();
    }

    public void deleteExam(Exam exam) {

        //em.getTransaction().begin();

        em.remove(exam);

        //em.getTransaction().commit();
    }

    public void updateTest(WrittenTest exam) {

        //em.getTransaction().begin();

        em.persist(exam);

        //em.getTransaction().commit();
    }

    public void updatePresentation(Presentation exam) {

        //em.getTransaction().begin();

        em.persist(exam);

        //em.getTransaction().commit();
    }

    public void savePresentation(Presentation presentation) {

        //em.getTransaction().begin();

        em.persist(presentation);

        //em.getTransaction().commit();
    }

    public void saveTest(WrittenTest test) {

        //em.getTransaction().begin();

        em.persist(test);

        //em.getTransaction().commit();
    }
}
