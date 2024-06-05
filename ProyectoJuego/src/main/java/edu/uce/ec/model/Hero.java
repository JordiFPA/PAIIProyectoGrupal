package edu.uce.ec.model;

import edu.uce.ec.interfaces.Shootable;
import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.*;
import java.util.ArrayList;

public class Hero implements Drawable, Movable, Shootable {

    public int[] cord_x = {400, 450, 350};
    public int[] cord_y = {500, 550, 550};
    private int health = 100;

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public void moveUp(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(int variable) {
        // Restar
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] - variable;
        }

    }

    @Override
    public void moveRight(int variable) {
        // Sumar
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] + variable;
        }

    }

    @Override
    public void draw(Graphics graphics, Drawable drawable) {
        // TODO Auto-generated method stub

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
