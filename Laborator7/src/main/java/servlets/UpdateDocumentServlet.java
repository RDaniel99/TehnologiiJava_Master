package servlets;

import com.google.gson.Gson;
import models.Document;
import repo.DocumentRepository;
import services.UpdateDocumentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/updateDocument")
public class UpdateDocumentServlet {

    @Inject
    private DocumentRepository repo;

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoc(Document document) {

        UpdateDocumentService service = new UpdateDocumentService(repo);

        return Response.status(201).entity(service.update(document)).build();
    }
}
