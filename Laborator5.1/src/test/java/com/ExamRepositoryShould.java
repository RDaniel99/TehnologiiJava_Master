package com;

import com.model.Exam;
import com.model.Presentation;
import com.repo.ExamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ExamRepositoryShould {

    EntityManager em;
    ExamRepository repository;

    @Test
    public void getExamList() {

        // setup
        Exam expected = new Presentation();

        em = mock(EntityManager.class);
        Query query = mock(Query.class);
        repository = new ExamRepository(em);

        when(em.createNamedQuery(any())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(expected));

        // execute
        List<Exam> actual = repository.getAllExams();

        // verify
        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));
    }

    @Test
    public void deleteExam() {

        // setup
        Exam exam = new Presentation();

        em = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);
        repository = new ExamRepository(em);


        // execute
        repository.deleteExam(exam);

        // verify
        verify(em, times(2)).getTransaction();
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
        verify(em, times(1)).remove(exam);
    }

    @Test
    public void updateExam() {

        // setup
        Presentation exam = new Presentation();

        em = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);
        repository = new ExamRepository(em);


        // execute
        repository.savePresentation(exam);

        // verify
        verify(em, times(2)).getTransaction();
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
        verify(em, times(1)).persist(exam);
    }

    @Test
    public void createExam() {

        // setup
        Presentation exam = new Presentation();

        em = mock(EntityManager.class);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);
        repository = new ExamRepository(em);


        // execute
        repository.savePresentation(exam);

        // verify
        verify(em, times(2)).getTransaction();
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
        verify(em, times(1)).persist(exam);
    }
}
