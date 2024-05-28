package edu.uce.ec.model;

import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.Color;
import java.awt.Graphics;

public class Hero implements Drawable, Movable {

    int[] cord_x = {400, 450, 350};
    int[] cord_y = {500, 550, 550};

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);

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

}
