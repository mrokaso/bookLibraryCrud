package data.repositories;


import data.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByAuthors(Long idAuthor, Pageable pageable);
    Book findByIsbn(String isbn);
}

