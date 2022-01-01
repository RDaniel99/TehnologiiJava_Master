import models.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repo.DocumentRepository;
import services.GetDocumentsService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetDocumentsServiceShould {

    @Test
    public void retrieveDocuments() {

        // setup
        DocumentRepository repo = mock(DocumentRepository.class);
        GetDocumentsService service = new GetDocumentsService(repo);

        Document doc = new Document();
        doc.setIsbn("isbn");
        doc.setTitle("title");
        doc.setContent("content");
        doc.setId(1L);

        when(repo.retrieve()).thenReturn(Collections.singletonList(doc));

        // execute
        List<Document> documents = service.getDocuments();

        // verify
        assertEquals(1, documents.size());
        assertEquals(1, documents.get(0).getId());
        assertEquals("title", documents.get(0).getTitle());
        assertEquals("isbn", documents.get(0).getIsbn());
        assertEquals("content", documents.get(0).getContent());
    }
}
