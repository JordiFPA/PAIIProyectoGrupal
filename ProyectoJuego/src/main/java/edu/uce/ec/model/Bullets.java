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
        y += variable + 3; // Incrementa un poco más rápido hacia abajo para evitar colisiones inmediatas
    }

    @Override
    public void moveLeft(int variable) {
        // No es necesario implementar este método para los proyectiles
    }

    @Override
    public void moveRight(int variable) {
        // No es necesario implementar este método para los proyectiles
    }

    @Override
    public void draw(Graphics graphics, Drawable drawable) {
        // No es necesario implementar este método para los proyectiles
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkCollision(int x, int y) {
        // Verifica si un punto (x, y) está dentro del área del proyectil
        return this.x < x && x < this.x + width && this.y < y && y < this.y + height;
    }
}
