package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "authorsdocuments")
@NamedQueries({
        @NamedQuery(query = "Select ad from AuthorDocument ad", name = "AuthorDocument.findAll")
})
public class AuthorDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Long id;

    @Column(name = "authorid")
    @NotNull
    private String authorId;

    @Column(name = "documentid")
    @NotNull
    private String documentId;

    public AuthorDocument() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
