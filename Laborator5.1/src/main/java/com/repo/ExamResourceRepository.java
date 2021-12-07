package com.repo;

import com.model.ExamResource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ExamResourceRepository {

    @PersistenceContext
    private EntityManager em;

    public ExamResourceRepository() {}

    public List<ExamResource> getAll() {

        Query query = em.createNamedQuery("ExamResource.findAll");

        return query.getResultList();
    }

    public ExamResource findById(ExamResource examResource) {

        Query query = em.createNamedQuery("ExamResource.findById");
        query.setParameter("id", examResource.getId());

        List<ExamResource> list = query.getResultList();
        return list.get(0);
    }

    public void save(ExamResource item) {

        em.persist(item);
    }

    public void update(ExamResource item) {

        ExamResource oldItem = findById(item);

        if(oldItem.getResourceId().equals(item.getResourceId())) {
            em.merge(item);
        }
    }

    public void remove(ExamResource examResource) {

        if(!em.contains(examResource)) {
            examResource = em.merge(examResource);
        }
        em.remove(examResource);
    }
}
