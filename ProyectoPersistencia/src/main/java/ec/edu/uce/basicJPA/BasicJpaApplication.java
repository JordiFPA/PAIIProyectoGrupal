package ec.edu.uce.basicJPA;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Michael Barrionuevo, Byron Flores, Jordi Pila
 * @Theme "Proyecto Juego"
 */


@SpringBootApplication
public class BasicJpaApplication implements CommandLineRunner {


	public static void main(String[] args) {

		SpringApplication.run(BasicJpaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
