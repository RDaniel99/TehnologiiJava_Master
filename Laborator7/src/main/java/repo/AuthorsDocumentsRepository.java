package repo;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorsDocumentsRepository {

    @PersistenceContext
    private EntityManager em;

    public AuthorsDocumentsRepository() {}
}
