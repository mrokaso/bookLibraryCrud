package web.endpoints;


import business.mapers.models.UserRequest;
import business.mapers.models.UserRequestAdd;
import data.entities.Rent;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.RentService;
import business.UserService;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RentService rentService;


    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{id}/rent")
    public Page<Rent> getRentByUser(@PathVariable long id, Pageable pageable){
        return userService.getRentByUser(id, pageable);
    }

    @GetMapping("/search/user")
    public User getUserBySocialModeAndSocialId(@RequestParam String socialMode, @RequestParam String socialId){
        return userService.getUserBySocialModeAndSocialId(socialMode,socialId);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody UserRequestAdd user) {
        return userService.addUser(user);
    }

    // TODO PERMISSIONS CLIENT
    @PostMapping("/user/{userId}/rent/book/{bookId}")
    public void rentBook(@PathVariable long userId, @PathVariable long bookId){
        rentService.rentBook(userId, bookId);
    }

    //TODO PERMISSIONS ADMIN
    @PostMapping("/user/{userId}/admin/rent/book/{bookId}")
    public void rentBook(@PathVariable long userId, @PathVariable long bookId, @RequestBody boolean taken){
        rentService.rentBook(userId, bookId, taken);
    }

    //TODO PERMISSIONS ADMIN
    @PostMapping("/user/{userId}/rent/book/{bookId}/return")
    public void returnBook(@PathVariable long userId, @PathVariable long bookId){
        rentService.returnBook(userId, bookId);
    }

    @PutMapping("/user/{id}")
    public void editUser(@RequestBody UserRequest user, @PathVariable Long id) {
        userService.editUser(user, id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
