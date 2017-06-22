package data.repositories;


import data.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

}
