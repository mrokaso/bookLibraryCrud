package web.endpoints;


import business.exceptions.ResourceNotExistingException;
import business.mapers.models.AuthorRequest;
import data.entities.Author;
import data.modelsSearch.SearchAuthorCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.AuthorService;

import java.util.List;

@Api
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation("Get all of authors")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/authors")
    public Page<Author> getAllAuthors(Pageable pageable){
        return authorService.getAllAuthors(pageable);
    }

    @ApiOperation("Search authors")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/author/search")
    public Page<Author> searchAllAuthors(@RequestBody SearchAuthorCriteria searchAuthorCriteria, Pageable pageable){
        List<Author> authors = authorService.searchForAuthors(searchAuthorCriteria);
        return new PageImpl<>(authors, pageable, authors.size());
    }

    @ApiOperation("Get author by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Author not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/author/{id}")
    public Author getAuthor(@PathVariable long id){
        return authorService.getAuthor(id);
    }

    @ApiOperation("Add author")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/author")
    public void addAuthor(@RequestBody AuthorRequest author){
        authorService.addAuthor(author);
    }

    @ApiOperation("Edit author")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/author")
    public void editAuthor(@RequestBody Author author){
        authorService.editAuthor(author);
    }

    @ApiOperation("Delete author by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/author/{id}")
    public void deleteAuthor(@PathVariable long id){
        authorService.deleteAuthor(id);
    }
}
