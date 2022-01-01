package services;

import models.Document;
import repo.DocumentRepository;

import javax.inject.Inject;

public class UpdateDocumentService {

    private DocumentRepository repo;

    @Inject
    public UpdateDocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public Document update(Document document) {

        return repo.update(document);
    }
}
