package ec.edu.uce.basicJPA;

import ec.edu.uce.basicJPA.models.User;
import ec.edu.uce.basicJPA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicJpaApplication implements CommandLineRunner {
	@Autowired
UserService serviceU = new UserService();
	public static void main(String[] args) {

		SpringApplication.run(BasicJpaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

	}
}
