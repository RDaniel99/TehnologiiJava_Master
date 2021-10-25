package servlets;

import database.Database;
import inputs.ExamBean;
import inputs.StudentBean;
import solver.MatchingSolver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet(value="/results")
public class SolveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<StudentBean.Student> students = Database.getINSTANCE().getStudentList();
        List<ExamBean.Exam> exams = Database.getINSTANCE().getExamList();

        MatchingSolver solver = new MatchingSolver(students, exams);
        List<Set<Integer>> repartition = solver.solve();

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");

        for(int day = 0; day < repartition.size(); day++) {

            out.println(String.format("<h3> Day %d: </h3>", (day + 1)));
            out.println("<ul>");

            for(Integer examId: repartition.get(day)) {

                out.println(String.format("<li> %d </li>", examId));
            }

            out.println("</ul>");
        }

        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
