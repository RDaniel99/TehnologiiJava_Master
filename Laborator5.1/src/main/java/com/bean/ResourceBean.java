package com.bean;

import com.model.Resource;
import com.repo.ExamRepository;
import com.repo.ResourceRepository;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "resourceBean")
@ViewScoped
public class ResourceBean implements Serializable {

    @EJB
    ResourceRepository repository;

    public ResourceBean() {}

    public List<Resource> getAllResources() {

        return repository.getAllResources();
    }

    public void deleteResource(Resource resource) {

        repository.deleteResource(resource);
    }

    public void saveResource(Resource resource) {

        repository.storeResource(resource);
    }

    public void updateResource(Resource resource) {

        repository.updateResource(resource);
    }

    public void incrementResource(Long resourceId) {

        repository.incrementResource(resourceId);
    }

    public void decrementResource(Long resourceId) {

        repository.decrementResource(resourceId);
    }

    public Boolean isResourceAvailable(Long resourceId) {

        return repository.getAvailableResources().stream().anyMatch(r -> r.getId().equals(resourceId));
    }

    public void onRowUpdate(RowEditEvent event) {

        Resource resource = (Resource) event.getObject();

        updateResource(resource);
    }

    public void onRowCancel(RowEditEvent event) { }
}
