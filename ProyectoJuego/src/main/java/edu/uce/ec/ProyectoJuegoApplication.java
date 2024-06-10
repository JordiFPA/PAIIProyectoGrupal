package edu.uce.ec;

import edu.uce.ec.view.Window1;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Michael Barrionuevo, Byron Flores, Jordi Pila
 * @Theme "Proyecto Juego"
 */

@SpringBootApplication
public class ProyectoJuegoApplication {

    public static void main(String[] args) {

        Window1 W = new Window1("Proyect_Galaga");
        W.setVisible(true);
    }
}

