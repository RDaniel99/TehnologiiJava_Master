package beans;

import models.AuthorDocument;
import models.Document;
import producers.ISBNProducer;
import utils.PublishedDocument;
import utils.SessionUtils;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "createDocumentInput")
@ViewScoped
public class CreateDocumentInput implements Serializable {

    @Inject
    private DocumentBean documentBean;

    @Inject
    private AuthorDocumentBean authorDocumentBean;

    @Inject
    Event<PublishedDocument> event;

    private String title;
    private String content;
    private String authors;
    private String isbn;
    private Long id;

    public CreateDocumentInput() {}

    public void store() {

        storeDocument();
        storeRelations();

        event.fire(new PublishedDocument(isbn));

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Document Published",
                        "The " + SessionUtils.getUsername() + " added new document"));
    }

    private void storeDocument() {

        Document document = new Document();
        document.setContent(content);
        document.setTitle(title);
        isbn = ISBNProducer.produceISBN();
        document.setIsbn(isbn);

        id = documentBean.save(document).getId();
    }

    private void storeRelations() {

        String[] authorsList = authors.split(",");

        for(String author: authorsList) {

            AuthorDocument authorDocument = new AuthorDocument();
            authorDocument.setAuthorId(Long.parseLong(author));
            authorDocument.setDocumentId(id);

            authorDocumentBean.save(authorDocument);
        }
    }

    public DocumentBean getDocumentBean() {
        return documentBean;
    }

    public void setDocumentBean(DocumentBean documentBean) {
        this.documentBean = documentBean;
    }

    public AuthorDocumentBean getAuthorDocumentBean() {
        return authorDocumentBean;
    }

    public void setAuthorDocumentBean(AuthorDocumentBean authorDocumentBean) {
        this.authorDocumentBean = authorDocumentBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
