package database;

import inputs.StudentBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    private static Database INSTANCE;
    private Connection connection = null;

    private String querySelectAllStudents = "SELECT * FROM students";
    private String queryInsertStudent = "INSERT INTO students(name, exams) VALUES(\"%s\", \"%s\")";
    private String queryDeleteStudent = "DELETE FROM students where id = %d";

    private Database() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laborator3_java?characterEncoding=latin1&useConfigs=maxPerformance&autoReconnect=true&useSSL=false",
                    "root", "password");
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

    public void deleteStudent(StudentBean.Student student) {
        try {
            Statement stmt = getINSTANCE().connection.createStatement();
            stmt.executeUpdate(String.format(queryDeleteStudent, student.getId()));
        }
        catch (Exception e) {
            System.out.println("Ceva nu a mers bine");
        }
    }
}
