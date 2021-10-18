package filters;

import decorators.InputRequestDecorator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

@WebFilter("/get-input")
public class InputFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if(request.getMethod().equals("GET")) {
            chain.doFilter(req, response);
            return ;
        }

        PrintWriter out = response.getWriter();
        String categoryName = request.getParameter("category");
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        if(categoryName == null || categoryName.isEmpty()) {
            ServletContext c = this.getServletContext();
            categoryName = c.getInitParameter("default-category");
        }

        if((categoryName != null && categoryName.isEmpty())
                || (key != null && key.isEmpty())
                || (value != null && value.isEmpty())) {

            out.println("Campurile nu pot ramane goale");
            return ;
        }

        System.out.printf("Request received by input.jsp with values: %s %s %s", categoryName, key, value);

        String[] listCategory = new String[1];
        listCategory[0] = categoryName;
        Map<String, String[]> extraParams = new TreeMap<String, String[]>();
        extraParams.put("category", listCategory);
        InputRequestDecorator reqDecorator = new InputRequestDecorator(request, extraParams);

        chain.doFilter(reqDecorator, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void destroy() {

    }
}
