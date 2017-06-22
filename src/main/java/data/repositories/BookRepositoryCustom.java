package data.repositories;


import data.entities.Book;
import data.models.SearchBookCriteria;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> search(SearchBookCriteria searchBookCriteria);
}
