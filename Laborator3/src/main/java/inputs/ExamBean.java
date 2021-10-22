package inputs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "exam", eager = true)
@SessionScoped
public class ExamBean implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String startHour;
    String duration;

    private static final ArrayList<Exam> examList =
            new ArrayList<>(Arrays.asList(

                    new Exam("Exam1", "8", "2"),
                    new Exam("Exam2", "10", "1"),
                    new Exam("Exam3", "12", "3")
            ));

    public String addAction() {

        Exam exam = new Exam(this.name, this.startHour, this.duration);

        examList.add(exam);

        return null;
    }

    public String deleteAction(Exam exam) {

        examList.remove(exam);

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<Exam> getExamList() {
        return examList;
    }

    public static class Exam {

        String name;
        String startHour;
        String duration;

        public Exam(String name, String startHour, String duration) {
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

        public String getStartHour() {
            return startHour;
        }

        public void setStartHour(String startHour) {
            this.startHour = startHour;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }
}
