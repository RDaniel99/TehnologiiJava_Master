package servlets;

import com.google.gson.Gson;
import models.Document;
import repo.DocumentRepository;
import services.DeleteDocumentService;
import services.GetDocumentsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/deleteDocument")
public class DeleteDocumentServlet {

    @Inject
    private DocumentRepository repo;

    @DELETE
    @Path("/")
    public Response doDelete(@QueryParam("id") long id) {

        DeleteDocumentService service = new DeleteDocumentService(repo);
        GetDocumentsService serviceGets = new GetDocumentsService(repo);
        Document document = serviceGets.getDocuments().stream().filter(doc -> doc.getId().equals(id)).findFirst().get();

        service.delete(document);

        return Response.status(200, "Deleted").build();
    }
}
