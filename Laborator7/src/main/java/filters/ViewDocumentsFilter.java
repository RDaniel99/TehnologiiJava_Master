package filters;

import com.google.gson.Gson;
import models.Document;
import repo.DocumentRepository;
import services.GetDocumentsService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter
public class ViewDocumentsFilter implements Filter {

    private List<Document> documents;

    @Inject
    private DocumentRepository repo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String URI = request.getRequestURI();

        if(URI.indexOf("getDocuments") > 0) {
            if(documents.isEmpty()) {

                GetDocumentsService service = new GetDocumentsService(repo);
                documents = service.getDocuments();
            }

            response.getWriter().write(new Gson().toJson(documents));
            return ;
        }
        else {
            documents.clear();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
