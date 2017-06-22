package business;


import data.entities.PublicationPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationPlaceService {
    void addPublicationPlace(String name);
    void editPublicationPlace(PublicationPlace publicationPlace);
    void deletePublicationPlace(long id);
    List<PublicationPlace> getPublicationPlaces();
    PublicationPlace getPublicationPlace(long id);
}
