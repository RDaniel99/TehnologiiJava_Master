package com.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@ManagedBean(name = "examresource")
@Entity
@Table(name = "examresource")
@NamedQueries({
        @NamedQuery(query = "Select er from ExamResource er", name = "ExamResource.findAll"),
        @NamedQuery(query = "Select er from ExamResource er where er.id = :id", name = "ExamResource.findById")
})
public class ExamResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "examid")
    private Long examId;

    @Column(name = "resourceid")
    private Long resourceId;

    public ExamResource() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
