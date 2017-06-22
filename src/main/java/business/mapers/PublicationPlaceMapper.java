package business.mapers;

import data.entities.PublicationPlace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "SPRING")
public interface PublicationPlaceMapper {
    PublicationPlace toPublicationPlace(String publicationPlace);
}
