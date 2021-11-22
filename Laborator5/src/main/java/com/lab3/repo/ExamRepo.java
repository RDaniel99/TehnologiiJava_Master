package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.SearchCriteria;
import com.lab3.model.Time;

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
        query.setParameter("disc", "presentation");

        return query.getResultList();
    }

    public List getExamListPresentationBySearchCriterias(SearchCriteria searchCriteria) {

        System.out.println(searchCriteria.getDayInExamSession());
        Query query = em.createNamedQuery("Exam.findAll");
        query.setParameter("disc", "presentation");

        return query.getResultList();
    }

    /**
     * Reds all the written exams from the database and returns them
     *
     * @return a list of {@link Exam} entities
     */
    public List getAllExamsWritten() {

        Query query = em.createNamedQuery("Exam.findAll");
        query.setParameter("disc", "written");

        return query.getResultList();
    }

    public Exam getById(Long examId) {

        return em.find(Exam.class, examId);
    }

    /**
     * Insert a {@link Exam} into the database
     *
     * @param exam The exam that needs to be saved
     */
    public int insertExam(Exam exam, Time time) {

        int result = 0;

        em.getTransaction().begin();
        exam.setHour(time);
        em.persist(exam);

        em.getTransaction().commit();

        return result;
    }

    /**
     * Gets a list with all the ids for the saved {@link Exam} entities
     *
     * @return a list of ids
     */
    public List getExamsIds() {

        em.getTransaction().begin();
        Query query = em.createNamedQuery("Exam.getIds");
        em.getTransaction().commit();

        return query.getResultList();
    }

    public void deleteExam(Exam exam) {

        em.getTransaction().begin();
        if (!em.contains(exam)) {
            exam = em.merge(exam);
        }

        em.remove(exam);

        em.getTransaction().commit();
    }

    public void update(Exam exam) {

        em.getTransaction().begin();
        Exam oldExam = em.find(Exam.class, exam);

        if (exam.getHour() != null) {
            oldExam.setHour(exam.getHour());
        }
        if (exam.getDuration() != null) {
            oldExam.setDuration(exam.getDuration());
        }
        if (exam.getName() != null) {

            oldExam.setName(exam.getName());
        }
        if (exam.getDayInExamSession() != null) {
            oldExam.setDayInExamSession(exam.getDayInExamSession());
        }

        em.persist(oldExam);
        em.getTransaction().commit();
    }
}
