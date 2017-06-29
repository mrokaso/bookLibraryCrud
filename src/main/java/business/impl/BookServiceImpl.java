package business.impl;

import business.exceptions.ResourceExistingException;
import business.mapers.models.BookRequest;
import business.security.JwtService;
import data.entities.*;
import business.exceptions.ResourceNotFoundException;
import business.mapers.BookMapper;
import business.mapers.models.BookRummager;
import data.modelsSearch.SearchBookCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.AuthorRepository;
import data.repositories.BookRepository;
import data.repositories.PublicationPlaceRepository;
import data.repositories.PublisherRepository;
import business.BookService;
import java.utils.http.HttpMethod;
import java.utils.http.HttpRequest;
import java.utils.http.HttpRequestBuilder;
import java.utils.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublicationPlaceRepository publicationPlaceRepository;
    private PublisherRepository publisherRepository;
    private BookMapper bookMapper;
    private JwtService jwtService;

    @Value("${modules.rummagerAPI}")
    private String rummagerAPI;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           PublicationPlaceRepository publicationPlaceRepository,
                           PublisherRepository publisherRepository,
                           BookMapper bookMapper,
                           JwtService jwtService){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publicationPlaceRepository = publicationPlaceRepository;
        this.publisherRepository = publisherRepository;
        this.bookMapper = bookMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBook(long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) throw new ResourceNotFoundException();
        return book;
    }

    @Override
    public void addBook(BookRequest bookRequest) {

        Book book = bookMapper.toBook(bookRequest);
        if(bookRepository.findByIsbn(book.getIsbn()) != null) throw new ResourceExistingException();
        BookRummager bookRummagerRequest = bookMapper.toRummagerBook(book);

        HttpRequest request = new HttpRequestBuilder()
                .withMethod(HttpMethod.POST)
                .withUri(rummagerAPI + "/book/")
                .withBody(bookRummagerRequest)
                .withHeader("Authorization", jwtService.createModuleToken())
                .build();

        HttpResponse response = request.send();
        if (response.getStatusCode() != 200) return;
        else {
            bookRummagerRequest = response.getBody(BookRummager.class);
            book = bookMapper.toBook(bookRequest, bookRummagerRequest);
        }

        List<Author> listaAuth = new ArrayList<Author>();
        List<Long> listOfAuthors = bookRequest.getAuthors();
        if(listOfAuthors != null)
            for(Long auth : listOfAuthors){
                Author author = authorRepository.getOne(auth);
                if(auth == null) throw new ResourceNotFoundException();
                listaAuth.add(author);
            }

        book.setAuthors(listaAuth);

        if(book.getPublicationPlace() != null) {
            PublicationPlace publicationPlace = publicationPlaceRepository.findByName(book.getPublicationPlace().getName());
            if (publicationPlace == null)
                publicationPlace = publicationPlaceRepository.save(book.getPublicationPlace());
            book.setPublicationPlace(publicationPlace);
        }

        if(book.getPublisher() != null) {
            Publisher publisher = publisherRepository.findByName(book.getPublisher().getName());
            if (publisher == null) publisher = publisherRepository.save(book.getPublisher());
            book.setPublisher(publisher);
        }

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.delete(id);
    }

    @Override
    public Page<Book> getAuthorBooks(long authorId, Pageable pageable) {
        Author auth = authorRepository.findOne(authorId);
        if(auth == null) throw new ResourceNotFoundException();
        return bookRepository.findAllByAuthors(authorId, pageable);
    }

    @Override
    public void subscribeBook(long bookId) {
        Book book = bookRepository.findOne(bookId);
        if(book == null) throw new ResourceNotFoundException();
        if(!book.isSubscribed()){
            book.setSubscribed(true);
            bookRepository.save(book);
        }
    }

    @Override
    public List<Book> searchForBooks(SearchBookCriteria searchBookCriteria) {
        return bookRepository.search(searchBookCriteria);
    }
}
