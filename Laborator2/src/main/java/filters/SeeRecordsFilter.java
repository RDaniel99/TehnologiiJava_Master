package filters;

import decorators.ResponseDecorator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/see-records")
public class SeeRecordsFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        //ResponseDecorator response = new ResponseDecorator((HttpServletResponse) res);

        chain.doFilter(req, res);

        // TODO: De ce nu merge??
        /*String content = response.toString();
        int indexEndTable = content.indexOf("</body>");
        content = content.substring(0, indexEndTable) + "<p> Decorat </p> </body> </html>";
        PrintWriter out = response.getWriter();
        out.write(content);*/
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {

    }
}
