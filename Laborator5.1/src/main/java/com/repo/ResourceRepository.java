package com.repo;

import com.model.ExamResource;
import com.model.Resource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ResourceRepository {

    @PersistenceContext
    private EntityManager em;

    public ResourceRepository() {}

    public List<Resource> getAllResources() {
        Query query = em.createNamedQuery("Resource.findAll");

        return query.getResultList();
    }

    public void deleteResource(Resource resource) {

        if(!em.contains(resource)) {
            resource = em.merge(resource);
        }

        em.remove(resource);
    }

    public List<Resource> getAvailableResources() {

        Query query = em.createNamedQuery("Resource.getAvailable");

        return query.getResultList();
    }

    public void updateResource(Resource resource) {

        em.merge(resource);
    }

    public void storeResource(Resource resource) {

        em.persist(resource);
    }

    public void incrementResource(Long resourceId) {

        Resource resource = findById(resourceId);
        resource.setAvailable(resource.getAvailable() + 1);

        updateResource(resource);
    }

    public void decrementResource(Long resourceId) {

        Resource resource = findById(resourceId);

        if(resource.getAvailable() > 0) {

            resource.setAvailable(resource.getAvailable() - 1);
            updateResource(resource);
        }
    }

    public Resource findById(Long resourceId) {

        Query query = em.createNamedQuery("Resource.findById");
        query.setParameter("id", resourceId);

        List<Resource> resources = query.getResultList();

        return resources.get(0);
    }
}
