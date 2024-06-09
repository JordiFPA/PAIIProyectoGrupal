package ec.edu.uce.basicJPA.Controller;
import ec.edu.uce.basicJPA.models.User;
import ec.edu.uce.basicJPA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserId/")
    public Optional<User> getUserById(@RequestParam(name="id") long id) {

        return userService.getOneUser(id);
    }

    @PostMapping("/createUser/")
    public User createUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping("/updateUser/")
    public User updateUser(@RequestParam(name="id") long id, @RequestBody User userUpDate) {
        Optional<User> optionalUser = userService.getOneUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userUpDate.getName());
            user.setPassword(userUpDate.getPassword());
            user.setScore(userUpDate.getScore());
            user.setHealth(userUpDate.getHealth());
            return userService.updateUser(user);
        } else {
            return null;
        }
    }

    @DeleteMapping("/deleteUser/")
    public void deleteUser(@RequestParam(name="id") long id) {

        userService.deleteUser(id);
    }
}