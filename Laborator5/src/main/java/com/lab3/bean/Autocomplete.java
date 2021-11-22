package com.lab3.bean;

import com.lab3.model.Student;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that handles the autocomplete function from the search student bar
 */
@ManagedBean(name = "autocomplete")
@RequestScoped
public class Autocomplete {

    private Student selectedStudent;
    private List<Student> selectedStudents;

    private StudentBean studentService;

    public Autocomplete() throws NamingException {

        studentService = new StudentBean();
    }


    /**
     * Given the query it selects only those students with a name that contains the typed letter
     * @param query
     */
    public List<Student> complete(String query) throws SQLException, NamingException, ClassNotFoundException {
        String queryLowerCase = query.toLowerCase();
        List<Student> countries = studentService.getStudentList();
        return countries.stream().filter(t -> t.getName().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    /**
     * If a student is selected it will display a message on screen
     * @param event
     */
    public void onItemSelect(SelectEvent<String> event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student Selected", event.getObject()));
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }
}
