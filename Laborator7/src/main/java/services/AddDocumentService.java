package services;


import models.Document;
import repo.DocumentRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


public class AddDocumentService {

    private DocumentRepository repo;

    @Inject
    public AddDocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public Document save(Document document) {

        return repo.save(document);
    }
}
