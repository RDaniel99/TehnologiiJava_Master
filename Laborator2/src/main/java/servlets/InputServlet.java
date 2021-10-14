package servlets;

import database.Database;
import models.Category;
import models.Record;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@WebServlet("/get-input")
public class InputServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Category category1 = new Category("value1");
        Category category2 = new Category("value2");
        Category category3 = new Category("value3");

        List<Category> categoryList = asList(category1, category2, category3);

        for(Category category: categoryList) {
            Database.getINSTANCE().addCategory(category);
        }

        request.setAttribute("listCategory", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("input.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String categoryName = request.getParameter("category");
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        if(categoryName.isEmpty() || key.isEmpty() || value.isEmpty()) {
            out.println("Campurile nu pot ramane goale");
            return ;
        }

        Database.getINSTANCE().addRecord(new Record(new Category(categoryName), key, value));
        out.println("Inregistrare adaugata cu succes");

        response.sendRedirect("/Laborator2-1.0-SNAPSHOT/see-records");
    }
}
