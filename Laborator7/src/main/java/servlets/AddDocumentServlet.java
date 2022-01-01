package servlets;

import com.google.gson.Gson;
import models.Document;
import repo.DocumentRepository;
import services.AddDocumentService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/addDocument")
public class AddDocumentServlet {

    @Inject
    private DocumentRepository repo;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDocument(Document document) {

        AddDocumentService service = new AddDocumentService(repo);

        return Response.status(201).entity(service.save(document)).build();
    }
}
