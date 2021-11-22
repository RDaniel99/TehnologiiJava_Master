package com.lab3.repo;

import com.lab3.model.Exam;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;

@ManagedBean
public class ExamRepo {

    private final EntityManager em;

    public ExamRepo(EntityManager entityManager) {

        this.em = entityManager;
    }


    /**
     * Reds all the exams from the database and returns them
     *
     * @return a list of {@link Exam} entities
     */
    public List getAllExamsPresentation() {

        Query query = em.createNamedQuery("Exam.findAll");

        return query.getResultList();
    }
}
