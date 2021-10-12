package servlets;

import models.Category;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@WebServlet("/get-input")
public class InputServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Category category1 = new Category("id1", "value1");
        Category category2 = new Category("id2", "value2");
        Category category3 = new Category("id3", "value3");

        List<Category> categoryList = asList(category1, category2, category3);

        request.setAttribute("listCategory", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("input.jsp");
        dispatcher.forward(request, response);
    }
}
