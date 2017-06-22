package data.repositories;


import data.entities.Author;
import data.models.SearchAuthorCriteria;

import java.util.List;

public interface AuthorRepositoryCustom {
    List<Author> search(SearchAuthorCriteria searchAuthorCriteria);
}
