package com.lab3.bean;

import com.lab3.model.*;
import com.lab3.repo.ExamRepo;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bean for the operations regarding a student
 */
@ManagedBean(name = "examBean")
@ViewScoped
public class ExamBean implements Serializable {


    ExamRepo examRepo;


    public ExamBean() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAExample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        this.examRepo = new ExamRepo(entityManager);
    }


    public List<Exam> getExamListPresentation() throws Exception {

        return examRepo.getAllExamsPresentation();
    }



}
