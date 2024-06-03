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
    private UserRepository repository;

    public void save(User user){
        repository.save(user);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> getOneUser(long id){
        return repository.findById(id);
    }

    public void deleteUser(long id){
        repository.deleteById(id);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }
}