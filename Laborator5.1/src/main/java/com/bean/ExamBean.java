package com.bean;

import com.model.Presentation;
import com.model.WrittenTest;
import com.repo.ExamRepository;
import com.model.Exam;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "examBean")
@ViewScoped
public class ExamBean implements Serializable {

    @EJB
    ExamRepository repository;

    public ExamBean() { }

    public List<Exam> getAllExams() throws Exception {

        return repository.getAllExams();
    }

    public List<Presentation> getAllPresentations() {

        return repository.getAllPresentations();
    }

    public List<WrittenTest> getAllTests() {

        return repository.getAllTests();
    }

    public void deleteExam(Exam exam) {

        repository.deleteExam(exam);
    }

    public void onRowUpdatePresentation(RowEditEvent event) {

        Presentation exam = (Presentation) event.getObject();

        repository.updatePresentation(exam);
    }

    public void onRowUpdateTest(RowEditEvent event) {

        WrittenTest exam = (WrittenTest) event.getObject();

        repository.updateTest(exam);
    }

    public void saveExamPresentation(Presentation presentation) {

        presentation.setType("presentation");

        repository.savePresentation(presentation);
    }

    public void saveExamTest(WrittenTest test) {

        test.setType("test");

        repository.saveTest(test);
    }
}
