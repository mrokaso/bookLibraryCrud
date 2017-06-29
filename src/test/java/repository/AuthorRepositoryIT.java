package repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import data.entities.Author;
import data.modelsSearch.SearchAuthorCriteria;
import data.repositories.AuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import config.DatabaseConfig;
import config.WebConfig;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, DatabaseConfig.class})
@DatabaseSetup(AuthorRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { AuthorRepositoryIT.DATASET })
@DirtiesContext
public class AuthorRepositoryIT {

    protected static final String DATASET = "classpath:datasets/author-items.xml";
    protected static final LocalDate OneDATE = LocalDate.of(2000,10,10);
    protected static final LocalDate TwoDATE = LocalDate.of(2000,10,12);

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testCheckSearchAuthorByNameLike() throws Exception {
        SearchAuthorCriteria searchAuthorCriteria = new SearchAuthorCriteria();
        searchAuthorCriteria.setNameLike("Mar");
        assertThat(authorRepository.search(searchAuthorCriteria))
                .hasSize(2)
                .extracting("id")
                .containsOnly(2L, 3L);
    }

    @Test
    public void testCheckSearchAuthorByBirthdayFrom() throws Exception {
        SearchAuthorCriteria searchAuthorCriteria = new SearchAuthorCriteria();
        searchAuthorCriteria.setBirthdateFrom(Date.from(OneDATE.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertThat(authorRepository.search(searchAuthorCriteria))
                .hasSize(3)
                .extracting("id")
                .containsOnly(2L, 3L, 4L);
    }

    @Test
    public void testCheckSearchAuthorByBirthdayTo() throws Exception {
        SearchAuthorCriteria searchAuthorCriteria = new SearchAuthorCriteria();
        searchAuthorCriteria.setBirthdateTo(Date.from(OneDATE.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertThat(authorRepository.search(searchAuthorCriteria))
                .hasSize(1)
                .extracting("id")
                .containsOnly(1L);
    }

    @Test
    public void testCheckSearchAuthorByNameLikeAndBirthdayFromAndBirthdayTo() throws Exception {
        SearchAuthorCriteria searchAuthorCriteria = new SearchAuthorCriteria();
        searchAuthorCriteria.setNameLike("Mar");
        searchAuthorCriteria.setBirthdateFrom(Date.from(OneDATE.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        searchAuthorCriteria.setBirthdateTo(Date.from(TwoDATE.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertThat(authorRepository.search(searchAuthorCriteria))
                .hasSize(1)
                .extracting("id")
                .containsOnly(2L);
    }
}
