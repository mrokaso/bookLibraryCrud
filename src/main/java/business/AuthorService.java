package business;


import business.mapers.models.AuthorRequest;
import data.entities.Author;
import data.modelsSearch.SearchAuthorCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    Page<Author> getAllAuthors(Pageable pageable);
    Author getAuthor(long authorId);
    void addAuthor(AuthorRequest author);
    void editAuthor(Author author);
    void deleteAuthor(long idAuthor);
    List<Author> searchForAuthors(SearchAuthorCriteria searchAuthorCriteria);
}
