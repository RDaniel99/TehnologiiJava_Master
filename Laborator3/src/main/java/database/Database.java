package database;

import inputs.ExamBean;
import inputs.StudentBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    private static Database INSTANCE;
    private Connection connection = null;

    private final String querySelectAllStudents = "SELECT * FROM students";
    private final String querySelectAllExams = "SELECT * FROM exams";
    private final String queryInsertStudent = "INSERT INTO students(name, exams) VALUES(\"%s\", \"%s\")";
    private final String queryInsertExam = "INSERT INTO exams(name, startHour, duration) VALUES(\"%s\", %d, %d)";
    private final String queryDeleteStudent = "DELETE FROM students where id = %d";
    private final String queryDeleteExam = "DELETE FROM exams where id = %d";

    private Database() {
        try {

            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/lab4");
            java.sql.Connection conn = ds.getConnection();

            //Class.forName("com.mysql.jdbc.Driver");
            connection = conn;//DriverManager.getConnection("jdbc:mysql://localhost:3306/laborator3_java?characterEncoding=latin1&useConfigs=maxPerformance&autoReconnect=true&useSSL=false",
                    //"root", "password");
        }
        catch (Exception e) {
            System.out.println("Ceva nu e bine la BD");
        }
    }

    public static synchronized Database getINSTANCE() {

        if(INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    public ArrayList<StudentBean.Student> getStudentList() {
        ArrayList<StudentBean.Student> studentList = new ArrayList<>();

        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            ResultSet rs = stmt.executeQuery(querySelectAllStudents);

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String exams = rs.getString("exams");

                studentList.add(new StudentBean.Student(id, name, exams));
            }

            stmt.close();
        }
        catch(Exception e) {
            System.out.println("Ceva nu a mers bine");
        }

        return studentList;
    }

    public ArrayList<ExamBean.Exam> getExamList() {
        ArrayList<ExamBean.Exam> examList = new ArrayList<>();

        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            ResultSet rs = stmt.executeQuery(querySelectAllExams);

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer startHour = rs.getInt("startHour");
                Integer duration = rs.getInt("duration");

                examList.add(new ExamBean.Exam(id, name, startHour, duration));
            }

            stmt.close();
        }
        catch(Exception e) {
            System.out.println("Ceva nu a mers bine");
        }

        return examList;
    }

    public void storeStudent(StudentBean.Student student) {
        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            stmt.executeUpdate(String.format(queryInsertStudent, student.getName(), student.getExams()),
                    Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                student.setId(rs.getInt(1));
            }
        }
        catch(Exception e) {
            System.out.println("Ceva nu a mers bine");
        }
    }

    public void storeExam(ExamBean.Exam exam) {
        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            stmt.executeUpdate(String.format(queryInsertExam, exam.getName(), exam.getStartHour(), exam.getDuration()),
                    Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                exam.setId(rs.getInt(1));
            }
        }
        catch(Exception e) {
            System.out.println("Ceva nu a mers bine");
        }
    }

    public void deleteStudent(StudentBean.Student student) {
        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            stmt.executeUpdate(String.format(queryDeleteStudent, student.getId()));
        }
        catch (Exception e) {
            System.out.println("Ceva nu a mers bine");
        }
    }

    public void deleteExam(ExamBean.Exam exam) {
        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            stmt.executeUpdate(String.format(queryDeleteExam, exam.getId()));
        }
        catch (Exception e) {
            System.out.println("Ceva nu a mers bine");
        }
    }
}
