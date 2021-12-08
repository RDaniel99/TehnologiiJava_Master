package beans;

import models.AuthorDocument;
import repo.AuthorsDocumentsRepository;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorDocumentBean implements Serializable {

    @Inject
    private AuthorsDocumentsRepository repo;

    public AuthorDocumentBean() {}
    public AuthorDocumentBean(AuthorsDocumentsRepository repo) {
        this.repo = repo;
    }

    public void save(AuthorDocument authorDocument) {

        repo.save(authorDocument);
    }
}
