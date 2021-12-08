package beans;

import models.Document;
import repo.DocumentRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "documentBean")
@ViewScoped
public class DocumentBean implements Serializable {

    @Inject
    private DocumentRepository repo;

    public DocumentBean() {}
    public DocumentBean(DocumentRepository repo) {
        this.repo = repo;
    }

    public Document save(Document document) {

        return repo.save(document);
    }

    public List<Document> retrieve() {

        return repo.retrieve();
    }
}
