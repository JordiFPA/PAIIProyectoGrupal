package ec.edu.uce.basicJPA.repository;

import ec.edu.uce.basicJPA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Nuevo método para obtener usuarios ordenados por puntaje en orden descendente

    List<User> findAllByOrderByScoreDesc();

    Optional<User> findByNameAndPassword(String name, String password);


}
