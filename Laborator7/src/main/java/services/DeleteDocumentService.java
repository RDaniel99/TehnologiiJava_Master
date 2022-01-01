package services;

import models.Document;
import repo.DocumentRepository;

import javax.inject.Inject;

public class DeleteDocumentService {

    private DocumentRepository repo;

    @Inject
    public DeleteDocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public void delete(Document document) {

        repo.delete(document);
    }
}
