package ec.edu.uce.basicJPA.repository;

import ec.edu.uce.basicJPA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
