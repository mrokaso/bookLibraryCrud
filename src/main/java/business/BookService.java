package business;


import business.mapers.models.BookRequest;
import business.mapers.models.BookResponse;
import data.entities.Book;
import data.models.SearchBookCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<Book> getAllBooks(Pageable pageable);
    Book getBook(long id);
    void addBook(BookRequest adminDTO);
    void deleteBook(long id);
    Page<Book> getAuthorBooks(long authorId, Pageable pageable);
    void subscribeBook(long bookId);
    List<Book> searchForBooks(SearchBookCriteria searchBookCriteria);
}
