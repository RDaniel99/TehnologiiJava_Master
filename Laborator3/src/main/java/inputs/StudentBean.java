package inputs;

import database.Database;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "student", eager = true)
@SessionScoped
public class StudentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String exams;
    Integer id;

    private static final ArrayList<Student> studentList = Database.getINSTANCE().getStudentList();

    public String addAction() {

        Student student = new Student(this.id, this.name, this.exams);

        Database.getINSTANCE().storeStudent(student);

        studentList.add(student);

        return null;
    }

    public String deleteAction(Student student) {

        Database.getINSTANCE().deleteStudent(student);

        studentList.remove(student);

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExams() {
        return exams;
    }

    public void setExams(String exams) {
        this.exams = exams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public static class Student {

        String name;
        String exams;
        Integer id;

        public Student(Integer id, String name, String exams) {
            this.id = id;
            this.name = name;
            this.exams = exams;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExams() {
            return exams;
        }

        public void setExams(String exams) {
            this.exams = exams;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
