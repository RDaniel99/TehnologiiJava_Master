package inputs;

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

    private static final ArrayList<Student> studentList =
            new ArrayList<>(Arrays.asList(

                    new Student("Name1", "Exams1"),
                    new Student("Name2", "Exams2"),
                    new Student("Name3", "Exams3")
            ));

    public String addAction() {

        Student student = new Student(this.name, this.exams);

        studentList.add(student);

        return null;
    }

    public String deleteAction(Student student) {

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

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public static class Student {

        String name;
        String exams;

        public Student(String name, String exams) {
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
    }
}
