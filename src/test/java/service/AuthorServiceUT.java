package service;

import business.AuthorService;
import business.impl.AuthorServiceImpl;
import data.repositories.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import web.endpoints.AuthorController;

import java.utils.AuthorUtils;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceUT {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService = new AuthorServiceImpl();

    @Before
    public void setup() throws Exception {
        initMocks(this);
    }

    @Test
    public void testGetAuthorByIdWhenIsNotFound() {
        when(authorRepository.getOne((long)1)).thenReturn(AuthorUtils.getAuthor(true));


    }




}
