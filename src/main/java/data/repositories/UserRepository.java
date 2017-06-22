package data.repositories;


import data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    Page<User> findAll(Pageable page);
    User findBySocialModeAndSocialId(String socialMode, String socialId);
}
