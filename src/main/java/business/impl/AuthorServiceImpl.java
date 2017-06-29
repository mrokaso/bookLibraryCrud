package business.impl;


import business.exceptions.MissingIdParameterRequestException;
import business.exceptions.ResourceNotFoundException;
import business.mapers.AuthorMapper;
import business.mapers.models.AuthorRequest;
import data.entities.Author;
import data.modelsSearch.SearchAuthorCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.AuthorRepository;
import business.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;

    @Override
    public Page<Author> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author getAuthor(long authorId) {
        Author author = authorRepository.findOne(authorId);
        if(author == null) throw new ResourceNotFoundException();
        return author;
    }

    @Override
    public void addAuthor(AuthorRequest author) {
        Author auth = authorMapper.toAuthor(author);
        authorRepository.save(auth);
    }

    @Override
    public void editAuthor(Author author) {
        if(author.getId() == null) throw new MissingIdParameterRequestException();
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(long idAuthor) {
        authorRepository.delete(idAuthor);
    }

    @Override
    public List<Author> searchForAuthors(SearchAuthorCriteria searchAuthorCriteria) {
        return authorRepository.search(searchAuthorCriteria);
    }


}
