package ec.edu.uce.basicJPA.services;

import ec.edu.uce.basicJPA.models.User;
import ec.edu.uce.basicJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getOneUser(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Nuevo método para obtener usuarios ordenados por puntaje de mayor a menor
    public List<User> getUsersByScoreDesc() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public Optional<User> getUserByUsernameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }


}
