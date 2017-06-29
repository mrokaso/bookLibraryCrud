package controller;

import business.AuthorService;
import business.exceptions.ResourceNotFoundException;
import data.entities.Author;

import java.utils.AuthorUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import web.endpoints.AuthorController;
import web.endpoints.ExceptionController;

import java.util.Locale;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerUT {

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController = new AuthorController();

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new ExceptionController())
                .setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                })
                .build();
    }

    @Test
    public void testControllerGetAllAuthorsReturnObjectPageableWithTwoAuthors() throws Exception {
        Pageable defaultPageable = new PageRequest(0, 20);
        Page<Author> authors = AuthorUtils.getAuthors(defaultPageable);
        when(authorService.getAllAuthors(defaultPageable)).thenReturn(authors);
        mockMvc.perform(
                get("/authors").contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name").value(authors.getContent().get(0).getName()))
                .andExpect(jsonPath("$.content[0].surname").value(authors.getContent().get(0).getSurname()))
                .andExpect(jsonPath("$.content[0].birthDate").value(authors.getContent().get(0).getBirthDate()))
                .andExpect(jsonPath("$.content[0].books").doesNotExist())
                .andExpect(jsonPath("$.content[1].name").value(authors.getContent().get(1).getName()))
                .andExpect(jsonPath("$.content[1].surname").value(authors.getContent().get(1).getSurname()))
                .andExpect(jsonPath("$.content[1].books").doesNotExist());
    }

    @Test
    public void testControllerGetAuthorByIdReturnObjectAuthor() throws Exception {
        Author author = AuthorUtils.getAuthor(true);
        when(authorService.getAuthor(1)).thenReturn(author);
        mockMvc.perform(
                get("/author/1").contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(author.getName()))
                .andExpect(jsonPath("$.surname").value(author.getSurname()))
                .andExpect(jsonPath("$.birthDate").value(author.getBirthDate()))
                .andExpect(jsonPath("$.books").doesNotExist());
    }

    @Test
    public void testControllerGetAuthorByIdReturn404() throws Exception {
        when(authorService.getAuthor(1)).thenThrow(new ResourceNotFoundException());
        mockMvc.perform(
                get("/author/1").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }


}
