package com.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;

@ManagedBean(name = "rresource")
@Entity
@Table(name = "resource")
@NamedQueries({
        @NamedQuery(query = "Select r from Resource r", name = "Resource.findAll"),
        @NamedQuery(query = "Select r from Resource r where r.available > 0", name = "Resource.getAvailable"),
        @NamedQuery(query = "Select r from Resource r where r.id = :id", name = "Resource.findById")
})
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "available")
    private Long available;

    public Resource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }
}
