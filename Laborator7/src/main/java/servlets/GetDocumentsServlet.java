package servlets;

import com.google.gson.Gson;
import repo.DocumentRepository;
import services.GetDocumentsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/getDocuments")
public class GetDocumentsServlet {

    @Inject
    private DocumentRepository repo;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveDocuments() {

        GetDocumentsService service = new GetDocumentsService(repo);

        return Response.status(200).entity(service.getDocuments()).build();
    }
}
