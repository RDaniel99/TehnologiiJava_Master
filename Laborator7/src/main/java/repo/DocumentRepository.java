package repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DocumentRepository {

    @PersistenceContext
    private EntityManager em;

    public DocumentRepository() {}
}
