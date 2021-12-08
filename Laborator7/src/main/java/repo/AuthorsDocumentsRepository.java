package repo;

import logger.Logger;
import models.AuthorDocument;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class AuthorsDocumentsRepository {

    @PersistenceContext
    private EntityManager em;

    public AuthorsDocumentsRepository() {}

    @Interceptors(Logger.class)
    @Transactional
    public void save(AuthorDocument authorDocument) {

        em.persist(authorDocument);
    }
}
