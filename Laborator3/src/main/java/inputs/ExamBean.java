package inputs;

import database.Database;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "exam", eager = true)
@SessionScoped
public class ExamBean implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    Integer startHour;
    Integer duration;
    Integer id;

    private static final ArrayList<Exam> examList = Database.getINSTANCE().getExamList();

    public String addAction() {

        Exam exam = new Exam(this.id, this.name, this.startHour, this.duration);

        Database.getINSTANCE().storeExam(exam);

        examList.add(exam);

        return null;
    }

    public String deleteAction(Exam exam) {

        Database.getINSTANCE().deleteExam(exam);

        examList.remove(exam);

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Exam> getExamList() {
        return examList;
    }

    public static class Exam {

        String name;
        Integer startHour;
        Integer duration;
        Integer id;

        public Exam(Integer id, String name, Integer startHour, Integer duration) {
            this.id = id;
            this.name = name;
            this.startHour = startHour;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStartHour() {
            return startHour;
        }

        public void setStartHour(Integer startHour) {
            this.startHour = startHour;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getId() { return id; }

        public void setId(Integer id) { this.id = id; }
    }
}
