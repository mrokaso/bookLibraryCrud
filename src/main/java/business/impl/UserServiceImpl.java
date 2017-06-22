package business.impl;


import business.exceptions.ResourceNotExistingException;
import business.mapers.UserMapper;
import business.mapers.models.UserRequest;
import business.mapers.models.UserRequestAdd;
import data.entities.Rent;
import data.entities.User;
import business.exceptions.ResourceExistingException;
import business.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.RentRepository;
import data.repositories.UserRepository;
import business.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RentRepository rentRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
        this.userMapper = userMapper;
    }

    public Page<User> getAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User getUserBySocialModeAndSocialId(String socialMode, String socialId){
        User user = userRepository.findBySocialModeAndSocialId(socialMode, socialId);
        if(user == null) throw new ResourceNotFoundException();
        return user;
    }

    public User getUserById(long id) {
        User user = userRepository.findOne(id);
        if (user == null) throw new ResourceNotFoundException();
        return user;
    }

    public Page<Rent> getRentByUser(long id, Pageable pageable){
        User user = userRepository.findOne(id);
        if (user == null) throw new ResourceNotFoundException();
        return rentRepository.findAllByUserAndDateReturnIsNull(user, pageable);
    }

    public User addUser(UserRequestAdd user) {
        User userTmp = userRepository.findBySocialModeAndSocialId(user.getSocialMode(), user.getSocialId());
        if (userTmp != null)
            throw new ResourceExistingException();
        return userRepository.save(userMapper.toUser(user));
    }

    public void editUser(UserRequest userRequest, Long id) {
        User user = userRepository.findOne(id);
        if(user == null) throw new ResourceNotExistingException();
        user = userMapper.toUser(userRequest, user);
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        User user = userRepository.findOne(id);
        if (user == null) throw new ResourceNotFoundException();
        userRepository.delete(user);
    }
}
