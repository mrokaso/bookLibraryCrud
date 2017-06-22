package data.repositories;


import com.querydsl.jpa.impl.JPAQuery;
import data.entities.Author;
import data.entities.QAuthor;
import data.models.SearchAuthorCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Author> search(SearchAuthorCriteria searchAuthorCriteria) {
        QAuthor author = QAuthor.author;
        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        JPAQuery<Author> boobs = query.select(author).from(author);

        if(searchAuthorCriteria.getNameLike() != null)
            boobs.where(author.name.like("%" + searchAuthorCriteria.getNameLike() + "%")
                   .or(author.surname.like("%" + searchAuthorCriteria.getNameLike() + "%")));
        if(searchAuthorCriteria.getBirthdateFrom() != null)
            boobs.where(author.birthDate.goe(searchAuthorCriteria.getBirthdateFrom()));
        if(searchAuthorCriteria.getBirthdateTo() != null)
            boobs.where(author.birthDate.loe(searchAuthorCriteria.getBirthdateTo()));

        return boobs.fetch();
    }
}
