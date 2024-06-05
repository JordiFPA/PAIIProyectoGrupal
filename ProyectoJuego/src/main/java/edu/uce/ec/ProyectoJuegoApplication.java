package edu.uce.ec;

import edu.uce.ec.view.GameFrame;
import edu.uce.ec.view.Window1;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoJuegoApplication {

    public static void main(String[] args) {
        GameFrame game = new GameFrame("Galaga");
        game.setVisible(true);

        //Window1 W = new Window1("Proyect Galaga");
       // W.setVisible(true);
    }
}

