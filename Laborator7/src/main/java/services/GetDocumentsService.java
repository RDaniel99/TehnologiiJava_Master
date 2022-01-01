package services;

import models.Document;
import repo.DocumentRepository;

import javax.inject.Inject;
import java.util.List;

public class GetDocumentsService {

    private DocumentRepository repo;

    @Inject
    public GetDocumentsService(DocumentRepository repo) {
        this.repo = repo;
    }

    public List<Document> getDocuments() {

        return repo.retrieve();
    }
}
