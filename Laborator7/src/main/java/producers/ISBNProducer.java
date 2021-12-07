package producers;

import javax.enterprise.inject.Produces;
import java.util.UUID;

public class ISBNProducer {

    @Produces
    public static String produceISBN() {

        return UUID.randomUUID().toString();
    }
}
