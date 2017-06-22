package business.mapers.helpers;

import data.entities.Author;
import data.entities.PublicationPlace;
import data.entities.Publisher;
import org.springframework.stereotype.Component;

@Component
public class BookHelperMapper {

    public PublicationPlace toPublicationPlace(String publicationPlace){
        if(publicationPlace != null)
            return new PublicationPlace(publicationPlace);
        else return null;
    }

    public String toStringPublicationPlace(PublicationPlace publicationPlace){
        if(publicationPlace != null)
            return publicationPlace.getName();
        return null;
    }

    public Publisher toPublisher(String publisher){
        if(publisher != null)
            return new Publisher(publisher);
        else return null;
    }

    public String toStringPublisher(Publisher publisher) {
        if(publisher != null)
            return publisher.getName();
        return null;
    }

    public Author toAuthor(Long id) { return new Author(); }
}
