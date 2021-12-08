package repo;

import logger.Logger;
import models.Document;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class DocumentRepository {

    @PersistenceContext
    private EntityManager em;

    public DocumentRepository() {}

    @Transactional
    @Interceptors(Logger.class)
    public Document save(Document document) {

        em.persist(document);
        em.flush();
        return document;
    }

    public List<Document> retrieve() {

        Query query = em.createNamedQuery("Document.findAll");

        return query.getResultList();
    }
}
