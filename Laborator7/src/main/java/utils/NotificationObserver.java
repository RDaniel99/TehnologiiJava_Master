package utils;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class NotificationObserver {

    public void notify(@Observes PublishedDocument event) {

        System.out.println("New document with ISBN = " + event.getISBN());
    }
}
