package data.repositories;


import com.querydsl.jpa.impl.JPAQuery;
import data.entities.Book;
import data.entities.QBook;
import data.modelsSearch.SearchBookCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class BookRepositoryImpl implements BookRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> search(SearchBookCriteria searchBookCriteria) {
        QBook book = QBook.book;
        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        JPAQuery<Book> boobs = query.select(book).from(book);

        if(searchBookCriteria.getTitleLike() != null && !searchBookCriteria.getTitleLike().isEmpty())
            boobs.where(book.title.like("%" + searchBookCriteria.getTitleLike() + "%"));
        if(searchBookCriteria.getIsbn() != null && !searchBookCriteria.getIsbn().isEmpty())
            boobs.where(book.isbn.eq(searchBookCriteria.getIsbn()));
        if(searchBookCriteria.getYearFrom() != null)
            boobs.where(book.publicationYear.goe(searchBookCriteria.getYearFrom()));
        if(searchBookCriteria.getYearTo() != null)
            boobs.where(book.publicationYear.loe(searchBookCriteria.getYearTo()));
        if(searchBookCriteria.getPublisher() != null && !searchBookCriteria.getPublisher().isEmpty())
            boobs.where(book.publisher.name.like("%" + searchBookCriteria.getPublisher() + "%"));
        if(searchBookCriteria.getPublicationPlace() != null && !searchBookCriteria.getPublicationPlace().isEmpty())
            boobs.where(book.publicationPlace.name.like("%" + searchBookCriteria.getPublicationPlace() + "%"));

        return boobs.fetch();
    }
}
