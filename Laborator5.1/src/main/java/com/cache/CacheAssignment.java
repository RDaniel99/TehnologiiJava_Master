package com.cache;

import com.bean.ResourceBean;
import com.model.Exam;
import com.model.ExamResource;
import com.model.Resource;
import com.repo.ExamResourceRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
@Named("cache")
@ApplicationScoped
public class CacheAssignment implements Serializable {

    @EJB
    private ExamResourceRepository repository;

    @Inject
    private ResourceBean bean;

    private Map<Long, List<Long>> resourceIdToExamId;

    private List<ExamResource> items;

    @PostConstruct
    public void init() {

        items = repository.getAll();

        resourceIdToExamId = new HashMap<>();
        for(ExamResource item: items) {

            add(item);
        }
    }

    public List<ExamResource> getItems() {
        return items;
    }

    public void onExamResourceUpdate(@Observes ExamResource entity) {
        resourceIdToExamId = null;
    }

    public void update(ExamResource examResource) {
        repository.update(examResource);
        onExamResourceUpdate(examResource);
    }

    public void add(ExamResource examResource) {

        Long resourceId = examResource.getResourceId();
        Long examId = examResource.getExamId();

        if(!resourceIdToExamId.containsKey(resourceId)) {

            resourceIdToExamId.put(resourceId, new ArrayList<>());
        }

        resourceIdToExamId.get(resourceId).add(examId);
    }

    public void save(ExamResource examResource) {

        if(bean.isResourceAvailable(examResource.getResourceId())) {
            add(examResource);

            items.add(examResource);
            repository.save(examResource);
            bean.decrementResource(examResource.getResourceId());
        }
    }

    public void remove(ExamResource examResource) {

        Long resourceId = examResource.getResourceId();
        Long examId = examResource.getExamId();

        if(resourceIdToExamId.containsKey(resourceId)) {

            int position = resourceIdToExamId.get(resourceId).lastIndexOf(examId);
            resourceIdToExamId.get(resourceId).remove(position);
        }

        items.remove(examResource);
        repository.remove(examResource);
        bean.incrementResource(resourceId);
    }
}
