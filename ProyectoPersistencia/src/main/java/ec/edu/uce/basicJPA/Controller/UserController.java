package ec.edu.uce.basicJPA.Controller;

import ec.edu.uce.basicJPA.models.User;
import ec.edu.uce.basicJPA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userService.getOneUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());
            user.setScore(updatedUser.getScore());
            user.setHealth(updatedUser.getHealth());
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUser/")
    public void deleteUser(@RequestParam(name="id") long id) {
        userService.deleteUser(id);
    }

    // Nuevo endpoint para obtener el ranking de usuarios
    @GetMapping("/getRanking")
    public ResponseEntity<List<User>> getRanking() {
        List<User> rankingList = userService.getUsersByScoreDesc();
        return ResponseEntity.ok(rankingList);
    }

    @GetMapping("/getUserByUsernameAndPassword")
    public ResponseEntity<User> getUserByUsernameAndPassword(@RequestParam(name="name") String name,
                                                             @RequestParam(name="password") String password) {
        Optional<User> optionalUser = userService.getUserByUsernameAndPassword(name, password);
        return optionalUser.map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
