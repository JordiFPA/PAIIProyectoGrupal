package edu.uce.ec.model;

import edu.uce.ec.interfaces.Shootable;
import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.*;
import java.util.ArrayList;

public class Hero implements Drawable, Movable, Shootable {

    public int[] cord_x = {400, 450, 350};
    public int[] cord_y = {500, 550, 550};
    private final User user;
    private int health = 0;
    private int score;

    public Hero(User user) {
        this.user = user;
        this.user.setHealth(user.getHealth());
        this.user.setScore(user.getScore());
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return user.getHealth();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);
    }


    public void reduceHealth(int damage) {
        user.setHealth(user.getHealth() - damage);
        if (user.getHealth() < 0) {
            user.setHealth(0);
        }
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {

    }

    @Override
    public void moveLeft(int variable) {
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] - variable;
        }

    }

    @Override
    public void moveRight(int variable) {
        for (int i = 0; i < cord_x.length; i++) {
            cord_x[i] = cord_x[i] + variable;
        }

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
