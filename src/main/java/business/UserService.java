package business;

import business.mapers.models.UserRequest;
import business.mapers.models.UserRequestAdd;
import data.entities.Rent;
import data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getAll(Pageable pageable);
    User getUserBySocialModeAndSocialId(String socialMode, String socialId);
    User getUserById(long id);
    Page<Rent> getRentByUser(long id, Pageable pageable);
    User addUser(UserRequestAdd user);
    void editUser(UserRequest user, Long id);
    void deleteUser(long id);
}
