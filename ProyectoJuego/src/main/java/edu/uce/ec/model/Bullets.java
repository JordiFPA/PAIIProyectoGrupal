package edu.uce.ec.model;

import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.Color;
import java.awt.Graphics;

public class Bullets implements Movable, Drawable {

    private int x, y;
    private int speed = 5;
    private int width = 10;
    private int height = 10;

    public Bullets(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, width, height);
    }

    @Override
    public void moveUp(int variable) {
        y -= variable;
    }

    @Override
    public void moveDown(int variable) {
        y += variable + 3;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkCollision(int x, int y) {
        return this.x < x && x < this.x + width && this.y < y && y < this.y + height;
    }
}
