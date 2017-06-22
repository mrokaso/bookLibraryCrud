package business.mapers;

import business.mapers.helpers.BookHelperMapper;
import business.mapers.models.BookRequest;
import business.mapers.models.BookResponse;
import data.entities.Book;
import business.mapers.models.BookRummager;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {BookHelperMapper.class}, componentModel = "SPRING", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface BookMapper {

    Book toBook(BookRummager dtoBook);
    Book toBook(BookRequest dtoBook);
    BookRummager toRummagerBook(Book book);

    @Mappings({
            @Mapping( target = "title", source = "rummaReq.title"),
            @Mapping( target = "pages", source = "rummaReq.pages"),
            @Mapping( target = "description", source = "rummaReq.description"),
            @Mapping( target = "publicationPlace", source = "rummaReq.publicationPlace"),
            @Mapping( target = "isbn", source = "rummaReq.isbn"),
            @Mapping( target = "image", source = "rummaReq.image"),
            @Mapping( target = "publisher", source = "rummaReq.publisher"),
            @Mapping( target = "weight", source = "rummaReq.weight"),
            @Mapping( target = "publicationYear", source = "rummaReq.publicationYear")
    })
    Book toBook(BookRequest bookRequest, BookRummager rummaReq);
    BookResponse toResponse(Book book);

    List<BookResponse> toResponses(List<Book> books);
}
