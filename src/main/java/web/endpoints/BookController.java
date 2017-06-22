package web.endpoints;


import business.exceptions.ResourceNotExistingException;
import business.mapers.BookMapper;
import business.mapers.models.BookRequest;
import business.mapers.models.BookResponse;
import data.entities.Book;
import data.models.SearchBookCriteria;
import data.repositories.RentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.BookService;

import java.util.List;

@Api
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookMapper bookMapper;

    @ApiOperation("Get all of books")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public Page<BookResponse> getAllBooks(Pageable pageable){
        Page<Book> booksPage = bookService.getAllBooks(pageable);
        List<BookResponse> booksDTO = bookMapper.toResponses(booksPage.getContent());
        for(int i = 0; i < booksDTO.size(); i++)
            booksDTO.get(i).setAvailableCount((int) (booksDTO.get(i).getCount() - rentRepository.countByBookAndDateReturnIsNull(booksPage.getContent().get(i))));
        return new PageImpl<>(booksDTO, pageable, booksPage.getTotalElements());
    }

    @ApiOperation("Search of books")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/book/search")
    public Page<BookResponse> searchBooks(@RequestBody SearchBookCriteria searchBookCriteria, Pageable pageable){
        List<Book> books = bookService.searchForBooks(searchBookCriteria);
        List<BookResponse> booksDTO = bookMapper.toResponses(books);
        for(int i = 0; i < booksDTO.size(); i++)
            booksDTO.get(i).setAvailableCount((int) (booksDTO.get(i).getCount() - rentRepository.countByBookAndDateReturnIsNull(books.get(i))));
        return new PageImpl<>(booksDTO, pageable, booksDTO.size());
    }

    @ApiOperation("Get books by author")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/books/author/{authorId}")
    public Page<BookResponse> getAuthorBooks(@PathVariable long authorId, Pageable pageable){
        Page<Book> booksPage = bookService.getAuthorBooks(authorId, pageable);
        List<BookResponse> booksDTO = bookMapper.toResponses(booksPage.getContent());
        for(int i = 0; i < booksDTO.size(); i++)
            booksDTO.get(i).setAvailableCount((int) (booksDTO.get(i).getCount() - rentRepository.countByBookAndDateReturnIsNull(booksPage.getContent().get(i))));
        return new PageImpl<>(booksDTO, pageable, booksPage.getTotalElements());
    }

    @ApiOperation("Get book by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/book/{id}")
    public BookResponse getBook(@PathVariable long id){
        Book book = bookService.getBook(id);
        if(book == null) throw new ResourceNotExistingException();
        BookResponse bookResponse = bookMapper.toResponse(book);
        bookResponse.setAvailableCount((int) (bookResponse.getCount() - rentRepository.countByBookAndDateReturnIsNull(book)));
        return bookResponse;
    }

    @ApiOperation("Add book")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/book")
    public void addBook(@RequestBody BookRequest book){
        bookService.addBook(book);
    }

    @ApiOperation("Subscribe book")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/book/subscribe/{bookId}")
    public void subscribeBook(@PathVariable long bookId){
        bookService.subscribeBook(bookId);
    }

    @ApiOperation("Delete book")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/book/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }

}
