package business.mapers;

import business.mapers.models.AuthorRequest;
import data.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "SPRING")
public interface AuthorMapper {
    Author toAuthor(AuthorRequest authorRequest);
}
