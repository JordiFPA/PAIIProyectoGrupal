package edu.uce.ec.model;

import edu.uce.ec.interfaces.Shootable;
import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.*;
import java.util.ArrayList;

public class Opponents implements Drawable, Movable, Shootable {

    public int[] cord_x = new int[5];
    public int[] cord_y = new int[5];
    public boolean isLarge;
    private int health; // Añadir contador de impactos
    private int maxHealth; // Salud máxima para la barra de vida

    public Opponents() {
    }

    public Opponents(int randomX, int randomY) {
        this(randomX, randomY, false, 1.0);
    }

    public Opponents(int randomX, int randomY, boolean isLarge) {
        this(randomX, randomY, isLarge, 2.5);
    }

    public Opponents(int randomX, int randomY, boolean isLarge, double enlargeFactor) {
        this.isLarge = isLarge;
        this.maxHealth = isLarge ? 100 : 1; // Salud máxima 100 si es grande (nivel 3), 1 en caso contrario
        this.health = maxHealth; // Inicializar con la salud máxima
        initializeCoordinates(randomX, randomY);
        if (isLarge) {
            enlarge(enlargeFactor);
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    private void initializeCoordinates(int randomX, int randomY) {
        cord_x[0] = randomX;
        cord_x[1] = randomX + 100;
        cord_x[2] = randomX + 100;
        cord_x[3] = randomX + 50;
        cord_x[4] = randomX;

        cord_y[0] = randomY;
        cord_y[1] = randomY;
        cord_y[2] = randomY + 50;
        cord_y[3] = randomY + 25;
        cord_y[4] = randomY + 50;
    }

    private void enlarge(double factor) {
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = (int) (cord_x[i] * factor - cord_x[0] * (factor - 1));  // Ajuste de posición según el factor
            cord_y[i] = (int) (cord_y[i] * factor - cord_y[0] * (factor - 1));  // Ajuste de posición según el factor
        }
    }

    @Override
    public void draw(Graphics graphics) {
        if (isLarge) {
            graphics.setColor(Color.GREEN);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.fillPolygon(cord_x, cord_y, 5);

        if (isLarge) {
            drawHealthPercentage(graphics);
        }
    }

    public void drawHealthPercentage(Graphics graphics) {
        if (isLarge) {
            int healthBarWidth = 50;
            int healthBarHeight = 5;
            int currentHealthWidth = (int) ((health / (double) maxHealth) * healthBarWidth);

            graphics.setColor(Color.RED);
            graphics.fillRect(cord_x[0] - 25, cord_y[0] - 10, healthBarWidth, healthBarHeight);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(cord_x[0] - 25, cord_y[0] - 10, currentHealthWidth, healthBarHeight);

            // Dibujar el texto de porcentaje de salud
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 15));
            graphics.drawString(health + "%", cord_x[0] - 25, cord_y[0] - 15);
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {
        for (int i = 0; i < cord_y.length; i++) {
            cord_y[i] += variable;
        }
    }

    @Override
    public void moveLeft(int variable) {

    }

    @Override
    public void moveRight(int variable) {

    }

    @Override
    public void draw(Graphics graphics, Drawable drawable) {

    }

    @Override
    public void shoot(ArrayList<Bullets> e) {

    }

    @Override
    public boolean checkCollision(int x, int y) {
        Polygon polygon = new Polygon(cord_x, cord_y, cord_x.length);
        return polygon.contains(x, y);
    }
}
