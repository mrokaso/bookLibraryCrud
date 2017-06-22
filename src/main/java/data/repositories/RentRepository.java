package data.repositories;


import data.entities.Book;
import data.entities.Rent;
import data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long>{
    Page<Rent> findAllByUser(User user, Pageable page);
    Page<Rent> findAllByUserAndDateReturnIsNull(User user, Pageable page);
    Long countByBookAndDateReturnIsNull(Book book);
    List<Rent> findByBookAndUserAndDateReturnIsNull(Book book, User user);
}
